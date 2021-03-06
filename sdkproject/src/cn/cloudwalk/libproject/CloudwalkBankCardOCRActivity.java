package cn.cloudwalk.libproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Method;

import cn.cloudwalk.BankOcrSDK;
import cn.cloudwalk.callback.BankCardCallback;
import cn.cloudwalk.jni.BankCardInfo;
import cn.cloudwalk.libproject.camera.AutoFocusCameraPreview;
import cn.cloudwalk.libproject.camera.Delegate;
import cn.cloudwalk.libproject.net.HttpManager;
import cn.cloudwalk.libproject.progressHUD.CwProgressHUD;
import cn.cloudwalk.libproject.util.ImgUtil;
import cn.cloudwalk.libproject.util.Util;
import cn.cloudwalk.libproject.view.OcrMaskView;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

public class CloudwalkBankCardOCRActivity extends Activity implements Delegate, BankCardCallback {

    public CwProgressHUD processDialog;
    private Dialog mDialog;

    private static final int CANCEL_FOCUS = 0,UP_LOADING = 1,DRAW_LINE = 2;
    AutoFocusCameraPreview mAutoFoucsCameraPreview;
    OcrMaskView maskView;
    ImageView mIv_idrect;

    int ocr_flag = Contants.OCR_FLAG_BANKCARD;

    public BankOcrSDK bankOcrSDK;
    private BankCardInfo bankCardInfo;
    int initRet = -1;
    String faceappid;//
    String bankserver;// 服务器地址
    String licence;
    Bitmap bmpCanLine;//扫描线
    Bitmap bmpfocus;
    Bitmap bmpfocused;
    final String OutJpgName = "bankcard.jpg";//保存的图片名
    boolean mAutoRatio;
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == CANCEL_FOCUS) {
                maskView.clearFocus();
            }else if (msg.what == UP_LOADING){
                mHandler.removeCallbacksAndMessages(null);
                cwUpLoaing(mBitmap);
            }else if (msg.what == DRAW_LINE){
                cwDrawLine();
            }
            super.handleMessage(msg);
        }
    };

    public void cwDrawLine(){
        maskView.setLine(bankCardInfo.left, bankCardInfo.top, bankCardInfo.right, bankCardInfo.bottom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAutoRatio = getIntent().getBooleanExtra("BANKCARD_AUTO_RATIO",false);//是否支持竖版银行卡识别
        if (!mAutoRatio){
            setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);//不支持竖版银行卡则强制横屏显示
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.cloudwalk_activity_bankocr);

        licence = getIntent().getStringExtra("LICENCE");
        bankserver = getIntent().getStringExtra("BANKSERVER");
        faceappid = getIntent().getStringExtra("FACEAPPID");
        initView();
        initSDK();
        bmpCanLine = BitmapFactory.decodeResource(getResources(), R.drawable.scan_line);
        bmpfocus = BitmapFactory.decodeResource(getResources(), R.drawable.focus);
        bmpfocused = BitmapFactory.decodeResource(getResources(), R.drawable.focused);
        mAutoFoucsCameraPreview.setAutoRatio(mAutoRatio);
        Point point = getScreenSize();
        mAutoFoucsCameraPreview.setScreenSize(point.x,point.y);
        mAutoFoucsCameraPreview.setFlag(ocr_flag);
        mAutoFoucsCameraPreview.setSizeCallback(new AutoFocusCameraPreview.SizeCallback() {
            @Override
            public void onSizeChange(int width, int height, final int ocrRectW, final int ocrRectH) {
                maskView.setOcr(width, height, ocrRectW, ocrRectH, ocr_flag, bmpCanLine, bmpfocus, bmpfocused);
            }
        });

        deleteCachedJpg();

        processDialog = CwProgressHUD.create(this).setStyle(CwProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("正在识别中...").setCancellable(true).setAnimationSpeed(2)
                .setCancellable(false).setDimAmount(0.5f);

    }

    protected Point getScreenSize() {
        int realWidth = 0, realHeight = 0;
        Display display = this.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        realWidth = metrics.widthPixels;
        realHeight = metrics.heightPixels;
        try {
            if (android.os.Build.VERSION.SDK_INT >= 17) {
                Point size = new Point();
                display.getRealSize(size);
                realWidth = size.x;
                realHeight = size.y;
            } else if (android.os.Build.VERSION.SDK_INT < 17
                    && android.os.Build.VERSION.SDK_INT >= 14) {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Point(realWidth,realHeight);
    }


    /**
     * 注册接口
     */
    private void initCallback() {
        bankOcrSDK.cwBankCardCallback(this);
        mAutoFoucsCameraPreview.setDelegate(this);
    }

    /**
     * 初始化SDK
     */
    private void initSDK() {
        bankOcrSDK = BankOcrSDK.getInstance(this/*,ocr_flag*/);
        if (0!=initRet)
            initRet = bankOcrSDK.cwCreateCardHandle(licence);
        if (initRet != 0) {
            mDialog = new AlertDialog.Builder(this).setMessage("初始化失败，授权码无效")
                    .setNegativeButton("确定", new AlertDialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.dismiss();
                        }
                    }).show();
        }
    }

    /**
     * 初始化相关参数
     */
    private void initView() {
        mAutoFoucsCameraPreview = (AutoFocusCameraPreview) findViewById(R.id.CameraPreview);
        maskView = (OcrMaskView) findViewById(R.id.maskView);
        mIv_idrect = (ImageView) findViewById(R.id.iv_idrect);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCallback();
        mAutoFoucsCameraPreview.cwStartCamera();
   }

    @Override
    protected void onStop() {
        super.onStop();
        mAutoFoucsCameraPreview.cwStopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bankOcrSDK.cwDestroyCardHandle();
        if (mDialog != null) {
            mDialog.dismiss();
        }
        if (null!=mBitmap && ! mBitmap.isRecycled()) {
            mBitmap.recycle();
            mBitmap = null;
        }
        if (bmpCanLine!=null && !bmpCanLine.isRecycled()){
            bmpCanLine.recycle();
        }
        if (bmpfocus!=null && !bmpfocus.isRecycled()){
            bmpfocus.recycle();
        }
        if (bmpfocused!=null && !bmpfocused.isRecycled()){
            bmpfocused.recycle();
        }
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.dismiss();
        }
    }

    @Override
    public void onOpenCameraError() {

    }

    @Override
    public void onFocus(float x, float y) {
        maskView.setFocus(x, y);
    }

    @Override
    public void onFocused() {
        maskView.setFocused();
        mHandler.sendEmptyMessageDelayed(CANCEL_FOCUS, 150);
    }

    @Override
    public void BankCardInfo(BankCardInfo bankCardInfo) {
        this.bankCardInfo = bankCardInfo;
        mHandler.sendEmptyMessage(DRAW_LINE);
    }

    Bitmap mBitmap = null;
    @Override
    public void BankCardAlignData(byte[] data,int infoWidth,int infoHeight) {
        if (null!=mBitmap)
            return;

        mBitmap = ImgUtil.byteArrayBGRToBitmap(data, infoWidth, infoHeight);
        mHandler.sendEmptyMessage(UP_LOADING);
    }

    private void cwUpLoaing(final Bitmap bitmap) {
        mAutoFoucsCameraPreview.stopCameraPreview();

        deleteCachedJpg();//删除缓存图片

        processDialog.setLabel(getString(R.string.bank_loading)).show();
        HttpManager.cwBankOcr(bankserver, faceappid, faceappid, ImgUtil.bitmapToByte(bitmap, Bitmap.CompressFormat.JPEG, 100), new HttpManager.DataCallBack() {
            @Override
            public void requestFailure(String errorMsg) {
                if (processDialog != null && processDialog.isShowing())
                    processDialog.dismiss();
                mAutoFoucsCameraPreview.showCameraPreview();

                if (null!=mBitmap && ! mBitmap.isRecycled()) {
                    mBitmap.recycle();
                    mBitmap = null;
                }

                Toast.makeText(CloudwalkBankCardOCRActivity.this, TextUtils.isEmpty(errorMsg)?"网络异常,请检查网络":errorMsg, Toast.LENGTH_SHORT).show();//提示
            }

                @Override
                public void requestSucess(JSONObject jb) {
                    if (processDialog != null && processDialog.isShowing())
                        processDialog.dismiss();
                    final String number = jb.optString("CardNum");
                    final String vissuer = jb.optString("BankName");
                    final String cardName = jb.optString("CardName");
                    final String cardType = jb.optString("CardType");

                if (vissuer.equals("unknown")){
                    mAutoFoucsCameraPreview.showCameraPreview();

                    if (null!=mBitmap && ! mBitmap.isRecycled()) {
                        mBitmap.recycle();
                        mBitmap = null;
                    }
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String path = Util.getDiskCacheDir(CloudwalkBankCardOCRActivity.this) + "/" + OutJpgName;
                        ImgUtil.saveJPGE_After(bitmap,path,100);//银行卡图片保存

                        if (null!=mBitmap && ! mBitmap.isRecycled()) {
                            mBitmap.recycle();
                            mBitmap = null;
                        }

                            final Intent mIntent = new Intent(CloudwalkBankCardOCRActivity.this, BankCardResultActivity.class);
                            mIntent.putExtra("CardNum", number);
                            mIntent.putExtra("BankName", vissuer);
                            mIntent.putExtra("CardName", cardName);
                            mIntent.putExtra("CardType", cardType);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(mIntent);
                                    finish();
                                }
                            });

                        }
                    }).start();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    protected void deleteCachedJpg() {
        try {
            String path = Util.getDiskCacheDir(CloudwalkBankCardOCRActivity.this) + "/" + OutJpgName;
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
