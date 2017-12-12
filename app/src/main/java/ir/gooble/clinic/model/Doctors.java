package ir.gooble.clinic.model;

public class Doctors {

    private int CID;
    private Doctor[] Doctors;

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public Doctor[] getDoctors() {
        return Doctors;
    }

    public void setDoctors(Doctor[] doctors) {
        Doctors = doctors;
    }
}
