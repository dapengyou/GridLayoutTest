package com.gridlayouttest.weight;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.gridlayouttest.R;

/**
 * Created by lady_zhou on 2017/4/5.
 */

public class PasswordEditText extends android.support.v7.widget.AppCompatEditText {
    //给眼睛设置图标
    private Drawable[] drawables;
    private int eyeWidth;
    private Drawable drawableEyeOpen;
    private Context mContext;


    public PasswordEditText(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        drawables = getCompoundDrawables();
        eyeWidth = drawables[2].getBounds().width();// 眼睛图标的宽度
        drawableEyeOpen = getResources().getDrawable(R.drawable.ic_eyes_open);
        drawableEyeOpen.setBounds(drawables[2].getBounds());//这一步不能省略
    }



    public boolean onTouchEvent(MotionEvent event) {
        boolean isHidePwd = true;// 输入框密码是否是隐藏的，默认为true
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // getWidth,getHeight必须在这里处理
            float et_pwdMinX = this.getWidth() - eyeWidth - this.getPaddingRight();
            float et_pwdMaxX = this.getWidth();
            float et_pwdMinY = 0;
            float et_pwdMaxY = this.getHeight();
            float x = event.getX();
            float y = event.getY();
            if (x < et_pwdMaxX && x > et_pwdMinX && y > et_pwdMinY && y < et_pwdMaxY) {
                if (isHidePwd) {
                    this.setCompoundDrawables(null, null, drawableEyeOpen, null);
                    //隐藏密码
                    this.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHidePwd = false;
                }else{
                    this.setCompoundDrawables(null, null, drawables[2], null);
                    //显示密码
                    this.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHidePwd = true;
                }
            }
        }
        return super.onTouchEvent(event);
    }


}
