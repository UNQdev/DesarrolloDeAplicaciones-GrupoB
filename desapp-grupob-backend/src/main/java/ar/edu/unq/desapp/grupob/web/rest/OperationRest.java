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

    @GET
    @Path("/save/{id}")
    @Produces("application/json")
    public Operation saveOperation(@PathParam("id") final int id) {
        Operation operation = new Operation();
        getOperationService().save(operation);
        return operation;
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

    private GenericService<Operation> getOperationService() {
        return this.operationService;
    }

    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }
}
