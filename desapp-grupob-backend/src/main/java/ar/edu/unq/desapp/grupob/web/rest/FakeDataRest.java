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
    private PaymentService paymentService;

    @GET
    @Path("/initialize-db")
    public void dbInit() {
        
        // Accounts
        CashAccount cash = new CashAccount();
        BankAccount bank = new BankAccount(2);
        CurrentAccount current = new CurrentAccount();
        
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
        		DateTime.now(), 120.00,
                "",
                compras, insumos,
                current.getAccountName());
        Operation operation2 = new Operation(
        		OperationType.Outcoming, Shift.Beforenoon, 
        		DateTime.now(), 544.29,
                "Articulos de limpieza SEPTIEMBRE 2014",
                compras, limpieza,
                bank.getAccountName());
        Operation operation3 = new Operation(
        		OperationType.Incoming, Shift.Afternoon,
        		DateTime.now(), 12.00,
        		"Capitan Triple x1",
        		ventas, alfajores,
        		cash.getAccountName());
        
        cash.addOperation(operation3);
        bank.addOperation(operation2);
        current.addOperation(operation1);
        getAccountService().save(cash);
        getAccountService().save(bank);
        getAccountService().save(current);

        // Payment
        Invoice invoice = new Invoice(
        		"0000001", 									// numero de factura
        		DateTime.now().minusDays(5),				// fecha factura 
        		new Vendor("20-32830432-6", "Pepito"), 		// proveedor
        		InvoiceType.A,								// tipo factura
                "Insumos de libreria SEPTIEMBRE 2014", 		// descripcion
                100.00); 									// monto neto
        Payment payment = new Payment(DateTime.now(), invoice, operation1);
        getPaymentService().save(payment);
        
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

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
}
