package ar.edu.unq.desapp.grupob.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import ar.edu.unq.desapp.grupob.model.*;
import ar.edu.unq.desapp.grupob.services.*;

@Service
@Path("/app")
public class FakeDataRest {

	private AccountService accountService;
    private CategoryService categoryService;
    private OperationService operationService;
    private InvoiceService invoiceService;

    @GET
    @Path("/initialize-db")
    public void dbInit() {
        
        // Accounts
        CashAccount cash = new CashAccount();
        BankAccount bank = new BankAccount(2);
        CurrentAccount current = new CurrentAccount();
        getAccountService().save(cash);
        getAccountService().save(bank);
        getAccountService().save(current);
        
        // Categories & SubCategories
        Category compras = new Category("Compras");
        
        SubCategory insumos = new SubCategory("Insumos Libreria");
        SubCategory limpieza = new SubCategory("Art Limpieza");
        compras.createSubCategory(insumos);
        compras.createSubCategory(limpieza);
        
        Category ventas = new Category("Ventas");
        SubCategory alfajores = new SubCategory("Alfajores");
        ventas.createSubCategory(alfajores);
        
        getCategoryService().save(compras);
        getCategoryService().save(ventas);
        
        // Operations
        Operation operation1 = new Operation(
        		OperationType.Outcoming, Shift.Afternoon,
        		DateTime.now(), 359.00,
                "Insumos de libreria SEPTIEMBRE 2014",
                compras, insumos,
                current);
        Operation operation2 = new Operation(
        		OperationType.Outcoming, Shift.Beforenoon, 
        		DateTime.now(), 544.29,
                "Articulos de limpieza SEPTIEMBRE 2014",
                compras, limpieza,
                bank);
        Operation operation3 = new Operation(
        		OperationType.Incoming, Shift.Afternoon,
        		DateTime.now(), 12.00,
        		"Capitan Triple x1",
        		ventas, alfajores,
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

    public OperationService getOperationService() {
        return operationService;
    }

    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
}
