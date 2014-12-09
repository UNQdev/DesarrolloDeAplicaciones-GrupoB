package ar.edu.unq.desapp.grupob.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.*;
import ar.edu.unq.desapp.grupob.model.builders.InvoiceBuilder;
import ar.edu.unq.desapp.grupob.model.builders.OperationBuilder;
import ar.edu.unq.desapp.grupob.model.builders.PaymentBuilder;
import ar.edu.unq.desapp.grupob.services.*;
import ar.edu.unq.desapp.grupob.utils.OperationParse;
import ar.edu.unq.desapp.grupob.utils.PaymentParse;

@Service
@Path("/paymentService")
public class PaymentRest {

    @Autowired
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;
    private OperationService operationService;
    private InvoiceService invoiceService;
    private PaymentService paymentService;
    private VendorService vendorService;
    private AccountService accountService;

    @GET
    @Path("/payments")
    @Produces("application/json")
    public List<Payment> getPayments() {
        return getPaymentService().retriveAll();
    }

    @GET
    @Path("/payment/{id}")
    @Produces("application/json")
    public Payment getPayment(@PathParam("id") final int id) {
        Payment payment = getPaymentService().getById(id);
        return payment;
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveCategory(@Multipart(value = "payment", type = "application/json") final String jsonPayment) {
        try {
            PaymentParse paymentParsed = parsePayment(jsonPayment);
            Payment paymentToSave = mkPayment(paymentParsed);
            getPaymentService().update(paymentToSave);
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.status(200).build();
    }

    private PaymentParse parsePayment(final String jsonPayment) {
        PaymentParse newPaymentParse = new PaymentParse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            newPaymentParse = mapper.readValue(jsonPayment, PaymentParse.class);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return newPaymentParse;
    }
    
    private Payment mkPayment(PaymentParse paymentParsed) throws Exception {
        
        Operation operation = OperationBuilder.aOperationBuilder()
                .withAccount(paymentParsed.getAccountType())
                .withAmount(paymentParsed.getAmount()) //deberia ser el monto total, dependiendo del tipo de factura
                .withConcept(paymentParsed.getConcept())
                .withDate(paymentParsed.getDate())
                .withShift(paymentParsed.getShift())
                .withType(paymentParsed.getType())
                .withCategory(getCategoryService().getById(paymentParsed.getCategory()))
                .withSubCategory(getSubCategoryService().getById(paymentParsed.getSubcategory()))
                .build();
        operation.setCardType(paymentParsed.getCardType());
        operation.setId(paymentParsed.getId());
        
        getAccountService().update(addOperationAccordingLinkedAccount(operation));
        
        
        
        Invoice invoice = InvoiceBuilder.anInvoiceBuilder()
                 .withDate(paymentParsed.getDate())
                 .withDescription(paymentParsed.getConcept())
                 .withInvoiceType(paymentParsed.getInvoiceType())
                 .withNumber(paymentParsed.getInvoiceNumber())
                 .withTaxCode(paymentParsed.getVendorTaxCode())
                 .withVendor(getVendorService().getById(paymentParsed.getVendorId()))
                 .withSubTotal(paymentParsed.getAmount())
                 .buildA(); //withTax? withTotal? withHasIIBB? withNoGrabado?
                 
        Payment payment = PaymentBuilder.aPaymentBuilder()
                .withInputDate(paymentParsed.getDate())
                .withOperation(operation)
                .withInvoice(invoice)
                .build();

        return payment;
    }

    private Account addOperationAccordingLinkedAccount(Operation operationToSave) throws Exception {
        String accountTypeToString = operationToSave.getAccountType().name();
        switch (accountTypeToString) {
        case "Cash":
            Account cashAccount = getAccountService().getById(1);
            cashAccount.addOperation(operationToSave);
            return cashAccount;
        case "Bank":
            Account bankAccount = getAccountService().getById(2);
            bankAccount.addOperation(operationToSave);
            return bankAccount;
        case "Current":
            Account currentAccount = getAccountService().getById(3);
            currentAccount.addOperation(operationToSave);
            return currentAccount;    
        default:
            //by default
            Account account = getAccountService().getById(1);
            account.addOperation(operationToSave);
            return account;
        }
    }
    
    // SERVICES GETTERS & SETTERS
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

    public SubCategoryService getSubCategoryService() {
        return subCategoryService;
    }

    public void setSubCategoryService(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    public VendorService getVendorService() {
        return vendorService;
    }

    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
