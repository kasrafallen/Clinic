package ir.gooble.clinic.model;

import java.util.ArrayList;

import ir.gooble.clinic.oracle.Api;

public class Doctor {

    public Doctor() {
    }

    public Doctor(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    private Expertise[] expertise;
    private String family_name;
    private Degree[] Certificates;
    private int doctor_id;
    private String image;
    private String name;

    private long start;
    private long end;

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public Expertise[] getExpertise() {
        return expertise;
    }

    public void setExpertise(Expertise[] expertise) {
        this.expertise = expertise;
    }

    public Degree[] getCertificates() {
        return Certificates;
    }

    public void setCertificates(Degree[] certificates) {
        Certificates = certificates;
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

    public String getImage() {
        if (image != null) {
            return Api.BASE + image;
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExpertiseString() {
        if (expertise != null && expertise.length > 0) {
            return expertise[0].getName();
        }
        return "-";
    }

    public String getAbout() {
        return "-";
    }

    public ArrayList<String> getExpertiseList() {
        ArrayList<String> list = new ArrayList<>();
        if (expertise != null && expertise.length > 0) {
            for (Expertise ex : expertise) {
                list.add("- " + ex.getName());
            }
        }
        return list;
    }

    public ArrayList<String> getDegreeList() {
        ArrayList<String> list = new ArrayList<>();
        if (Certificates != null && Certificates.length > 0) {
            for (Degree de : Certificates) {
                list.add("- " + de.getName());
            }
        }
        return list;
    }
}
