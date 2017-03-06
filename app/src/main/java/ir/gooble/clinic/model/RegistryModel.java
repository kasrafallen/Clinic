package ir.gooble.clinic.model;

public class RegistryModel {
    public final static int SEX_MALE = 0;
    public final static int SEX_FEMALE = 1;

    private int id;
    private int user_sex;

    private String user_name;
    private String father_name;
    private String user_code;
    private String user_mobile;
    private String user_address;
    private String insurance_id;
    private String user_image_link;
    private String user_image_path;
    private String user_birthday;

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_image_link() {
        return user_image_link;
    }

    public void setUser_image_link(String user_image_link) {
        this.user_image_link = user_image_link;
    }

    public String getUser_image_path() {
        return user_image_path;
    }

    public void setUser_image_path(String user_image_path) {
        this.user_image_path = user_image_path;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(String insurance_id) {
        this.insurance_id = insurance_id;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }
}
