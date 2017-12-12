package ir.gooble.clinic.model;

public class Reserve {

    private int DoctorID;
    private String StartDate;
    private int Index;

    private String Date;
    private String Hour;
    private String PName;
    private String PLastName;
    private String Mobile;

    public Reserve(int doctorID, String startDate, int index) {
        DoctorID = doctorID;
        StartDate = startDate;
        Index = index;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int doctorID) {
        DoctorID = doctorID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getPLastName() {
        return PLastName;
    }

    public void setPLastName(String PLastName) {
        this.PLastName = PLastName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public Reserve(int doctorID) {
        DoctorID = doctorID;
    }
}
