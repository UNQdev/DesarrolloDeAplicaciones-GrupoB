package ar.edu.unq.desapp.grupob;

public class Bank extends Account {

    private Devenger devenger;
    
    public Bank(int days) {
        this.devenger = new Devenger(this, days);
    }

    
    private boolean operationIsConsolidated(Operation operation) {
        return this.getOperations().contains(operation);
    }
    @Override
    public void addOperation(Operation operation) {
        this.devenger.addOperation(operation);
    }
    @Override
    public void removeOperation(Operation operation) {
       if(this.operationIsConsolidated(operation)) {
           super.getOperations().remove(operation);
           this.updateAccountBalance(-operation.getAmount());
       } else {
           this.devenger.removeOperation(operation);
       }
    }
    

    @Override
    public void consolidate() {
        double consolidatedAmount = this.devenger.consolidateOperations();
        this.updateAccountBalance(consolidatedAmount);
    }

}
