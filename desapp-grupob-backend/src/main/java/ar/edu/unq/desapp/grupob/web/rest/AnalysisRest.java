package ar.edu.unq.desapp.grupob.web.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.services.CategoryAnalysisService;
import ar.edu.unq.desapp.grupob.services.OperationAnalysisService;
import ar.edu.unq.desapp.grupob.services.VendorAnalysisService;

@Service
@Path("/analysisService")
public class AnalysisRest {

	private CategoryAnalysisService categoryAnalysisService;
	private VendorAnalysisService vendorAnalysisService;
	private OperationAnalysisService operationAnalysisService;

	@GET
	@Path("/categoriesIncomingTotals")
	@Produces("application/json")
	public HashMap<String, Double> getCategoriesIncomingTotals() {
		return getCategoryAnalysisService().calculateCategoriesIncomingTotals();
	};
	@GET
	@Path("/categoriesOutcomingTotals")
	@Produces("application/json")
	public HashMap<String, Double> getCategoriesOutcomingTotals() {
		return getCategoryAnalysisService().calculateCategoriesOutcomingTotals();
	};
	
	@GET
	@Path("/subCategoriesIncomingTotals")
	@Produces("application/json")
	public HashMap<String, Double> getSubCategoriesTotals() {
		return getCategoryAnalysisService().calculateSubCategoriesIncomingTotals();
	};
	@GET
	@Path("/subCategoriesOutcomingTotals")
	@Produces("application/json")
	public HashMap<String, Double> getSubCategoriesOutcomingTotals() {
		return getCategoryAnalysisService().calculateSubCategoriesIncomingTotals();
	};
	
	@GET
	@Path("/monthIncomingAccrual")
	@Produces("application/json")
	public HashMap<String, Double> getMonthIncomingAccrual() {
		return getOperationAnalysisService().calculateMonthIncoming();
	};
	@GET
	@Path("/monthOutcomingAccrual")
	@Produces("application/json")
	public HashMap<String, Double> getMonthOutcomingAccrual() {
		return getOperationAnalysisService().calculateMonthOutcoming();
	};
	
	@GET
	@Path("/vendorsTotals")
	@Produces("application/json")
	public HashMap<String, Double> getVendorsTotals() {
		return getVendorAnalysisService().calculateVendorsTotals();
	}

	
	public CategoryAnalysisService getCategoryAnalysisService() {
		return categoryAnalysisService;
	}

	public void setCategoryAnalysisService(
			CategoryAnalysisService categoryAnalysisService) {
		this.categoryAnalysisService = categoryAnalysisService;
	}

	public VendorAnalysisService getVendorAnalysisService() {
		return vendorAnalysisService;
	}

	public void setVendorAnalysisService(VendorAnalysisService vendorAnalysisService) {
		this.vendorAnalysisService = vendorAnalysisService;
	}

	public OperationAnalysisService getOperationAnalysisService() {
		return operationAnalysisService;
	}

	public void setOperationAnalysisService(
			OperationAnalysisService operationAnalysisService) {
		this.operationAnalysisService = operationAnalysisService;
	};
	
}
