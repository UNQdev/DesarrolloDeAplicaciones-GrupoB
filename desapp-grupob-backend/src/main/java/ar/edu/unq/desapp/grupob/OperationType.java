package ar.edu.unq.desapp.grupob;

public enum OperationType {

        Incoming {
            public double getValue(double amount) {
                return amount;
            }
        },
        Outcoming {
            public double getValue(double amount) {
                return -amount;
            }
        };

        public abstract double getValue(double amount);
}
