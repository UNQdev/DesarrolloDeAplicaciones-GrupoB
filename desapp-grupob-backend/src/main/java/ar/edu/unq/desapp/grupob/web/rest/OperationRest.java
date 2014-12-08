package ar.edu.unq.desapp.grupob.web.rest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.*;
import ar.edu.unq.desapp.grupob.model.builders.OperationBuilder;
import ar.edu.unq.desapp.grupob.services.*;
import ar.edu.unq.desapp.grupob.utils.OperationParse;

@Service
@Path("/operationService")
public class OperationRest {
    
    @Autowired
    private OperationService operationService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private SubCategoryService subCategoryService;
    
    @Autowired
    private AccountService accountService;

    @GET
    @Path("/operations")
    @Produces("application/json")
    public List<Operation> getOperations() {
        return getOperationService().retriveAll();
    }

    @GET
    @Path("/operation/{id}")
    @Produces("application/json")
    public Operation getOperation(@PathParam("id") final int id) {
        Operation operation = getOperationService().getById(id);
        return operation;
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveCategory(@Multipart(value = "operation", type = "application/json") final String jsonOperation) {
        try {
            OperationParse operationParsed = parseOperation(jsonOperation);
            Operation operationToSave = mkOperation(operationParsed);
            getAccountService().update(addOperationAccordingLinkedAccount(operationToSave));
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.status(200).build();
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public Response updateCategory(@Multipart(value = "operation", type = "application/json") final String jsonOperation) {
        try {
            OperationParse operationParsed = parseOperation(jsonOperation);
            
            Operation operationTobeUpdated  = getOperationService().getById(operationParsed.getId());
            
            operationTobeUpdated.setConcept(operationParsed.getConcept());
            operationTobeUpdated.setShift(operationParsed.getShift());
            
            getOperationService().update(operationTobeUpdated);
        } catch (Exception e) {
            return Response.status(Status.METHOD_NOT_ALLOWED).build();
        }
        return Response.ok().status(200).build();
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
    
    private OperationParse parseOperation(final String jsonOperation) {
        OperationParse newOperationParse = new OperationParse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            newOperationParse = mapper.readValue(jsonOperation, OperationParse.class);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return newOperationParse;
    }

    private Operation mkOperation(OperationParse operationParsed) {
        Operation operation = OperationBuilder.aOperationBuilder()
                .withAccount(operationParsed.getAccountType())
                .withAmount(operationParsed.getAmount())
                .withConcept(operationParsed.getConcept())
                .withDate(operationParsed.getDate())
                .withShift(operationParsed.getShift())
                .withType(operationParsed.getType())
                .withCategory(getCategoryService().getById(operationParsed.getCategory()))
                .withSubCategory(getSubCategoryService().getById(operationParsed.getSubcategory()))
                .build();
        operation.setCardType(operationParsed.getCardType());
        operation.setId(operationParsed.getId());
        return operation;
    }
    
    private GenericService<Operation> getOperationService() {
        return this.operationService;
    }

    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }
    
    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public SubCategoryService getSubCategoryService() {
        return subCategoryService;
    }

    public void setSubCategoryService(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
