package ir.gooble.clinic.model;

public class User {

    private String number;
    private String token;
    private String name;

    private String mobile_number;

    public User(String name, String mobile_number) {
        this.name = name;
        this.mobile_number = mobile_number;
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
