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
            return "/reserveinfo";
        }
    },
    RESERVE_POST(Method.POST) {
        @Override
        public String toString() {
            return "/reservepost";
        }
    },
    PROFILE_POST(Method.POST) {
        @Override
        public String toString() {
            return "/fillprofile";
        }
    };

    public static final String BASE = "176.9.220.144:8080";
    public static final String ROUTE = BASE + "/api/Spand";
    private int id;

    Api(int id) {
        this.id = id;
    }

    public int getMethod() {
        return id;
    }
}
