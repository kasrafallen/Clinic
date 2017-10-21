package ir.gooble.clinic.model;

public class Gallery {

    private String[] Pictures;

    public String[] getPictures() {
        if(Pictures == null){
            return new String[0];
        }
        return Pictures;
    }

    public void setPictures(String[] pictures) {
        Pictures = pictures;
    }
}
