package ir.gooble.clinic.model;

public class WeekDay {

    private String Condition;
    private String DayName;
    private String EndTime;
    private String StartTime;
    private int Index;
    private String date;

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

    public int getSize(int duration) {
        int from = get(getStartTime());
        int to = get(getEndTime());
        return (to - from) / duration;
    }

    private int get(String format) {
        int hour = 0;
        int minute = 0;
        String data[] = format.split(":");
        boolean isHour = true;
        for (String st : data) {
            if (st.startsWith("0")) {
                if (isHour) {
                    hour = Integer.parseInt(st.charAt(1) + "");
                } else {
                    minute = Integer.parseInt(st.charAt(1) + "");
                }
            } else {
                if (isHour) {
                    hour = Integer.parseInt(st);
                } else {
                    minute = Integer.parseInt(st);
                }
            }
            isHour = false;
        }
        return (hour * 60) + minute;
    }

    public int getHour(int i, int duration) {
        int inMinutes = (i * duration) + get(getStartTime());
        return (inMinutes / 60);
    }

    public int getMinute(int i, int duration) {
        int inMinutes = (i * duration) + get(getStartTime());
        int remain = inMinutes % 60;
        if (remain > 0) {
            if (remain > duration) {
                return (remain / duration) * duration;
            } else {
                return remain;
            }
        }
        return 0;
    }

    public int getIndex(int i, int duration) {
        int inMinutes = (i * duration) + get(getStartTime());
        int remain = inMinutes % 60;
        if (remain > 0) {
            return (remain / duration);
        }
        return 0;
    }

    public String format(int time) {
        if (time < 10) {
            return "0" + time;
        } else {
            return "" + time;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
