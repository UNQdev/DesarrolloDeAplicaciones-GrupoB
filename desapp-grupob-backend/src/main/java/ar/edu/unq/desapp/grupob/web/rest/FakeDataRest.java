package ar.edu.unq.desapp.grupob.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.*;
import ar.edu.unq.desapp.grupob.model.builders.VendorBuilder;
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
    public void dbInit() throws Exception {
        
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
        
        Category gastosGenerales = new Category ("GastosGenerales");
        gastosGenerales.createSubCategory(new SubCategory("Luz"));
        gastosGenerales.createSubCategory(new SubCategory("Gas"));
        gastosGenerales.createSubCategory(new SubCategory("Agua"));
        
        Category logistica = new Category ("Logistica");
        logistica.createSubCategory(new SubCategory("Remis"));
        
        getCategoryService().save(logistica);
        getCategoryService().save(gastosGenerales);
        getCategoryService().save(compras);
        getCategoryService().save(ventas);
        
        // Operations
        Operation operation1 = new Operation(
        		OperationType.Outcoming, Shift.Afternoon,
        		DateTime.now().minusDays(3), 120.00,
                "",
                compras, insumos,
                current.getAccountName());
        Operation operation2 = new Operation(
        		OperationType.Outcoming, Shift.Beforenoon, 
        		DateTime.now().minusDays(2), 658.59,
        		"",
                compras, limpieza,
                bank.getAccountName());
        operation2.setCardType(CardType.Credit);
        Operation operation3 = new Operation(
        		OperationType.Incoming, Shift.Afternoon,
        		DateTime.now().minusDays(1), 12.00,
        		"Capitan Triple x1",
        		ventas, alfajores,
        		cash.getAccountName());
        
        cash.addOperation(operation3);
        bank.addOperation(operation2);
        current.addOperation(operation1);
        getAccountService().save(cash);
        getAccountService().save(bank);
        getAccountService().save(current);

        Vendor pepito = VendorBuilder.aVendorBuilder().withName("Pepito").withTaxCode("20-32830432-6").build();
        Vendor arnaldo = VendorBuilder.aVendorBuilder().withName("Arnaldo").withTaxCode("20-35401778-6").build();
        // Payments
        Invoice invoice1 = new Invoice(
        		"0000001", 									// numero de factura
        		DateTime.now().minusDays(5),				// fecha factura 
        		pepito, 		                            // proveedor
        		InvoiceType.A,								// tipo factura
                "Insumos de libreria SEPTIEMBRE 2014", 		// descripcion
                100.00); 									// monto neto
        Payment payment1 = new Payment(DateTime.now(), invoice1, operation1);
        getPaymentService().save(payment1);
        
        Invoice invoice2 = new Invoice(
        		"0000002", 									// numero de factura
        		DateTime.now().minusDays(4),				// fecha factura 
        		arnaldo, 	                                // proveedor
        		InvoiceType.A,								// tipo factura
        		"Articulos de limpieza SEPTIEMBRE 2014", 	// descripcion
                544.29); 									// monto neto
        Payment payment2 = new Payment(DateTime.now(), invoice2, operation2);
        getPaymentService().save(payment2);

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
