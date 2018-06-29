package com.example.giotto.mttext.demo.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.io.Serializable;
import java.security.MessageDigest;

/**
 * @author SHAOS.
 * @PackageName com.example.giotto.mttext.demo.utils
 * @date 2016-09-14 11:31
 * @description
 */
public class MyUtils {
    Context context;

    public MyUtils(Context context) {
        this.context = context;
    }

    public static void intentStartActivity(Activity fromActivity,
                                           Class<?> toActivity) {
        Intent loIntent = new Intent(fromActivity, toActivity);
        fromActivity.startActivity(loIntent);
    }

    /**
     * intent传值,可传递简单值和模型类
     */
    public static <T> void intentStartActivity(Activity fromActivity, Class<T> activity, Serializable checkOutUpOrderMode) {
        Intent intent = new Intent(fromActivity, activity);
        intent.putExtra(activity.getSimpleName(), checkOutUpOrderMode);
        fromActivity.startActivity(intent);
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str))
            return true;
        else return false;
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * @param num
     * @return
     * @Description: 检验手机号格式是否正确(1开头, 11位)
     */
    public static boolean checkPhoneNum(String num) {
        return num.matches("^1\\d{10}$");
    }

    /**
     * MD5加密
     */
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 微信是否安装
     *
     * @param context
     * @param api
     * @return
     */
    public static boolean isWechatAppInstalledAndSupported(Context context, IWXAPI api) {
        boolean isWechatAppInstalledAndSupported = api.isWXAppInstalled() && api.isWXAppSupportAPI();
        return isWechatAppInstalledAndSupported;
    }

    /**
     * @param context
     * @param message
     * @Method showToast
     * @Description 构建小提示对话框
     */
    public final static void showToast(final Context context,
                                       final String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public Dialog showdialog;

    public void disDialog() {
        showdialog.dismiss();
    }

    /**
     * @param context
     * @param message
     * @Title: showToastLong
     * @Description: 构建长时间提示Toast
     * @return: void
     */
    public final static void showLongToast(final Context context,
                                           final String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static int getScollYDistance(LinearLayoutManager linearLayoutManager) {
        int position = linearLayoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = linearLayoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void verifyStoragePermissions(Activity activity) {
// Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
// We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    /**
     * @Description 输入框增加一键删除按钮, 多个输入框都输入完整按钮颜色变化效果
     * @param context
     * @param myEditText      当前监听的EditText
     * @param edittext        所有要监听的EditText数组
     * @param delete          当前监听的EditText对应的删除图标
     * @param onClickListener 删除图标的点击事件(把对应的EditText.setText(""))
     * @param btn             监听的可点击的变化按钮
     * @param layoutIdEmpty   按钮的变化效果(为空图片)
     * @param layoutIdEnable  按钮的变化效果(不为空图片)
     * @return
     */
    private static boolean isFocus;

    public static EditText setEdittext(Context context,
                                       final EditText myEditText, final EditText[] edittext,
                                       final ImageView delete, final View.OnClickListener onClickListener,
                                       final TextView btn, final int layoutIdEmpty, final int layoutIdEnable) {
        isFocus = false;
        myEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isFocus = true;
                    if (!TextUtils.isEmpty(myEditText.getText())) {
                        delete.setVisibility(View.VISIBLE);
                    }
                } else {
                    isFocus = false;
                    delete.setVisibility(View.INVISIBLE);
                }
            }
        });
        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (isFocus && !TextUtils.isEmpty(s)) {
                    delete.setVisibility(View.VISIBLE);
                } else {
                    delete.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //都不为空时设置按钮样式
                boolean isEmpty = false;
                for (int i = 0; i < edittext.length; i++) {
                    int itest = edittext[i].getId();
                    if (TextUtils.isEmpty(edittext[i].getText())) {
                        isEmpty = true;
                    }
                    if (isEmpty) {
                        break;
                    }
                }
                if (isEmpty) {
                    btn.setEnabled(false);
                    btn.setBackgroundResource(layoutIdEmpty);
                } else {
                    btn.setEnabled(true);
                    btn.setBackgroundResource(layoutIdEnable);
                }
            }
        });
        delete.setOnClickListener(onClickListener);
        return myEditText;
    }


    /**
     * @Description 输入框增加一键删除按钮, 监听输入框输入的为手机号时按钮变亮
     * @param context
     * @param myEditText        当前监听的EditText
     * @param edittext          所有要监听的EditText数组
     * @param delete            当前监听的EditText对应的删除图标
     * @param onClickListener   删除图标的点击事件(把对应的EditText.setText(""))
     * @param btn               监听的可点击的变化按钮
     * @param layoutIdEmpty     按钮的变化效果(为空图片)
     * @param layoutIdEnable    按钮的变化效果(不为空图片)
     * @return
     */
    private static boolean isFocus_phone;

    public static EditText setPhoneIstrueEditText(final Context context,
                                                  final EditText myEditText, final ImageView delete, final View.OnClickListener onClickListener,
                                                  final TextView btn, final int layoutIdEmpty, final int layoutIdEnable) {
        isFocus_phone = false;
        myEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isFocus_phone = true;
                    if (!TextUtils.isEmpty(myEditText.getText())) {
                        delete.setVisibility(View.VISIBLE);
                    }
                } else {
                    isFocus_phone = false;
                    delete.setVisibility(View.GONE);
                }
            }
        });
        myEditText.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before,
                                                                        int count) {
                                                  if (isFocus_phone && !TextUtils.isEmpty(s)) {
                                                      delete.setVisibility(View.VISIBLE);
                                                  } else {
                                                      delete.setVisibility(View.GONE);
                                                  }
                                              }

                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count,
                                                                            int after) {
                                              }

                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  if (TextUtils.isEmpty(myEditText.getText().toString())) {
                                                      // 为空
                                                      btn.setBackgroundResource(layoutIdEmpty);
                                                  } else {
                                                      // 不为空
                                                      if (MyUtils.checkPhoneNum(myEditText.getText().toString())) {
                                                          // 符合手机号格式
                                                          btn.setBackgroundResource(layoutIdEnable);
                                                      } else {
                                                          // 不符合手机号格式
                                                          btn.setBackgroundResource(layoutIdEmpty);
                                                      }
                                                  }
                                              }
                                          }

        );
        delete.setOnClickListener(onClickListener);
        return myEditText;
    }


}
