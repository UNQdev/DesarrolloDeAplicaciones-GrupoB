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
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.*;
import ar.edu.unq.desapp.grupob.services.*;

@Service
@Path("/operationService")
public class OperationRest {
    private OperationService operationService;

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
            System.out.println(jsonOperation);
            Operation operation = parseOperation(jsonOperation);
            System.out.println(operation);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.status(200).build();
    }

    @GET
    @Path("/update/{id}")
    @Produces("application/json")
    public Operation updateOperation(@PathParam("id") final int id) {
        Operation operation = getOperationService().getById(id);
        getOperationService().update(operation);
        return operation;
    }

    @GET
    @Path("/delete/{id}")
    @Produces("application/json")
    public Operation deleteOperation(@PathParam("id") final int id) {
        Operation operation = this.getOperation(id);
        getOperationService().delete(operation);
        return operation;
    }
    
    private Operation parseOperation(final String jsonOperation) throws Exception {
        Operation newOperation = new Operation();
        ObjectMapper mapper = new ObjectMapper();
        try {
            newOperation = mapper.readValue(jsonOperation, Operation.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newOperation;
    }

    private GenericService<Operation> getOperationService() {
        return this.operationService;
    }

    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }
}
