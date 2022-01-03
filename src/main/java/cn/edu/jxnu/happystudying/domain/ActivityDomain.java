package cn.edu.jxnu.happystudying.domain;

import java.util.Date;

public class ActivityDomain {
    private String aId;
    private String aUserId;
    private String aUserName;
    private Date aPublishTime;
    private Date aBeginTime;
    private Date aEndTime;
    private Integer aViewNumber;
    private Integer aResponsesNumber;
    private String aTitle;
    private String aIcon;
    private String aDescription;
    private String aCollege;
    private String aGradeLevel;
    private String isSticky;
    private String isEssence;
    private String isAudit;

    @Override
    public String toString() {
        return "ActivityDomain{" +
                "aId='" + aId + '\'' +
                ", aUserId='" + aUserId + '\'' +
                ", aUserName='" + aUserName + '\'' +
                ", aPublishTime=" + aPublishTime +
                ", aBeginTime=" + aBeginTime +
                ", aEndTime=" + aEndTime +
                ", aViewNumber=" + aViewNumber +
                ", aResponsesNumber=" + aResponsesNumber +
                ", aTitle='" + aTitle + '\'' +
                ", aIcon='" + aIcon + '\'' +
                ", aDescription='" + aDescription + '\'' +
                ", aCollege='" + aCollege + '\'' +
                ", aGradeLevel='" + aGradeLevel + '\'' +
                ", isSticky='" + isSticky + '\'' +
                ", isEssence='" + isEssence + '\'' +
                ", isAudit='" + isAudit + '\'' +
                '}';
    }

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    public String getaCollege() {
        return aCollege;
    }

    public void setaCollege(String aCollege) {
        this.aCollege = aCollege;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getaUserId() {
        return aUserId;
    }

    public void setaUserId(String aUserId) {
        this.aUserId = aUserId;
    }

    public String getaUserName() {
        return aUserName;
    }

    public void setaUserName(String aUserName) {
        this.aUserName = aUserName;
    }

    public Date getaPublishTime() {
        return aPublishTime;
    }

    public void setaPublishTime(Date aPublishTime) {
        this.aPublishTime = aPublishTime;
    }

    public Date getaBeginTime() {
        return aBeginTime;
    }

    public void setaBeginTime(Date aBeginTime) {
        this.aBeginTime = aBeginTime;
    }

    public Date getaEndTime() {
        return aEndTime;
    }

    public void setaEndTime(Date aEndTime) {
        this.aEndTime = aEndTime;
    }

    public Integer getaViewNumber() {
        return aViewNumber;
    }

    public void setaViewNumber(Integer aViewNumber) {
        this.aViewNumber = aViewNumber;
    }

    public Integer getaResponsesNumber() {
        return aResponsesNumber;
    }

    public void setaResponsesNumber(Integer aResponsesNumber) {
        this.aResponsesNumber = aResponsesNumber;
    }

    public String getaTitle() {
        return aTitle;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public String getaIcon() {
        return aIcon;
    }

    public void setaIcon(String aIcon) {
        this.aIcon = aIcon;
    }

    public String getaDescription() {
        return aDescription;
    }

    public void setaDescription(String aDescription) {
        this.aDescription = aDescription;
    }

    public String getaGradeLevel() {
        return aGradeLevel;
    }

    public void setaGradeLevel(String aGradeLevel) {
        this.aGradeLevel = aGradeLevel;
    }

    public String getIsSticky() {
        return isSticky;
    }

    public void setIsSticky(String isSticky) {
        this.isSticky = isSticky;
    }

    public String getIsEssence() {
        return isEssence;
    }

    public void setIsEssence(String isEssence) {
        this.isEssence = isEssence;
    }
}
