package cn.edu.jxnu.happystudying.domain;

import java.util.Date;

public class BlogDomain {
    private String bId;
    private String bUserId;
    private String bUserName;
    private String bActivityId;
    private Date bPublishTime;
    private String bTitle;
    private String bIcon;
    private String bContent;
    private String isEssence;
    private Integer bViewNumber;
    private Integer bResponsesNumber;

    @Override
    public String toString() {
        return "BlogDomain{" +
                "bId='" + bId + '\'' +
                ", bUserId='" + bUserId + '\'' +
                ", bUserName='" + bUserName + '\'' +
                ", bActivityId='" + bActivityId + '\'' +
                ", bPublishTime=" + bPublishTime +
                ", bTitle='" + bTitle + '\'' +
                ", bIcon='" + bIcon + '\'' +
                ", bContent='" + bContent + '\'' +
                ", isEssence='" + isEssence + '\'' +
                ", bViewNumber=" + bViewNumber +
                ", bResponsesNumber=" + bResponsesNumber +
                '}';
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public String getbUserId() {
        return bUserId;
    }

    public void setbUserId(String bUserId) {
        this.bUserId = bUserId;
    }

    public String getbUserName() {
        return bUserName;
    }

    public void setbUserName(String bUserName) {
        this.bUserName = bUserName;
    }

    public String getbActivityId() {
        return bActivityId;
    }

    public void setbActivityId(String bActivityId) {
        this.bActivityId = bActivityId;
    }

    public Date getbPublishTime() {
        return bPublishTime;
    }

    public void setbPublishTime(Date bPublishTime) {
        this.bPublishTime = bPublishTime;
    }

    public String getbTitle() {
        return bTitle;
    }

    public void setbTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getbIcon() {
        return bIcon;
    }

    public void setbIcon(String bIcon) {
        this.bIcon = bIcon;
    }

    public String getbContent() {
        return bContent;
    }

    public void setbContent(String bContent) {
        this.bContent = bContent;
    }

    public String getIsEssence() {
        return isEssence;
    }

    public void setIsEssence(String isEssence) {
        this.isEssence = isEssence;
    }

    public Integer getbViewNumber() {
        return bViewNumber;
    }

    public void setbViewNumber(Integer bViewNumber) {
        this.bViewNumber = bViewNumber;
    }

    public Integer getbResponsesNumber() {
        return bResponsesNumber;
    }

    public void setbResponsesNumber(Integer bResponsesNumber) {
        this.bResponsesNumber = bResponsesNumber;
    }
}
