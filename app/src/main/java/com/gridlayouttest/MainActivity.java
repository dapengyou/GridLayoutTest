package com.gridlayouttest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.gridlayouttest.ui.RecycleViewActivity;
import com.gridlayouttest.util.ConstellationUtil;
import com.gridlayouttest.util.CountDownShowHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    private Button mBtRecycle;

    private RadioGroup mRgSex;      //性别
    private RadioButton mRbSex;

    private ImageView imageView;    //剪裁图像
    private ImagePicker imagePicker;

    private TextView mTvDate;   //日期
    private TextView mTvConstellation;  //星座

    private TimePickerView pickerView;  //时间picker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialog();
        initTimePicker();
        //imagePicker初始化
        imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(false);

        button = (Button) findViewById(R.id.bt_button);
        tv = (TextView) findViewById(R.id.text);
        et = (EditText) findViewById(R.id.et_pwd);
        captch = (Button) findViewById(R.id.bt_captcha);
        imageView = (ImageView) findViewById(R.id.image);
        mTvDate = (TextView) findViewById(R.id.textView);
        mTvConstellation = (TextView) findViewById(R.id.tv_constellation);
        mBtRecycle = (Button) findViewById(R.id.button);

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
        imageView.setOnClickListener(imageClickListener);

        mTvDate.setOnClickListener(this);
        mTvConstellation.setOnClickListener(this);
        mBtRecycle.setOnClickListener(this);

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

    View.OnClickListener imageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
            startActivityForResult(intent, 100);
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

    //imagepicker回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.get(0) != null) {
                    imagePicker.getImageLoader().displayImage(MainActivity.this, images.get(0).path, imageView, 200, 200);
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                if (pickerView != null) {
                    pickerView.show();
                }
                break;
            case R.id.button:
                Intent intent = new Intent(this, RecycleViewActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.YEAR, endDate.MONTH, endDate.DATE);
        //时间选择器
        pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                mTvDate.setText(getTime(date));
                int month = getMonth(date);
                int day = getDay(date);
                mTvConstellation.setText(ConstellationUtil.getConstellation(month,day));
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setCancelText("取消")
                .setSubmitText("确定")
                .build();
    }

    private int getDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String dayString = format.format(date);
        int dayInteger = Integer.parseInt(dayString);
//        Log.d("month",monthInteger+"");
        return dayInteger;
    }

    private int getMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
       String monthString = format.format(date);
        int monthInteger = Integer.parseInt(monthString);
//        Log.d("month",monthInteger+"");
        return monthInteger;
    }


    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


}
