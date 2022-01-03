package cn.edu.jxnu.happystudying.domain;

import java.util.Date;

public class CheckInDomain {
    private String cId;
    private Date cLatestCheckinsTime;
    private String cContinuousCheckinsNumber;

    @Override
    public String toString() {
        return "CheckInDomain{" +
                "cId='" + cId + '\'' +
                ", cLatestCheckinsTime=" + cLatestCheckinsTime +
                ", cContinuousCheckinsNumber='" + cContinuousCheckinsNumber + '\'' +
                '}';
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public Date getcLatestCheckinsTime() {
        return cLatestCheckinsTime;
    }

    public void setcLatestCheckinsTime(Date cLatestCheckinsTime) {
        this.cLatestCheckinsTime = cLatestCheckinsTime;
    }

    public String getcContinuousCheckinsNumber() {
        return cContinuousCheckinsNumber;
    }

    public void setcContinuousCheckinsNumber(String cContinuousCheckinsNumber) {
        this.cContinuousCheckinsNumber = cContinuousCheckinsNumber;
    }
}
