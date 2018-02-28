package com.android.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * author: Rea.X
 * date: 2018/1/10.
 */

public class UserModel implements Serializable{
    private static final long serialVersionUID = -2025317346241254026L;

    @SerializedName("username")
    private String username;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("photo")
    private String photo;
    @SerializedName("userid")
    private String userid;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("sex")
    private int sex;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("sign")
    private String sign;
    //查看他人信息时返回是否关注了这个人
    @SerializedName("is_attention")
    private boolean is_attention;

    @SerializedName("attCount")
    private String attCount;
    @SerializedName("fanCount")
    private String fanCount;

    public String getAttCount() {
        return attCount;
    }

    public void setAttCount(String attCount) {
        this.attCount = attCount;
    }

    public String getFanCount() {
        return fanCount;
    }

    public void setFanCount(String fanCount) {
        this.fanCount = fanCount;
    }

    public boolean isIs_attention() {
        return is_attention;
    }

    public void setIs_attention(boolean is_attention) {
        this.is_attention = is_attention;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
