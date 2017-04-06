package com.gridlayouttest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gridlayouttest.util.CountDownShowHelper;
import com.gridlayouttest.weight.PasswordEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> interestList = new ArrayList<String>() {{
        add("唱歌");
        add("看书");
        add("旅游");
        add("美食");
        add("骑行");
        add("电影");
    }};
    private List<String> list;
    private Button button;
    private Dialog interestDialog;
    private TextView tv;
    private EditText et;
    private Button captch;

    private RadioGroup mRgSex;      //性别
    private RadioButton mRbSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialog();

        button = (Button) findViewById(R.id.bt_button);
        tv = (TextView) findViewById(R.id.text);
        et = (EditText) findViewById(R.id.et_pwd);
        captch = (Button) findViewById(R.id.bt_captcha);

        //性别
        mRgSex = (RadioGroup) findViewById(R.id.rg_sex);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interestDialog != null) {
                    interestDialog.show();//显示对话框
                }
            }
        });


        captch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownShowHelper cd = new CountDownShowHelper(captch, "获取验证码", "%ds后重新发送");
                cd.onFinish();
                cd.start();
            }
        });

        mRgSex.setOnCheckedChangeListener(sexCheckedListener);

    }

    RadioGroup.OnCheckedChangeListener sexCheckedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            //获取变更后的选中项的ID
            int radioButtonId = group.getCheckedRadioButtonId();
            mRbSex = (RadioButton) MainActivity.this.findViewById(radioButtonId);
            //更新文本内容，以符合选中项
            Toast.makeText(MainActivity.this, "您的性别是：" + mRbSex.getText(), Toast.LENGTH_SHORT).show();
        }
    };


    private void initCaptch() {
        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                captch.setText("获取验证码" + "(" + ((millisUntilFinished + 15) / 1000)
                        + "秒)");
            }

            @Override
            public void onFinish() {
                captch.setText("获取验证码");
                Toast.makeText(MainActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private void initDialog() {
        GridLayout grild = new GridLayout(this);   //兴趣Layout
        list = new ArrayList<>();
        // grild = (GridLayout) findViewById(R.id.gl_interest);
        int size = interestList.size(); //得出list数组的个数
        int rowCount = size / 3;    //每行显示3个
        grild.setRowCount(rowCount);    //设置行和列
        grild.setColumnCount(3);
        for (String interest : interestList) {
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setText(interest);
            //checkBox设置背景
            //checkBox.setBackgroundResource(R.drawable.ic_interest_disable);
            //checkBox设置监听
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        list.add(buttonView.getText().toString().trim());
                    }
                }
            });

            grild.addView(checkBox);
        }
        interestDialog = new AlertDialog.Builder(this).setView(grild)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv.setText(list.toString());
                    }
                })
                //取消按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create();//创建对话框
    }

}
