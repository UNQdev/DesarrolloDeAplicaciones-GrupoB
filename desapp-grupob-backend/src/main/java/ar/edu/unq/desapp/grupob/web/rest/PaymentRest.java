package ar.edu.unq.desapp.grupob.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.*;
import ar.edu.unq.desapp.grupob.services.*;

@Service
@Path("/paymentService")
public class PaymentRest {

    @Autowired
    private CategoryService categoryService;
    private SubCategoryService subcategoryService;
    private OperationService operationService;
    private InvoiceService invoiceService;
    private PaymentService paymentService;

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

    @GET
    @Path("/save/{id}")
    @Produces("application/json")
    public Payment savePayment(@PathParam("id") final int id) {
        Payment payment = new Payment();
        getPaymentService().save(payment);
        return payment;
    }

    @GET
    @Path("/update/{id}/{name}")
    @Produces("application/json")
    public Payment updatePayment(@PathParam("id") final int id,
            @PathParam("name") final String name) {
        Payment operation = getPaymentService().getById(id);
        getPaymentService().update(operation);
        return operation;
    }

    @GET
    @Path("/delete/{id}")
    @Produces("application/json")
    public Payment deletePayment(@PathParam("id") final int id) {
        Payment payment = this.getPayment(id);
        getPaymentService().delete(payment);
        return payment;
    }


    // SERVICES GETTERS & SETTERS
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
