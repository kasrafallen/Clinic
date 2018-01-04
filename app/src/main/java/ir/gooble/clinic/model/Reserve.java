package ir.gooble.clinic.model;

public class Reserve {

    private int DoctorID;
    private String StartDate;
    private int Duration;
    private int Index;

    private String Date;
    private String Hour;
    private String PName;
    private String PLastName;
    private String Mobile;

    private WeekDay[] Week;
    private ReserveDay[] Days;

    private Doctor Doctor;

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public WeekDay[] getWeek() {
        return Week;
    }

    public void setWeek(WeekDay[] week) {
        Week = week;
    }

    public ReserveDay[] getDays() {
        return Days;
    }

    public void setDays(ReserveDay[] days) {
        Days = days;
    }

    public Doctor getDoctor() {
        return Doctor;
    }

    public void setDoctor(Doctor doctor) {
        Doctor = doctor;
    }

    public Reserve(int doctorID, String startDate, int index) {
        DoctorID = doctorID;
        StartDate = startDate;
        Index = index;
    }

    public Reserve(int doctorID, String startDate, int index, String hour, User user) {
        DoctorID = doctorID;
        Date = startDate;
        Index = index;
        Hour = hour;
        PName = user.getPName();
        PLastName = user.getPLastName();
        Mobile = user.getMobile_number();
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
