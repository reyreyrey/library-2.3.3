package com.android.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wiki on 2018/1/17.
 */

public class PostDetailModel implements Serializable{

    @SerializedName("userStar")
    private boolean userStar;
    @SerializedName("userSave")
    private boolean userSave;
    @SerializedName("content")
    private String content;

    public boolean isUserStar() {
        return userStar;
    }

    public void setUserStar(boolean userStar) {
        this.userStar = userStar;
    }

    public boolean isUserSave() {
        return userSave;
    }

    public void setUserSave(boolean userSave) {
        this.userSave = userSave;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
