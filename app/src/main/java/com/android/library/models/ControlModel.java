package com.android.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * author: Rea.X
 * date: 2018/1/10.
 */

public class ControlModel implements Serializable {
    private static final long serialVersionUID = -8017624805432391840L;

    @SerializedName("url")
    private String url;
    @SerializedName("open")
    private boolean open;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
