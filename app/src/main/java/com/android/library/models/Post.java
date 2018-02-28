package com.android.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wiki on 2018/1/13.
 */

public class Post implements Serializable{

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("time")
    private String time;
    @SerializedName("seeNum")
    private String seeNum;
    @SerializedName("comments_count")
    private String comments_count;
    @SerializedName("img1")
    private String img1;
    @SerializedName("img2")
    private String img2;
    @SerializedName("img3")
    private String img3;
    @SerializedName("save_count")
    private String save_count;
    @SerializedName("star_count")
    private String star_count;
    @SerializedName("userName")
    private String userName;
    @SerializedName("userNickname")
    private String userNickname;
    @SerializedName("userPhoto")
    private String userPhoto;
    @SerializedName("intro")
    private String intro;

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    @SerializedName("userSign")
    private String userSign;
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

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

    public String getSeeNum() {
        return seeNum;
    }

    public void setSeeNum(String seeNum) {
        this.seeNum = seeNum;
    }

    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getSave_count() {
        return save_count;
    }

    public void setSave_count(String save_count) {
        this.save_count = save_count;
    }

    public String getStar_count() {
        return star_count;
    }

    public void setStar_count(String star_count) {
        this.star_count = star_count;
    }
}
