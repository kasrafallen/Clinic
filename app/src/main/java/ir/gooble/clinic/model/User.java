package ir.gooble.clinic.model;

public class User {

    private String number;
    private String token;
    private String name;

    private String mobile_number;
    private String deviceID;

    public User(String name, String mobile_number, String deviceID) {
        this.name = name;
        this.mobile_number = mobile_number;
        this.deviceID = deviceID;
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
}
