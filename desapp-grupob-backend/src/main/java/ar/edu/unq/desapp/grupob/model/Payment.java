package ar.edu.unq.desapp.grupob.model;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.builders.InvoiceBuilder;
import ar.edu.unq.desapp.grupob.model.builders.OperationBuilder;

public class Payment extends Entity {
    private static final long serialVersionUID = 4150568739587426311L;
    /*
     * PROPERTIES
     */
    private DateTime inputDate;
    private Invoice invoice;
    private Operation operation;

    /*
     * CONSTRUCTORS
     */
    public Payment () {};
    
    public Payment(
    		DateTime inputDate,
    		/**
    		 * INVOICE REQUIREMENTS
    		 */
    		Vendor invoiceVendor, 				//LA FC NECESITA EL OBJETO VENDOR (RESPONSABILIDAD DEL CONTROLLER)
    		String invoiceNumber,
    		DateTime invoiceDate,
    		double invoiceSubTotal,
    		double invoiceTotal,
    		InvoiceType invoiceType,
    		String invoiceTaxCode,				//REPLANTEAR ESTO! QUE REPRESENTABA
    		String invoiceDescription,
    		/**
    		 * OPERATION REQUIREMENTS
    		 */
    		AccountType operationAccount,
    		Category operationCategory,			//PODRIA TRAERSE UNA CUENTA DEPENDIENDO EL VENDOR
    		SubCategory operationSubcategory,
    		Shift operationShift					//RESPONSABILIDAD DEL SISTEMA
    		) {

        this.setInputDate(inputDate);

        this.setInvoice(
        		InvoiceBuilder.anInvoiceBuilder()
        		.withVendor(invoiceVendor)
        		.withNumber(invoiceNumber)
        		.withDate(invoiceDate)
        		.withSubTotal(invoiceSubTotal)
        		.withTotal(invoiceTotal)
        		.withInvoiceType(invoiceType)
        		.withTaxCode(invoiceTaxCode)
        		.withDescription(invoiceDescription)
        		.build());

        this.setOperation(
        		OperationBuilder.aOperationBuilder()
	        		.withType(OperationType.Outcoming)
	        		.withAmount(invoiceTotal)
//	        		.withAccount(operationAccount)
	        		.withCategory(operationCategory)
	        		.withSubCategory(operationSubcategory)
	        		.withConcept("Cancelacion Pago " + invoiceVendor.getName() + " - " + invoiceDescription)
	        		.withShift(operationShift)
	        		.withDate(inputDate)
	        		.build());
    }
    
    public DateTime getInputDate() {
        return inputDate;
    }

    public void setInputDate(DateTime inputDate) {
        this.inputDate = inputDate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
