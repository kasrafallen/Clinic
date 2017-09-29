package ir.gooble.clinic.model;

import ir.gooble.clinic.oracle.Api;

public class Doctor {

    private int doctor_id;
    private String name;
    private String family_name;
    private Expertise[] expertise;
    private Degree[] degree;
    private String image;

    private long start;
    private long end;

    public Doctor() {
    }

    public Doctor(int doctor_id) {
        this.doctor_id = doctor_id;
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

    public String getImage() {
        if(image != null){
            return Api.BASE + image;
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
