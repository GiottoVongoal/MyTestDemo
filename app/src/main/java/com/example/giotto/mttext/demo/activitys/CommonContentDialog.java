package com.example.giotto.mttext.demo.activitys;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.utils.InterfaceManager;

/**
 * @author shaoshuai.
 * @PackageName com.cheyipai.ui.common.view.dialog
 * @date 2016-11-07 15:16
 * @description 内容布局可变的对话框
 */
public class CommonContentDialog extends Dialog {

    public CommonContentDialog(Context context) {
        super(context);
    }

    public CommonContentDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CommonContentDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context mContext;
        private String mTitle, leftString, rightString;
        private int mResId;
        private boolean mCancelOnTouch = false;
        private View.OnClickListener mLeftBtnOnClickListener;
        private View.OnClickListener mRightBtnOnClickListener;
        private InterfaceManager.CallBackView mCallBackView;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }
        public Builder setLeftString(String leftString) {
            this.leftString = leftString;
            return this;
        }

        public Builder setRightString(String rightString) {
            this.rightString = rightString;
            return this;
        }

        public Builder setCancelOnTouch(boolean cancelOnTouch) {
            this.mCancelOnTouch = cancelOnTouch;
            return this;
        }

        public Builder setLeftBtnOnClickListener(View.OnClickListener leftBtnOnClickListener) {
            this.mLeftBtnOnClickListener = leftBtnOnClickListener;
            return this;
        }

        public Builder setRightBtnOnClickListener(View.OnClickListener rightBtnOnClickListener) {
            this.mRightBtnOnClickListener = rightBtnOnClickListener;
            return this;
        }

        public Builder setContent(int resId) {
            this.mResId = resId;
            return this;
        }
        public Builder setViewInterface(InterfaceManager.CallBackView callBackView) {
            this.mCallBackView = callBackView;
            return this;
        }

        public CommonContentDialog build() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.common_content_dialog_layout, null);
            LinearLayout content_dialog_ll_title = (LinearLayout) view.findViewById(R.id.content_dialog_ll_title);
            TextView titleTv = (TextView) view.findViewById(R.id.content_dialog_tv_title);
            LinearLayout content_dialog_ll_content = (LinearLayout) view.findViewById(R.id.content_dialog_ll_content);
            TextView content_dialog_tv_left = (TextView) view.findViewById(R.id.content_dialog_tv_left);
            View content_dialog_tv_line = (View) view.findViewById(R.id.content_dialog_tv_line);//按钮中间的分界线
            TextView content_dialog_tv_right = (TextView) view.findViewById(R.id.content_dialog_tv_right);
            final CommonContentDialog mContentDialog = new CommonContentDialog(mContext, R.style.dialog);

            if (TextUtils.isEmpty(mTitle)) {
                content_dialog_ll_title.setVisibility(View.GONE);
            } else {
                content_dialog_ll_title.setVisibility(View.VISIBLE);
                titleTv.setText(mTitle);
            }
            if (mResId > 0) {
                View contentView = LayoutInflater.from(mContext).inflate(mResId, null);
                content_dialog_ll_content.addView(contentView);
                if (mCallBackView != null) {
                    mCallBackView.getCallBackView(content_dialog_ll_content);
                }
            }
            if (mLeftBtnOnClickListener != null) {
                content_dialog_tv_left.setText(leftString);
                content_dialog_tv_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mLeftBtnOnClickListener != null)
                            mLeftBtnOnClickListener.onClick(v);
                        if (mContentDialog != null) {
                            mContentDialog.dismiss();
                        }
                    }
                });
            } else {
                content_dialog_tv_left.setVisibility(View.GONE);
                content_dialog_tv_line.setVisibility(View.GONE);
            }

            if (mRightBtnOnClickListener != null) {
                content_dialog_tv_right.setText(rightString);
                content_dialog_tv_right.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (mRightBtnOnClickListener != null)
                            mRightBtnOnClickListener.onClick(v);
                        if (mContentDialog != null) {
                            mContentDialog.dismiss();
                        }
                    }
                });
            } else {
                content_dialog_tv_right.setVisibility(View.GONE);
                content_dialog_tv_line.setVisibility(View.GONE);
            }

//            // 测量文本 text 所占的长度
//            float tWidth = getTextPaint().measureText(content);//单位为px
//            if (tWidth > 260) {
//                //多余260就标示要显示2行,左对齐显示
//                content_dialog_tv_content.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
//            } else {
//                //否则显示单行,居中显示
//                content_dialog_tv_content.setGravity(Gravity.CENTER);
//            }
//            content_dialog_tv_content.setText(content);
            mContentDialog.setContentView(view);
            mContentDialog.setCanceledOnTouchOutside(mCancelOnTouch);// 设置点击屏幕Dialog不消失
            return mContentDialog;
        }

        public Paint getTextPaint() {
            Paint mTextPaint = new Paint();
            Paint.Style style = mTextPaint.getStyle();
            mTextPaint.setStyle(style);
            mTextPaint.setColor(mContext.getResources().getColor(R.color.black3));
            mTextPaint.setAntiAlias(true); // 非锯齿效果
            return mTextPaint;
        }
    }
}
