package com.android.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wiki on 2018/1/17.
 */

public class PostCommentModel implements Serializable{


    @SerializedName("commentId")
    private String commentId;
    @SerializedName("commentText")
    private String commentText;
    @SerializedName("userNmae")
    private String userNmae;
    @SerializedName("nickName")
    private String nickName;
    @SerializedName("userSign")
    private String userSign;
    @SerializedName("userPhoto")
    private String userPhoto;
    @SerializedName("reply")
    private List<ReplyBean> reply;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUserNmae() {
        return userNmae;
    }

    public void setUserNmae(String userNmae) {
        this.userNmae = userNmae;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public List<ReplyBean> getReply() {
        return reply;
    }

    public void setReply(List<ReplyBean> reply) {
        this.reply = reply;
    }

    public static class ReplyBean implements Serializable{
        @SerializedName("replyText")
        private String replyText;
        @SerializedName("replyId")
        private String replyId;
        @SerializedName("userNmae")
        private String userNmae;
        @SerializedName("nickName")
        private String nickName;
        @SerializedName("userSign")
        private String userSign;
        @SerializedName("userPhoto")
        private String userPhoto;

        public String getReplyText() {
            return replyText;
        }

        public void setReplyText(String replyText) {
            this.replyText = replyText;
        }

        public String getReplyId() {
            return replyId;
        }

        public void setReplyId(String replyId) {
            this.replyId = replyId;
        }

        public String getUserNmae() {
            return userNmae;
        }

        public void setUserNmae(String userNmae) {
            this.userNmae = userNmae;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserSign() {
            return userSign;
        }

        public void setUserSign(String userSign) {
            this.userSign = userSign;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }
    }
}
