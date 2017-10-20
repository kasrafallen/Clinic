package ir.gooble.clinic.model;

public class Status {

    private String MessageEN;
    private String MessageFA;
    private int Status;

    public String getMessageEN() {
        return MessageEN;
    }

    public void setMessageEN(String messageEN) {
        MessageEN = messageEN;
    }

    public String getMessageFA() {
        return MessageFA;
    }

    public void setMessageFA(String messageFA) {
        MessageFA = messageFA;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
