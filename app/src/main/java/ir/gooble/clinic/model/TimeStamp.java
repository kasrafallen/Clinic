package ir.gooble.clinic.model;

public class TimeStamp {

    private String status;
    private long timestamp;
    private long gmtOffset;
    private String formatted;

    public long getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(long gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public boolean isSuccess(){
        return status != null && status.equalsIgnoreCase("OK");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }
}
