package ir.gooble.clinic.model;

public class ReserveDay {

    private String Date;
    private String Time;
    private int index;

    public ReserveDay() {
    }

    public ReserveDay(String date, String time, int index) {
        Date = date;
        Time = time;
        this.index = index;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public boolean isCurrentDate(String time) {
        return time != null && Date != null && Date.equalsIgnoreCase(time);
    }

    public int getHour() {
        if (Time == null) {
            return 0;
        }
        try {
            String[] data = Time.split(":");
            String hour = data[0];
            if (hour.startsWith("0")) {
                return Integer.parseInt(hour.charAt(1) + "");
            } else {
                return Integer.parseInt(hour);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMinute() {
        if (Time == null) {
            return 0;
        }
        try {
            String[] data = Time.split(":");
            String minute = data[1];
            if (minute.startsWith("0")) {
                return Integer.parseInt(minute.charAt(1) + "");
            } else {
                return Integer.parseInt(minute);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String config(int hour, int min, int index, int duration) {
        StringBuilder builder = new StringBuilder();
        if (index > 0) {
            min = min + (duration * index);
        }
        if (min >= 60) {
            hour = hour + (min / 60);
            min = min % (60);
        }
        if (hour < 10) {
            builder.append("0");
        }
        builder.append(hour);
        builder.append(":");
        if (min < 10) {
            builder.append("0");
        }
        builder.append(min);
        return builder.toString();
    }
}
