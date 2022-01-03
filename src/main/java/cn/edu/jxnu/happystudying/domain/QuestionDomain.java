package cn.edu.jxnu.happystudying.domain;

import java.util.Date;

public class QuestionDomain {
    private String qId;
    private String qUserId;
    private String qUserName;
    private String qCollege;
    private String qGradeLevel;
    private String qIcon;
    private String qTitle;
    private String qDescription;
    private Integer qDiamondNumber;
    private Date qPublishTime;
    private String isSolve;
    private String isSticky;
    private String isEssence;
    private Integer qViewNumber;
    private Integer qResponsesNumber;

    @Override
    public String toString() {
        return "QuestionDomain{" +
                "qId='" + qId + '\'' +
                ", qUserId='" + qUserId + '\'' +
                ", qUserName='" + qUserName + '\'' +
                ", qCollege='" + qCollege + '\'' +
                ", qGradeLevel='" + qGradeLevel + '\'' +
                ", qIcon='" + qIcon + '\'' +
                ", qTitle='" + qTitle + '\'' +
                ", qDescription='" + qDescription + '\'' +
                ", qDiamondNumber=" + qDiamondNumber +
                ", qPublishTime=" + qPublishTime +
                ", isSolve='" + isSolve + '\'' +
                ", isSticky='" + isSticky + '\'' +
                ", isEssence='" + isEssence + '\'' +
                ", qViewNumber=" + qViewNumber +
                ", qResponsesNumber=" + qResponsesNumber +
                '}';
    }

    public Integer getqViewNumber() {
        return qViewNumber;
    }

    public void setqViewNumber(Integer qViewNumber) {
        this.qViewNumber = qViewNumber;
    }

    public Integer getqResponsesNumber() {
        return qResponsesNumber;
    }

    public void setqResponsesNumber(Integer qResponsesNumber) {
        this.qResponsesNumber = qResponsesNumber;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public String getqUserId() {
        return qUserId;
    }

    public void setqUserId(String qUserId) {
        this.qUserId = qUserId;
    }

    public String getqUserName() {
        return qUserName;
    }

    public void setqUserName(String qUserName) {
        this.qUserName = qUserName;
    }

    public String getqCollege() {
        return qCollege;
    }

    public void setqCollege(String qCollege) {
        this.qCollege = qCollege;
    }

    public String getqGradeLevel() {
        return qGradeLevel;
    }

    public void setqGradeLevel(String qGradeLevel) {
        this.qGradeLevel = qGradeLevel;
    }

    public String getqIcon() {
        return qIcon;
    }

    public void setqIcon(String qIcon) {
        this.qIcon = qIcon;
    }

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getqDescription() {
        return qDescription;
    }

    public void setqDescription(String qDescription) {
        this.qDescription = qDescription;
    }


    public Integer getqDiamondNumber() {
        return qDiamondNumber;
    }

    public void setqDiamondNumber(Integer qDiamondNumber) {
        this.qDiamondNumber = qDiamondNumber;
    }

    public Date getqPublishTime() {
        return qPublishTime;
    }

    public void setqPublishTime(Date qPublishTime) {
        this.qPublishTime = qPublishTime;
    }

    public String getIsSolve() {
        return isSolve;
    }

    public void setIsSolve(String isSolve) {
        this.isSolve = isSolve;
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
