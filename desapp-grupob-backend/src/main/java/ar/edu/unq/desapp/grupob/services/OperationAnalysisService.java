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

	public HashMap<String, Double> calculateMonthIncoming() {
		HashMap<String, Double> monthIncomingHistory = new HashMap<String, Double>();
		List<Operation> monthOperations = operationService.getMonthOperations(DateTime.now());

		for(Operation operation : monthOperations) {
			String operationDate = operation.getDateToString();
			if (monthIncomingHistory.containsKey(operationDate) && operation.getRealAmount() >0) {
				monthIncomingHistory.put(operationDate, monthIncomingHistory.get(operationDate) + operation.getAmount());
			} else {
				monthIncomingHistory.put(operationDate, operation.getAmount());
			}
		}
		return monthIncomingHistory;
	}

	public HashMap<String, Double> calculateMonthOutcoming() {
		HashMap<String, Double> monthOutcomingHistory = new HashMap<String, Double>();
		List<Operation> monthOperations = operationService.getMonthOperations(DateTime.now());

		for(Operation operation : monthOperations) {
			String operationDate = operation.getDateToString();
			if (monthOutcomingHistory.containsKey(operationDate) && operation.getRealAmount() <0) {
				monthOutcomingHistory.put(operationDate, monthOutcomingHistory.get(operationDate) + operation.getAmount());
			} else {
				monthOutcomingHistory.put(operationDate, operation.getAmount());
			}			
		}
		return monthOutcomingHistory;
	}


	public HashMap<String, Double> calculateMonthAfternoonIncoming() {
		return null;
	}

	public HashMap<String, Double> calculateMonthBeforenoonIncoming() {
		return null;
	}

	public HashMap<String, Double> calculateMonthAfternoonOutcoming() {
		return null;
	}

	public HashMap<String, Double> calculateMonthBeforenoonOutcoming() {
		return null;
	}

	public static OperationService getOperationService() {
		return operationService;
	}

	public static void setOperationService(OperationService operationService) {
		OperationAnalysisService.operationService = operationService;
	}

	
}
