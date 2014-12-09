package ar.edu.unq.desapp.grupob.model;

public enum CardType {

    Debit {
    	public boolean isDebit() {
    		return true;
    	}
    }, 
    Credit {
    	public boolean isDebit() {
    		return false;
    	}
    };
    
    public abstract boolean isDebit();
}
