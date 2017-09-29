package ir.gooble.clinic.model;

public class User extends Sign {

    private String imagePath;
    private String number;
    private String token;
    private String name;

    private String birthday;
    private String national_number;
    private String father_name;
    private String street;
    private String insurance_number;
    private String insurance_type;

    private String trading_method = "-";
    private String description = "-";
    private String family_name = "-";
    private String fix_number = "-";
    private String alley = "-";
    private String plaque = "-";
    private String unit = "-";

    private boolean men;
    private boolean women;

    public String getTrading_method() {
        return trading_method;
    }

    public void setTrading_method(String trading_method) {
        this.trading_method = trading_method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getInsurance_type() {
        return insurance_type;
    }

    public void setInsurance_type(String insurance_type) {
        this.insurance_type = insurance_type;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNational_number() {
        return national_number;
    }

    public void setNational_number(String national_number) {
        this.national_number = national_number;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getFix_number() {
        return fix_number;
    }

    public void setFix_number(String fix_number) {
        this.fix_number = fix_number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getInsurance_number() {
        return insurance_number;
    }

    public void setInsurance_number(String insurance_number) {
        this.insurance_number = insurance_number;
    }

    public User(String name, String mobile_number, String deviceID) {
        this.name = name;
        this.mobile_number = mobile_number;
        this.deviceID = deviceID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isMen() {
        return men;
    }

    public boolean isWomen() {
        return women;
    }

    public void setMen(boolean men) {
        this.men = men;
    }

    public void setWomen(boolean women) {
        this.women = women;
    }
}
