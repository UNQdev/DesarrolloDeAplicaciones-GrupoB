package ar.edu.unq.desapp.grupob.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.*;
import ar.edu.unq.desapp.grupob.services.*;

@Service
@Path("/db")
public class DBInitializationRest {

	private CashAccountService cashAccountService;
	private BankAccountService bankAccountService;
	private CurrentAccountService currentAccountService;
	private SubCategoryService subcategoryService;
	private CategoryService categoryService;
	private OperationService operationService;

	@GET
	@Path("/initialize")
	public void dbInit() {

		//Accounts
		CashAccount cash = new CashAccount();
		BankAccount bank = new BankAccount(2);
		CurrentAccount current = new CurrentAccount();

		getCashAccountService().save(cash);
		getBankAccountService().save(bank);
		getCurrentAccountService().save(current);

		//Categories & SubCategories
		Category compras = new Category("Compras");
		SubCategory insumos = new SubCategory("Insumos Libreria");
		SubCategory limpieza = new SubCategory("Art Limpieza");
		compras.createSubCategory(insumos);
		compras.createSubCategory(limpieza);
		Category ventas = new Category("Ventas");
		SubCategory alfajores = new SubCategory("Alfajores");
		compras.createSubCategory(alfajores);
		
		getCategoryService().save(compras);
		getCategoryService().save(ventas);
		getSubcategoryService().save(insumos);
		getSubcategoryService().save(limpieza);
		getSubcategoryService().save(alfajores);

		//Operations
		Operation operation1 = new Operation(
				OperationType.Outcoming, 
				Shift.Afternoon,
				DateTime.parse("2014-10-01T04:00"),
	            359.00, 
	            new Invoice(), 
	            compras,
	            insumos,
	            "Insumos de libreria SEPTIEMBRE 2014",
	            current);
	    Operation operation2 = new Operation(
	    		OperationType.Outcoming,
				Shift.Beforenoon,
				DateTime.parse("2014-10-01T010:15"),
	            544.29, 
	            new Invoice(),
	            compras,
	            limpieza,
	            "Articulos de limpieza SEPTIEMBRE 2014",
	            bank);
        Operation operation3 = new Operation(
	    		OperationType.Incoming, 
	    		Shift.Afternoon,
	    		DateTime.parse("2014-09-01T02:30"),
	            12.00,
	            new Invoice(),
	            ventas,
	            alfajores,
	            "Capitan Triple x1",
	            cash);

		getOperationService().save(operation1);
		getOperationService().save(operation2);
		getOperationService().save(operation3);
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public SubCategoryService getSubcategoryService() {
		return subcategoryService;
	}
	
	public void setSubcategoryService(SubCategoryService subcategoryService) {
		this.subcategoryService = subcategoryService;
	}

	public OperationService getOperationService() {
		return operationService;
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public CashAccountService getCashAccountService() {
		return cashAccountService;
	}

	public void setCashAccountService(CashAccountService cashAccountService) {
		this.cashAccountService = cashAccountService;
	}

	public BankAccountService getBankAccountService() {
		return bankAccountService;
	}

	public void setBankAccountService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}

	public CurrentAccountService getCurrentAccountService() {
		return currentAccountService;
	}

	public void setCurrentAccountService(CurrentAccountService currentAccountService) {
		this.currentAccountService = currentAccountService;
	}
}