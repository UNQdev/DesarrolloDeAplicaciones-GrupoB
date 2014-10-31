package ar.edu.unq.desapp.grupob.web.rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.stereotype.Service;
import ar.edu.unq.desapp.grupob.model.*;
import ar.edu.unq.desapp.grupob.services.*;

@Service
@Path("/invoiceService")
public class InvoiceRest {
    
    private InvoiceService invoiceService;

    @GET
    @Path("/invoices")
    @Produces("application/json")
    public List<Invoice> getInvoices() {
        return getInvoiceService().retriveAll();
    }

    @GET
    @Path("/invoice/{id}")
    @Produces("application/json")
    public Invoice getInvoice(@PathParam("id") final int id) {
        Invoice invoice = getInvoiceService().getById(id);
        return invoice;
    }

    @GET
    @Path("/save/{id}")
    @Produces("application/json")
    public Invoice saveInvoice(@PathParam("id") final int id) {
        Invoice invoice = new Invoice();
        getInvoiceService().save(invoice);
        return invoice;
    }

    @GET
    @Path("/update/{id}/{name}")
    @Produces("application/json")
    public Invoice updateInvoice(@PathParam("id") final int id,
            @PathParam("name") final String name) {
        Invoice invoice = getInvoiceService().getById(id);
        getInvoiceService().update(invoice);
        return invoice;
    }

    @GET
    @Path("/delete/{id}")
    @Produces("application/json")
    public Invoice deleteOperation(@PathParam("id") final int id) {
        Invoice operation = this.getInvoice(id);
        getInvoiceService().delete(operation);
        return operation;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
}
