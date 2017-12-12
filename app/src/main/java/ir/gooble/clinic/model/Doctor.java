package ir.gooble.clinic.model;

import java.util.ArrayList;

public class Doctor {

    public Doctor() {
    }

    private int DoctorID;
    private String Email;
    private String about;
    private Degree[] Certificates;
    private Expertise[] Experties;
    private String DoctorLastName;
    private String DoctorTitle;
    private String PhoneNumber;
    private String NationalID;
    private String DoctorName;
    private String PictureURL;

    private long start;
    private long end;

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public Degree[] getCertificates() {
        return Certificates;
    }

    public void setCertificates(Degree[] certificates) {
        Certificates = certificates;
    }

    public Expertise[] getExperties() {
        return Experties;
    }

    public void setExperties(Expertise[] experties) {
        Experties = experties;
    }

    public String getPictureURL() {
        return PictureURL;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int doctorID) {
        DoctorID = doctorID;
    }

    public String getDoctorLastName() {
        return DoctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        DoctorLastName = doctorLastName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDoctorTitle() {
        return DoctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        DoctorTitle = doctorTitle;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNationalID() {
        return NationalID;
    }

    public void setNationalID(String nationalID) {
        NationalID = nationalID;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setPictureURL(String pictureURL) {
        PictureURL = pictureURL;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getExpertiseString() {
        if (Experties != null && Experties.length > 0) {
            return Experties[0].getText();
        }
        return "-";
    }

    public ArrayList<String> getExpertiseList() {
        ArrayList<String> list = new ArrayList<>();
        if (Experties != null && Experties.length > 0) {
            for (Expertise ex : Experties) {
                list.add("- " + ex.getText());
            }
        }
        return list;
    }

    public ArrayList<String> getDegreeList() {
        ArrayList<String> list = new ArrayList<>();
        if (Certificates != null && Certificates.length > 0) {
            for (Degree de : Certificates) {
                list.add("- " + de.getText());
            }
        }
        return list;
    }
}
