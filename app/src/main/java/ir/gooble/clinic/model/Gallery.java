package ir.gooble.clinic.model;

public class Gallery {

    private Picture[] Pictures;

    public Picture[] getPictures() {
        if (Pictures == null) {
            return new Picture[0];
        }
        return Pictures;
    }

    public void setPictures(Picture[] pictures) {
        Pictures = pictures;
    }

    public class Picture {
        private String Address;
        private String Tag;

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getTag() {
            return Tag;
        }

        public void setTag(String tag) {
            Tag = tag;
        }
    }
}
