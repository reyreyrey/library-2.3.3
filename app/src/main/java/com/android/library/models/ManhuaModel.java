package com.android.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * author: Rea.x
 * date: 2017/12/5.
 */

public class ManhuaModel implements Serializable {


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

    public static class ShowapiResBodyBean implements Serializable {

        @SerializedName("pagebean")
        private PagebeanBean pagebean;
        @SerializedName("ret_code")
        private int ret_code;

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public static class PagebeanBean implements Serializable {
            @SerializedName("currentPage")
            private int currentPage;
            @SerializedName("hasMorePage")
            private boolean hasMorePage;
            @SerializedName("maxResult")
            private String maxResult;
            @SerializedName("contentlist")
            private List<ContentlistBean> contentlist;

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public boolean isHasMorePage() {
                return hasMorePage;
            }

            public void setHasMorePage(boolean hasMorePage) {
                this.hasMorePage = hasMorePage;
            }

            public String getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(String maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean implements Serializable {

                @SerializedName("id")
                private String id;
                @SerializedName("link")
                private String link;
                @SerializedName("time")
                private String time;
                @SerializedName("title")
                private String title;
                @SerializedName("thumbnailList")
                private List<String> thumbnailList;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<String> getThumbnailList() {
                    return thumbnailList;
                }

                public void setThumbnailList(List<String> thumbnailList) {
                    this.thumbnailList = thumbnailList;
                }
            }
        }
    }
}
