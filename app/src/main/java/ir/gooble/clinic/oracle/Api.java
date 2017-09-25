package ir.gooble.clinic.oracle;

public enum Api {

    REGISTER(Method.POST){
        @Override
        public String toString() {
            return "/register";
        }
    },
    DOCTOR_INFO(Method.GET){
        @Override
        public String toString() {
            return "/doctorinfo";
        }
    },
    RESERVE_INFO(Method.GET){
        @Override
        public String toString() {
            return "/reserveinfo";
        }
    },
    RESERVE_POST(Method.POST){
        @Override
        public String toString() {
            return "/reservepost";
        }
    },
    PROFILE_INFO(Method.GET){
        @Override
        public String toString() {
            return "/patientinfo";
        }
    },
    PROFILE_POST(Method.POST){
        @Override
        public String toString() {
            return "/fillprofile";
        }
    };

    public static final String ROUTE = "pyana.ir";
    private int id;

    Api(int id) {
        this.id = id;
    }

    public int getMethod() {
        return id;
    }
}
