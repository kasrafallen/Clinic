package ir.gooble.clinic.model;

public class Clinic {

    private int CID;
    private String Color1;
    private String Color2;
    private String Color3;
    private String Description;
    private String LogoURL;
    private PhoneNumber[] PhoneNumbers;
    private SocialAccount[] SocialAccounts;
    private Address[] Addresses;
    private String PictureURL;
    private String Title;

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getColor1() {
        return Color1;
    }

    public void setColor1(String color1) {
        Color1 = color1;
    }

    public String getColor2() {
        return Color2;
    }

    public void setColor2(String color2) {
        Color2 = color2;
    }

    public String getColor3() {
        return Color3;
    }

    public void setColor3(String color3) {
        Color3 = color3;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLogoURL() {
        return LogoURL;
    }

    public void setLogoURL(String logoURL) {
        LogoURL = logoURL;
    }

    public PhoneNumber[] getPhoneNumbers() {
        return PhoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumber[] phoneNumbers) {
        PhoneNumbers = phoneNumbers;
    }

    public SocialAccount[] getSocialAccounts() {
        return SocialAccounts;
    }

    public void setSocialAccounts(SocialAccount[] socialAccounts) {
        SocialAccounts = socialAccounts;
    }

    public Address[] getAddresses() {
        return Addresses;
    }

    public void setAddresses(Address[] addresses) {
        Addresses = addresses;
    }

    public String getPictureURL() {
        return PictureURL;
    }

    public void setPictureURL(String pictureURL) {
        PictureURL = pictureURL;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
