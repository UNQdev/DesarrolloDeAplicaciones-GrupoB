package ar.edu.unq.desapp.grupob;

/**
 * @author damian
 */
public enum Shift {

    Beforenoon {
        public String getTimeOfDay() {
            return "Mañana";
        }
    },
    Afternoon {
        public String getTimeOfDay() {
            return "Noche";
        }
    };

    public abstract String getTimeOfDay();
}
