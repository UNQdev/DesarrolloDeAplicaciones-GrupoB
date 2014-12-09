package ar.edu.unq.desapp.grupob.services;

import java.util.HashMap;

import ar.edu.unq.desapp.grupob.model.Vendor;

/**
 * 
 * @author bananee
 *
 */
public class VendorAnalysisService extends GenericService<Vendor> {

	private static final long serialVersionUID = -2824876191788750311L;

	private static OperationService operationService;
	private static InvoiceService invoiceService;
	
	public HashMap<String, Double> calculateVendorsTotals() {
		HashMap<String, Double> vendorsTotals = new HashMap<String, Double>();
		
		//Get VENDORS name domain
		
		
		//Get OPERATIONS from COMPRAS CATEGORY
		
		
		//Iterate operations collection those for the vendors and add
		
		
		
		return vendorsTotals;
	}

	public static OperationService getOperationService() {
		return operationService;
	}

	public static void setOperationService(OperationService operationService) {
		VendorAnalysisService.operationService = operationService;
	}

	public static InvoiceService getInvoiceService() {
		return invoiceService;
	}

	public static void setInvoiceService(InvoiceService invoiceService) {
		VendorAnalysisService.invoiceService = invoiceService;
	}

	
}
