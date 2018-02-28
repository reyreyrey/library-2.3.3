package com.android.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wiki on 2018/1/22.
 */

public class SystemNotify implements Serializable{

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("time")
    private String time;
    @SerializedName("isRead")
    private boolean isRead;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}
