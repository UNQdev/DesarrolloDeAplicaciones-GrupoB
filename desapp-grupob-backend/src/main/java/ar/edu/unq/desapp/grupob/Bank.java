package ar.edu.unq.desapp.grupob;

public class Bank extends Account {

    private Devenger devenger;
    
    
    private boolean operationIsConsolidated(Operation operation) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeOperation(Operation operation) {
       if(this.operationIsConsolidated(operation)) {
           super.getOperations().remove(operation);
           this.updateAccountBalance(operation.getAmount());
       } else {
           this.devenger.removeOperation(operation);
       }
    }

    @Override
    public void updateAccountBalance(double amount) {
        // TODO Auto-generated method stub
        
    }

}
