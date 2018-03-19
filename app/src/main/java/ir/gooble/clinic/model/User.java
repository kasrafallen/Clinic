package ir.gooble.clinic.model;

public class User {

    public static final String MEN = "men";
    public static final String WOMEN = "women";

    private String mobile_number = "";
    private String Token = "";
    private String UID = "";
    private String PID = "";
    private String sms_code = "";

    private String imagePath = "";
    private String fcm_token = "";

    private Address Address = new Address();

    private String PName = "";
    private String PLastName = "";
    private String BirthDayDate = "";
    private String NationalNumber = "";
    private String Sexuality = "";
    private String FatherName = "";
    private String PhoneNumber = "";
    private String PMobile = "";
    private String InsuranceNumber = "";
    private String InsuranceType = "";
    private String ImagePatient = "";
    private String ImageInsurance = "";
    private String Familiarity = "";
    private String Description = "";
    private String TimeRegister = "";
    private String DateRegister = "";

    public String getTimeRegister() {
        return TimeRegister;
    }

    public void setTimeRegister(String timeRegister) {
        TimeRegister = timeRegister;
    }

    public String getDateRegister() {
        return DateRegister;
    }

    public void setDateRegister(String dateRegister) {
        DateRegister = dateRegister;
    }

    public String getImageInsurance() {
        return ImageInsurance;
    }

    public void setImageInsurance(String imageInsurance) {
        ImageInsurance = imageInsurance;
    }

    public User(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public User() {
    }

    public String getPMobile() {
        return PMobile;
    }

    public void setPMobile(String PMobile) {
        this.PMobile = PMobile;
    }

    public String getFamiliarity() {
        return Familiarity;
    }

    public void setFamiliarity(String familiarity) {
        Familiarity = familiarity;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address address) {
        Address = address;
    }

    public String getBirthDayDate() {
        return BirthDayDate;
    }

    public void setBirthDayDate(String birthDayDate) {
        BirthDayDate = birthDayDate;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getImagePatient() {
        return ImagePatient;
    }

    public void setImagePatient(String imagePatient) {
        ImagePatient = imagePatient;
    }

    public String getInsuranceNumber() {
        return InsuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        InsuranceNumber = insuranceNumber;
    }

    public String getInsuranceType() {
        return InsuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        InsuranceType = insuranceType;
    }

    public String getNationalNumber() {
        return NationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        NationalNumber = nationalNumber;
    }

    public String getPLastName() {
        return PLastName;
    }

    public void setPLastName(String PLastName) {
        this.PLastName = PLastName;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getSexuality() {
        return Sexuality;
    }

    public void setSexuality(String sexuality) {
        Sexuality = sexuality;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
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

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getAddressString() {
        if (getAddress() != null) {
            StringBuilder builder = new StringBuilder();
            if (getAddress().getStreet() != null && !getAddress().getStreet().equals("")) {
                builder.append(getAddress().getStreet()).append(" ");
            }
            if (getAddress().getAlley() != null && !getAddress().getAlley().equals("")) {
                builder.append(getAddress().getAlley()).append(" ");
            }
            if (getAddress().getHouseNumber() != null && !getAddress().getHouseNumber().equals("")) {
                builder.append(getAddress().getHouseNumber()).append(" ");
            }
            if (getAddress().getUnit() != null && !getAddress().getUnit().equals("")) {
                builder.append(getAddress().getUnit()).append(" ");
            }
            if (builder.length() > 0) {
                return builder.toString();
            }
        }
        return "-";
    }

    public String getName() {
        StringBuilder builder = new StringBuilder();
        if (getPName() != null && !getPName().equals("")) {
            builder.append(getPName());
        }
        if (getPLastName() != null && !getPLastName().equals("")) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(getPLastName());
        }
        return builder.toString();
    }

    public class Address {

        private String alley = "";
        private String HouseNumber = "";
        private String Street = "";
        private String Unit = "";
        private String UnFormatted = "";

        public Address() {
        }

        public Address(String unFormatted) {
            UnFormatted = unFormatted;
        }

        public String getUnFormatted() {
            return UnFormatted;
        }

        public void setUnFormatted(String unFormatted) {
            UnFormatted = unFormatted;
        }

        public String getAlley() {
            return alley;
        }

        public void setAlley(String alley) {
            this.alley = alley;
        }

        public String getHouseNumber() {
            return HouseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            HouseNumber = houseNumber;
        }

        public String getStreet() {
            return Street;
        }

        public void setStreet(String street) {
            Street = street;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String unit) {
            Unit = unit;
        }
    }

    public boolean isMen() {
        return !(Sexuality != null && Sexuality.equals(WOMEN));
    }
}
