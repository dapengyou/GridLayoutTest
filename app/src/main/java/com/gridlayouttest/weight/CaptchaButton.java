package com.gridlayouttest.weight;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;

/**
 * 自定义验证码按钮
 * Created by lady_zhou on 2017/4/5.
 */

public class CaptchaButton extends android.support.v7.widget.AppCompatButton{

    public CaptchaButton(Context context) {
        super(context);
        init();
    }

    public CaptchaButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CaptchaButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        CountDownTimer countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };
    }

}
