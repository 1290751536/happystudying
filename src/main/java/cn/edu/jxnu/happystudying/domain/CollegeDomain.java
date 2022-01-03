package cn.edu.jxnu.happystudying.domain;

public class CollegeDomain {
    private String cId,cName;

    @Override
    public String toString() {
        return "CollegeDomain{" +
                "cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                '}';
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
