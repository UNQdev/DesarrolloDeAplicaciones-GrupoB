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

    private CashAccountService cashAccountService;
    private BankAccountService bankAccountService;
    private CurrentAccountService currentAccountService;
    private CategoryService categoryService;
    private OperationService operationService;
    private InvoiceService invoiceService;

    @GET
    @Path("/initialize-db")
    public void dbInit() {
        
        // Accounts
//        CashAccount cash = new CashAccount();
//        BankAccount bank = new BankAccount(2);
//        CurrentAccount current = new CurrentAccount();
//        getCashAccountService().save(cash);
//        getBankAccountService().save(bank);
//        getCurrentAccountService().save(current);
        
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
//        Operation operation1 = new Operation(OperationType.Outcoming,
//                Shift.Afternoon, DateTime.parse("2014-10-01T04:00"), 359.00,
//                new Invoice(), compras,
//                "Insumos de libreria SEPTIEMBRE 2014", current);
//        Operation operation2 = new Operation(OperationType.Outcoming,
//                Shift.Beforenoon, DateTime.parse("2014-10-01T010:15"), 544.29,
//                new Invoice(), compras,
//                "Articulos de limpieza SEPTIEMBRE 2014", bank);
//        Operation operation3 = new Operation(OperationType.Incoming,
//                Shift.Afternoon, DateTime.parse("2014-09-01T02:30"), 12.00,
//                new Invoice(), ventas, "Capitan Triple x1", cash);
//        getOperationService().save(operation1);
//        getOperationService().save(operation2);
//        getOperationService().save(operation3);
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

    public void setCurrentAccountService(
            CurrentAccountService currentAccountService) {
        this.currentAccountService = currentAccountService;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
}
