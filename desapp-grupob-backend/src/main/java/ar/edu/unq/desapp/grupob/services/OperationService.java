package ar.edu.unq.desapp.grupob.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.Operation;

public class OperationService extends GenericService<Operation> {

    private static final long serialVersionUID = -4657650741365493072L;
    
	public List<Operation> getMonthOperations(DateTime today) {
		List<Operation> monthOperations = this.filterByDateMonth(today.getMonthOfYear());
		return monthOperations;
	}

	public List<Operation> getWeekOperation(DateTime today) {
		List<Operation> monthOperations = this.filterByDateWeek(today.getWeekyear());
		return monthOperations;
	}

	public List<Operation> filterByDateMonth(int monthOfTheYear) {
		List<Operation> allOperations = new ArrayList<Operation>();
		allOperations = this.retriveAll();
		System.out.println("ALL OPERATIONS SIZE" + allOperations.size());
		List<Operation> filteredOperations = new ArrayList<Operation>();
		
		for(Operation operation : allOperations) {
			if(operation.getDate().getMonthOfYear() == monthOfTheYear &&
					operation.getDate().getYear() == DateTime.now().getYear()) {
				filteredOperations.add(operation);
			}
		}
		
		System.out.println("FILTERED OPERATIONS SIZE" + filteredOperations.size());
		return filteredOperations;
	};

	public List<Operation> filterByDateWeek(int weekOfTheYear) {
		List<Operation> allOperations = new ArrayList<Operation>();
		List<Operation> filteredOperations = new ArrayList<Operation>();
		
		for(Operation operation : allOperations) {
			if(operation.getDate().getMonthOfYear() == weekOfTheYear &&
					operation.getDate().getYear() == DateTime.now().getYear()) {
				filteredOperations.add(operation);
			}
		}
		return filteredOperations;
	};
}
