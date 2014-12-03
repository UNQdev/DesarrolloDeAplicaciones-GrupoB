package ar.edu.unq.desapp.grupob.model;

public enum InvoiceType {
    A {    
	    public double getFullTotal(double subtotal, double tax) {
	    	double total;
	    	total = subtotal * (1.0 + tax + 3.5);
	    	return total;
	    }
	    
	    public double getTotal(double subtotal, double tax) {
	    	double total;
	    	total = subtotal * (1.0 + tax);
	    	return total;
	    	}
    },
    B {
    	public double getFullTotal(double subtotal, double tax) {
	    	return subtotal;
	    }
	    
	    public double getTotal(double subtotal, double tax) {
	    	return subtotal;
	    }
    }, 
    C {
    	public double getFullTotal(double subtotal, double tax) {
	    	return subtotal;
	    }
	    
	    public double getTotal(double subtotal, double tax) {
	    	return subtotal;
	    }
    },
    X {
    	public double getFullTotal(double subtotal, double tax) {
	    	return subtotal;
	    }
	    
	    public double getTotal(double subtotal, double tax) {
	    	return subtotal;
	    }
    };
    
    public abstract double getFullTotal(double subtotal, double tax);
    public abstract double getTotal(double subtotal, double tax);
}