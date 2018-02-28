package com.android.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * author: Rea.X
 * date: 2018/1/3.
 */

public class ArtListModel implements Serializable{

    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{

        @SerializedName("totalViewNums")
        private int totalViewNums;
        @SerializedName("worksSrc")
        private String worksSrc;
        @SerializedName("exhibitArtistList")
        private List<ExhibitArtistListBean> exhibitArtistList;

        public int getTotalViewNums() {
            return totalViewNums;
        }

        public void setTotalViewNums(int totalViewNums) {
            this.totalViewNums = totalViewNums;
        }

        public String getWorksSrc() {
            return worksSrc;
        }

        public void setWorksSrc(String worksSrc) {
            this.worksSrc = worksSrc;
        }

        public List<ExhibitArtistListBean> getExhibitArtistList() {
            return exhibitArtistList;
        }

        public void setExhibitArtistList(List<ExhibitArtistListBean> exhibitArtistList) {
            this.exhibitArtistList = exhibitArtistList;
        }

        public static class ExhibitArtistListBean  implements Serializable{

            @SerializedName("author")
            private String author;
            @SerializedName("exhibitCity")
            private String exhibitCity;
            @SerializedName("exhibitEndDate")
            private String exhibitEndDate;
            @SerializedName("exhibitStartDate")
            private String exhibitStartDate;
            @SerializedName("galleryId")
            private int galleryId;
            @SerializedName("galleryName")
            private String galleryName;
            @SerializedName("goodTimes")
            private int goodTimes;
            @SerializedName("headPic")
            private String headPic;
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("worksHeight")
            private String worksHeight;
            @SerializedName("worksId")
            private int worksId;
            @SerializedName("worksName")
            private String worksName;
            @SerializedName("worksPic")
            private String worksPic;
            @SerializedName("worksWidth")
            private String worksWidth;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getExhibitCity() {
                return exhibitCity;
            }

            public void setExhibitCity(String exhibitCity) {
                this.exhibitCity = exhibitCity;
            }

            public String getExhibitEndDate() {
                return exhibitEndDate;
            }

            public void setExhibitEndDate(String exhibitEndDate) {
                this.exhibitEndDate = exhibitEndDate;
            }

            public String getExhibitStartDate() {
                return exhibitStartDate;
            }

            public void setExhibitStartDate(String exhibitStartDate) {
                this.exhibitStartDate = exhibitStartDate;
            }

            public int getGalleryId() {
                return galleryId;
            }

            public void setGalleryId(int galleryId) {
                this.galleryId = galleryId;
            }

            public String getGalleryName() {
                return galleryName;
            }

            public void setGalleryName(String galleryName) {
                this.galleryName = galleryName;
            }

            public int getGoodTimes() {
                return goodTimes;
            }

            public void setGoodTimes(int goodTimes) {
                this.goodTimes = goodTimes;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }


            public String getWorksHeight() {
                return worksHeight;
            }

            public void setWorksHeight(String worksHeight) {
                this.worksHeight = worksHeight;
            }

            public int getWorksId() {
                return worksId;
            }

            public void setWorksId(int worksId) {
                this.worksId = worksId;
            }

            public String getWorksName() {
                return worksName;
            }

            public void setWorksName(String worksName) {
                this.worksName = worksName;
            }

            public String getWorksPic() {
                return worksPic;
            }

            public void setWorksPic(String worksPic) {
                this.worksPic = worksPic;
            }

            public String getWorksWidth() {
                return worksWidth;
            }

            public void setWorksWidth(String worksWidth) {
                this.worksWidth = worksWidth;
            }
        }
    }
}
