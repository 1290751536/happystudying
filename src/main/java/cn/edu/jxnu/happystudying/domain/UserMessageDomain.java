package cn.edu.jxnu.happystudying.domain;

import java.util.Date;

public class UserMessageDomain {
    private Integer mId, mUserId, mReplyUserId, mQuestionId, mBlogId;
    private String mMessageDescription, mReplyUserName, mQuestionTitle, mBlogTitle;
    private Date mResponseTime;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getmUserId() {
        return mUserId;
    }

    public void setmUserId(Integer mUserId) {
        this.mUserId = mUserId;
    }

    public Integer getmReplyUserId() {
        return mReplyUserId;
    }

    public void setmReplyUserId(Integer mReplyUserId) {
        this.mReplyUserId = mReplyUserId;
    }

    public Integer getmQuestionId() {
        return mQuestionId;
    }

    public void setmQuestionId(Integer mQuestionId) {
        this.mQuestionId = mQuestionId;
    }

    public Integer getmBlogId() {
        return mBlogId;
    }

    public void setmBlogId(Integer mBlogId) {
        this.mBlogId = mBlogId;
    }

    public String getmMessageDescription() {
        return mMessageDescription;
    }

    public void setmMessageDescription(String mMessageDescription) {
        this.mMessageDescription = mMessageDescription;
    }

    public String getmReplyUserName() {
        return mReplyUserName;
    }

    public void setmReplyUserName(String mReplyUserName) {
        this.mReplyUserName = mReplyUserName;
    }

    public String getmQuestionTitle() {
        return mQuestionTitle;
    }

    public void setmQuestionTitle(String mQuestionTitle) {
        this.mQuestionTitle = mQuestionTitle;
    }

    public String getmBlogTitle() {
        return mBlogTitle;
    }

    public void setmBlogTitle(String mBlogTitle) {
        this.mBlogTitle = mBlogTitle;
    }

    public Date getmResponseTime() {
        return mResponseTime;
    }

    public void setmResponseTime(Date mResponseTime) {
        this.mResponseTime = mResponseTime;
    }

    @Override
    public String toString() {
        return "UserMessageDomain{" +
                "mId=" + mId +
                ", mUserId=" + mUserId +
                ", mReplyUserId=" + mReplyUserId +
                ", mQuestionId=" + mQuestionId +
                ", mBlogId=" + mBlogId +
                ", mMessageDescription='" + mMessageDescription + '\'' +
                ", mReplyUserName='" + mReplyUserName + '\'' +
                ", mQuestionTitle='" + mQuestionTitle + '\'' +
                ", mBlogTitle='" + mBlogTitle + '\'' +
                ", mResponseTime=" + mResponseTime +
                '}';
    }

}
