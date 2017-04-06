package com.gridlayouttest.weight;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.Toast;


/**
 * 自定义验证码按钮
 * Created by lady_zhou on 2017/4/5.
 */

public class CaptchaButton extends android.support.v7.widget.AppCompatButton{
private Context mContext;
    public CaptchaButton(Context context) {
        super(context);
        mContext = context;
    }

    public CaptchaButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CaptchaButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }
    private void init(final Button button){
        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                button.setText("获取验证码" + "(" + ((millisUntilFinished + 15) / 1000)
                        + "秒)");
            }

            @Override
            public void onFinish() {
                button.setText("获取验证码");
                Toast.makeText(mContext, "获取验证码失败", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

}
