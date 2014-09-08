package ar.edu.unq.desapp.grupob;

public enum InvoiceType {
    A {
        public int getTax(Vendor vendor) {
            return vendor.getTaxFromA();
        }
    },
    B {
        public int getTax(Vendor vendor) {
            return vendor.getTaxFromB();
        }
    },
    C {
        public int getTax(Vendor vendor) {
            return vendor.getTaxFromC();
        }
    },
    X {
        public int getTax(Vendor vendor) {
            return vendor.getTaxFromX();
        }
    };
    
    public abstract int getTax(Vendor vendor);
}
