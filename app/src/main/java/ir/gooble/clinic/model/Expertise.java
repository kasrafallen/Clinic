package ir.gooble.clinic.model;

public class Expertise {

    private int id;
    private String name;
    private Pivot pivot;

    public class Pivot {
        private int doctor_id;
        private int expertise_id;

        public int getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(int doctor_id) {
            this.doctor_id = doctor_id;
        }

        public int getExpertise_id() {
            return expertise_id;
        }

        public void setExpertise_id(int expertise_id) {
            this.expertise_id = expertise_id;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }
}
