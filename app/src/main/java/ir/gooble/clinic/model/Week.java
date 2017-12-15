package ir.gooble.clinic.model;

public class Week {

    private String Condition;
    private String DayName;
    private String EndTime;
    private String StartTime;
    private int Index;

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        this.Index = index;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getDayName() {
        return DayName;
    }

    public void setDayName(String dayName) {
        DayName = dayName;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public boolean isCurrentDay(int currentDay) {
        return Index == currentDay;
    }
}
