package cn.edu.jxnu.happystudying.domain;

import java.util.Date;

public class QuestionRespDomain {
    private String rId;
    private String rQuestionId;
    private String rQuestionTitle;
    private Date rTime;
    private String rUserId;
    private String rUserName;
    private String rUserAvatar;
    private String rContent;
    private String isAdopt;

    @Override
    public String toString() {
        return "QuestionRespDomain{" +
                "rId='" + rId + '\'' +
                ", rQuestionId='" + rQuestionId + '\'' +
                ", rQuestionTitle='" + rQuestionTitle + '\'' +
                ", rTime=" + rTime +
                ", rUserId='" + rUserId + '\'' +
                ", rUserName='" + rUserName + '\'' +
                ", rUserAvatar='" + rUserAvatar + '\'' +
                ", rContent='" + rContent + '\'' +
                ", isAdopt='" + isAdopt + '\'' +
                '}';
    }

    public String getrQuestionTitle() {
        return rQuestionTitle;
    }

    public void setrQuestionTitle(String rQuestionTitle) {
        this.rQuestionTitle = rQuestionTitle;
    }

    public String getrUserAvatar() {
        return rUserAvatar;
    }

    public void setrUserAvatar(String rUserAvatar) {
        this.rUserAvatar = rUserAvatar;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getrQuestionId() {
        return rQuestionId;
    }

    public void setrQuestionId(String rQuestionId) {
        this.rQuestionId = rQuestionId;
    }

    public Date getrTime() {
        return rTime;
    }

    public void setrTime(Date rTime) {
        this.rTime = rTime;
    }

    public String getrUserId() {
        return rUserId;
    }

    public void setrUserId(String rUserId) {
        this.rUserId = rUserId;
    }

    public String getrUserName() {
        return rUserName;
    }

    public void setrUserName(String rUserName) {
        this.rUserName = rUserName;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public String getIsAdopt() {
        return isAdopt;
    }

    public void setIsAdopt(String isAdopt) {
        this.isAdopt = isAdopt;
    }
}
