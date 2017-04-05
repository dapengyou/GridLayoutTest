package com.gridlayouttest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.TextView;

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


    //给眼睛设置图标
    private Drawable[] drawables;
    private int eyeWidth;
    private Drawable drawableEyeOpen;

    private PasswordEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialog();
        button = (Button) findViewById(R.id.bt_button);
        tv = (TextView) findViewById(R.id.text);
        et = (EditText) findViewById(R.id.et_pwd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interestDialog != null) {
                    interestDialog.show();//显示对话框
                }
            }
        });
        drawables = et.getCompoundDrawables();
        eyeWidth = drawables[2].getBounds().width();// 眼睛图标的宽度
        drawableEyeOpen = getResources().getDrawable(R.drawable.ic_eyes_open);

        drawableEyeOpen.setBounds(drawables[2].getBounds());//这一步不能省略

         et.setOnTouchListener(eyesClickListener);

    }

    View.OnTouchListener eyesClickListener = new View.OnTouchListener() {
        private boolean isHidePwd = true;// 输入框密码是否是隐藏的，默认为true

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // getWidth,getHeight必须在这里处理
                float et_pwdMinX = v.getWidth() - eyeWidth - et.getPaddingRight();
                float et_pwdMaxX = v.getWidth();
                float et_pwdMinY = 0;
                float et_pwdMaxY = v.getHeight();
                float x = event.getX();
                float y = event.getY();
                if (x < et_pwdMaxX && x > et_pwdMinX && y > et_pwdMinY && y < et_pwdMaxY) {
                    if (isHidePwd) {
                        et.setCompoundDrawables(null, null, drawableEyeOpen, null);
                        //隐藏密码
                        et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        isHidePwd = false;
                    } else {
                        et.setCompoundDrawables(null, null, drawables[2], null);
                        //显示密码
                        et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        isHidePwd = true;
                    }
                }
            }
            return false;
        }
    };

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
