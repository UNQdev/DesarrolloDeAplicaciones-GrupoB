package ar.edu.unq.desapp.grupob.services;

import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.Operation;

/**
 * 
 * @author bananee
 *
 */
public class OperationAnalysisService extends GenericService<Operation> {

	private static final long serialVersionUID = 4978250321454497848L;

	private static OperationService operationService; 

	public HashMap<String, Double> calculateMonthAccrual() {
		HashMap<String, Double> monthHistory = new HashMap<String, Double>();
		List<Operation> monthOperations = operationService.getMonthOperations(DateTime.now());
		
		for(Operation operation : monthOperations) {
			String operationDate = operation.getDateToString();
			if (monthHistory.containsKey(operationDate)) {
				monthHistory.put(operationDate, monthHistory.get(operationDate) + operation.getRealAmount());
			} else {
				monthHistory.put(operationDate, operation.getRealAmount());
			}			
		}

		return monthHistory;
	}

	public static OperationService getOperationService() {
		return operationService;
	}

	public static void setOperationService(OperationService operationService) {
		OperationAnalysisService.operationService = operationService;
	}

	
}
