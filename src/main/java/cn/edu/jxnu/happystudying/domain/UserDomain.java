package cn.edu.jxnu.happystudying.domain;

import java.util.Date;

public class UserDomain {
    private String uId;
    private String uName;
    private String uEmail;
    private String uPassword;
    private String isPost;
    private String uNo;
    private String uAvatar;
    private String isSubDivision;
    private String uDiamondNumber;
    private String uAddress;
    private Date uRegisterTime;
    private String uSignature;
    private String uSex;

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getIsPost() {
        return isPost;
    }

    public void setIsPost(String isPost) {
        this.isPost = isPost;
    }

    public String getuNo() {
        return uNo;
    }

    public void setuNo(String uNo) {
        this.uNo = uNo;
    }

    public String getuAvatar() {
        return uAvatar;
    }

    public void setuAvatar(String uAvatar) {
        this.uAvatar = uAvatar;
    }

    public String getIsSubDivision() {
        return isSubDivision;
    }

    public void setIsSubDivision(String isSubDivision) {
        this.isSubDivision = isSubDivision;
    }

    public String getuDiamondNumber() {
        return uDiamondNumber;
    }

    public void setuDiamondNumber(String uDiamondNumber) {
        this.uDiamondNumber = uDiamondNumber;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public Date getuRegisterTime() {
        return uRegisterTime;
    }

    public void setuRegisterTime(Date uRegisterTime) {
        this.uRegisterTime = uRegisterTime;
    }

    public String getuSignature() {
        return uSignature;
    }

    public void setuSignature(String uSignature) {
        this.uSignature = uSignature;
    }


    @Override
    public String toString() {
        return "UserDomain{" +
                "uId='" + uId + '\'' +
                ", uName='" + uName + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", isPost='" + isPost + '\'' +
                ", uNo='" + uNo + '\'' +
                ", uAvatar='" + uAvatar + '\'' +
                ", isSubDivision='" + isSubDivision + '\'' +
                ", uDiamondNumber='" + uDiamondNumber + '\'' +
                ", uAddress='" + uAddress + '\'' +
                ", uRegisterTime=" + uRegisterTime +
                ", uSignature='" + uSignature + '\'' +
                ", uSex='" + uSex + '\'' +
                '}';
    }
}
