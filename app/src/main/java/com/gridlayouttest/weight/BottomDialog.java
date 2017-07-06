package com.gridlayouttest.weight;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gridlayouttest.R;

import java.util.List;


/**
 * 底部弹框
 * Created by lady_zhou on 2017/7/4.
 */

public class BottomDialog extends Dialog implements View.OnClickListener {
    private TextView mTitleTv;
    private TextView mOneTv;
    private TextView mTwoTv;
    private TextView mCancelTv;
    private Dialog myDialog;
    TitleClick titleClick;
    public interface TitleClick {
        public void titleClick();
    }

    public BottomDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public BottomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }

    protected BottomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        myDialog = new Dialog(getContext(), R.style.BottomDialogStyle);
        //填充对话框的布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bottom, null);
        //初始化控件
        mTitleTv = (TextView) view.findViewById(R.id.tv_dialog_title);
        mOneTv = (TextView) view.findViewById(R.id.tv_dialog_one);
        mTwoTv = (TextView) view.findViewById(R.id.tv_dialog_two);
        mCancelTv = (TextView) view.findViewById(R.id.tv_dialog_cancel);
        mTitleTv.setOnClickListener(this);
        mOneTv.setOnClickListener(this);
        mTwoTv.setOnClickListener(this);
        mCancelTv.setOnClickListener(this);
        //将布局设置给Dialog
        myDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = myDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (dialogWindow.getWindowManager().getDefaultDisplay().getWidth() * 0.95);
        lp.y = 20; //设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp); //将属性设置给窗体
        myDialog.show();//显示对话框
    }

    /**
     * 点击第一个TextView
     *
     * @param oneTv
     */
    public void onOneClick(View oneTv) {
    }

    /**
     * 点击第二个TextView
     *
     * @param twoTv
     */
    public void onTwoClick(View twoTv) {
    }

    /**
     * 点击titleTextView
     *
     * @param titleTv
     */
    public void onTitleClick(View titleTv) {
//        Toast.makeText(getContext(),"标题",Toast.LENGTH_SHORT).show();
        titleClickCallBack(titleClick);
    }

    public void titleClickCallBack(TitleClick titleClick) {
//        Toast.makeText(getContext(),"标题",Toast.LENGTH_SHORT).show();
        titleClick.titleClick();
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_title:
                onTitleClick(v);
                break;
            case R.id.tv_dialog_one:
                onOneClick(v);
                break;
            case R.id.tv_dialog_two:
                onTwoClick(v);
                break;
            case R.id.tv_dialog_cancel:
                myDialog.dismiss();
                break;
        }
    }

    /**
     * 设置标题栏文本文字
     *
     * @param stringId
     * @see #setTitleText(String)
     */
    public void setTitleText(@StringRes int stringId) {
        setTitleText(getContext().getString(stringId));
    }

    /**
     * 设置标题栏文本文字
     *
     * @param text
     */
    public void setTitleText(String text) {
        mTitleTv.setText(text);
        mTitleTv.setVisibility(View.VISIBLE);
        mTitleTv.setOnClickListener(this);
    }

    /**
     * 设置第一个TextView文字
     *
     * @param stringId
     */
    public void setOneText(@StringRes int stringId) {
        setOneText(getContext().getString(stringId));
    }

    /**
     * 设置第一个TextView文字
     *
     * @param text
     */
    public void setOneText(String text) {
        mOneTv.setText(text);
        mOneTv.setVisibility(View.VISIBLE);
        mOneTv.setOnClickListener(this);
    }

    /**
     * 设置第二个TextView文字
     *
     * @param stringId
     */
    public void setTwoText(@StringRes int stringId) {
        setTwoText(getContext().getString(stringId));
    }

    /**
     * 设置第二个TextView文字
     *
     * @param text
     */
    public void setTwoText(String text) {
        mTwoTv.setText(text);
        mTwoTv.setVisibility(View.VISIBLE);
        mTwoTv.setOnClickListener(this);
    }

    /**
     * 获取标题栏文本
     *
     * @return
     */
    public final TextView getTitleTv() {
        return mTitleTv;
    }

    /**
     * 获取第一个文本
     *
     * @return
     */
    public final TextView getOneTv() {
        return mOneTv;
    }

    /**
     * 获取第二个文本
     *
     * @return
     */
    public final TextView getTwoTv() {
        return mTwoTv;
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }
}
