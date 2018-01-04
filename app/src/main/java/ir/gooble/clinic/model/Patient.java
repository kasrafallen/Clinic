package ir.gooble.clinic.model;

public class Patient {

    private User[] Patients;
    private String PID;

    public User[] getPatients() {
        return Patients;
    }

    public void setPatients(User[] patients) {
        Patients = patients;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }
}
