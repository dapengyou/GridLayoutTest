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
