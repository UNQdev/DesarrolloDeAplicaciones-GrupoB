package ar.edu.unq.desapp.grupob.web.rest;

import java.util.List;

import javax.json.JsonException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.feature.Features;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.Account;
import ar.edu.unq.desapp.grupob.model.BankAccount;
import ar.edu.unq.desapp.grupob.services.AccountService;

@Service
@Path("/accountService")
public class AccountRest {
	
    private static final int HTTP_OK_CREATED = 201;

    private static final int HTTP_OK = 200;    

    private static final int HTTP_DELETE = 204;

    @Autowired
    private AccountService accountService;
    
    @GET
    @Path("/accounts")
    @Produces("application/json")
    public List<Account> getAccounts() {
        return getAccountService().retriveAll();
    }

    @GET
    @Path("/accountsConsolidation")
    @Produces("application/json")
    public Response consolidateAccounts() {
    	List<Account> accounts = getAccountService().retriveAll();

    	for(Account account : accounts) {
    		try {
				account.consolidate();
			} catch (Exception e) {
				return Response.serverError().entity(e.getMessage()).build();
			}
    	}

    	return Response.ok().status(HTTP_OK).build();
    }

    @GET
    @Path("/byId/{id}")
    @Produces("application/json")
    public Account getAccountById(@PathParam("id") final int id) {
        return getAccountService().getById(id) ;
    }
    
    @GET
    @Path("/byName/{name}")
    @Produces("application/json")
    public Response getAccountByName(@PathParam("name") final String name) {
        Account account = getAccountService().getByName(name);
        if (account != null ){
            return Response.ok().status(HTTP_OK).entity(account).build();
           
        }
        else {
            return Response.serverError().status(400).build();
        }
    }

    @GET
    @Path("/filterByName/{name}")
    @Produces("application/json")
    public List<Account> filterByName(@PathParam("accountName") final String name) {
        return getAccountService().filterByName(name);
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveAccount(@Multipart(value = "account", type = "application/json") final String jsonCategory) {
        try {
        	getAccountService().save(parseAccount(jsonCategory));
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.status(HTTP_OK_CREATED).build();
    }
    
    @PUT
    @Path("/{categoryId}")
    @Consumes("application/json")
    public Response updateAccount(@PathParam("accountId") final String accountId, final String jsonCategory) {
        try {
        	getAccountService().update(parseAccount(jsonCategory));
        } catch (Exception e) {
            return Response.status(Status.METHOD_NOT_ALLOWED).build();
        }
        return Response.ok().status(HTTP_OK).build();
    }
    

    private Account parseAccount(final String jsonCategory) throws Exception {
        Account newAccount = new BankAccount();
        ObjectMapper mapper = new ObjectMapper();
        newAccount = mapper.readValue(jsonCategory, Account.class);
        return newAccount;
    }
    

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}