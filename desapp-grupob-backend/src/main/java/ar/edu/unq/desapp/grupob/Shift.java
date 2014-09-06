package ar.edu.unq.desapp.grupob;

public enum Shift {

    Beforenoon {
        public boolean isBeforenoon() {
            return true;
        }
    },
    Afternoon {
        public boolean isBeforenoon() {
            return false;
        }
    };

    public abstract boolean isBeforenoon();

}
