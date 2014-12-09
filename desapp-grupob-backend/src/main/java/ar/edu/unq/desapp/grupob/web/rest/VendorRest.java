package ar.edu.unq.desapp.grupob.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unq.desapp.grupob.model.Vendor;
import ar.edu.unq.desapp.grupob.services.VendorService;

@Service
@Path("/vendorService")
public class VendorRest {
    
    @Autowired
    private VendorService vendorService;

    @GET
    @Path("/vendors")
    @Produces("application/json")
    public List<Vendor> getCategories() {
        return getVendorService().retriveAll();
    }
    
    @GET
    @Path("/byName/{name}")
    @Produces("application/json")
    public Response getCategoryByName(@PathParam("name") final String name) {
        Vendor vendor = getVendorService().getByName(name);
        return Response.ok().entity(vendor).build();
    }
    
    @GET
    @Path("/byId/{id}")
    @Produces("application/json")
    public Vendor getCategoryById(@PathParam("id") final int id) {
        return getVendorService().getById(id) ;
    }
    
    public VendorService getVendorService() {
        return vendorService;
    }

    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

}
