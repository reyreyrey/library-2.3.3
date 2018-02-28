package com.android.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * author: Rea.X
 * date: 2018/1/5.
 */

public class FuliModel implements Serializable{

    @SerializedName("showapi_res_code")
    private int showapi_res_code;
    @SerializedName("showapi_res_error")
    private String showapi_res_error;
    @SerializedName("showapi_res_body")
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean implements Serializable{

        @SerializedName("total")
        private int total;
        @SerializedName("ret_code")
        private int ret_code;
        @SerializedName("ret_message")
        private String ret_message;
        @SerializedName("data")
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getRet_message() {
            return ret_message;
        }

        public void setRet_message(String ret_message) {
            this.ret_message = ret_message;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{

            @SerializedName("id")
            private int id;
            @SerializedName("title")
            private String title;
            @SerializedName("ctime")
            private int ctime;
            @SerializedName("imgcount")
            private int imgcount;
            @SerializedName("imgurl")
            private String imgurl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public int getImgcount() {
                return imgcount;
            }

            public void setImgcount(int imgcount) {
                this.imgcount = imgcount;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }
        }
    }
}
