package ir.gooble.clinic.model;

public class Degree {

    private int id;
    private int name;
    private Pivot pivot;

    public class Pivot {

        private int doctor_id;
        private int degree_id;

        public int getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(int doctor_id) {
            this.doctor_id = doctor_id;
        }

        public int getDegree_id() {
            return degree_id;
        }

        public void setDegree_id(int degree_id) {
            this.degree_id = degree_id;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }
}
