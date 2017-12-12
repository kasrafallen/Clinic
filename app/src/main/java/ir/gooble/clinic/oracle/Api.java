package ir.gooble.clinic.oracle;

public enum Api {

    REGISTER(Method.POST) {
        @Override
        public String toString() {
            return "/Register";
        }
    },
    VERIFY(Method.POST) {
        @Override
        public String toString() {
            return "/Verify";
        }
    },
    CLINIC_INFO(Method.POST) {
        @Override
        public String toString() {
            return "/ClinicInfo";
        }
    },
    GALLERY(Method.POST) {
        @Override
        public String toString() {
            return "/galery";
        }
    },
    DOCTOR_INFO(Method.POST) {
        @Override
        public String toString() {
            return "/Doctors";
        }
    },
    PROFILE_INFO(Method.GET) {
        @Override
        public String toString() {
            return "/patientinfo";
        }
    },
    RESERVE_INFO(Method.POST) {
        @Override
        public String toString() {
            return "/GetWeek";
        }
    },
    RESERVE_POST(Method.POST) {
        @Override
        public String toString() {
            return "/ReserveTime";
        }
    },
    PROFILE_POST(Method.POST) {
        @Override
        public String toString() {
            return "/fillprofile";
        }
    },
    GET_TIME(Method.POST) {
        @Override
        public String toString() {
            return "http://api.timezonedb.com/v2/get-time-zone"
                    + "?key=" + KEY + "&format=json&by=zone&zone=Asia/Tehran";
        }
    };

    public static final String BASE = "176.9.220.144:8080";
    public static final String ROUTE = BASE + "/api/Spand";
    private static final String KEY = "1LVD4F1050VT";

    private int id;

    Api(int id) {
        this.id = id;
    }

    public int getMethod() {
        return id;
    }
}
