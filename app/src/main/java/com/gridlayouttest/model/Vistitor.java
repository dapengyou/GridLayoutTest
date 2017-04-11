package com.gridlayouttest.model;

import android.media.Image;

/**
 * 访问者
 * <p>
 * Created by lady_zhou on 2017/4/11.
 */

public class Vistitor {
    private String userImage;
    private String nickName;
    private String visitorTime;

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVisitorTime() {
        return visitorTime;
    }

    public void setVisitorTime(String visitorTime) {
        this.visitorTime = visitorTime;
    }
}
