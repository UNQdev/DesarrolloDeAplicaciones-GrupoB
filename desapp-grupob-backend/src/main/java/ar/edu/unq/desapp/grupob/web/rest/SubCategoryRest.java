package ar.edu.unq.desapp.grupob.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.SubCategory;
import ar.edu.unq.desapp.grupob.services.SubCategoryService;

@Service
@Path("/subcategoryService")
public class SubCategoryRest {

    private SubCategoryService subCategoryService;

    @GET
    @Path("/subcategories")
    @Produces("application/json")
    public List<SubCategory> getCategories() {
        return getSubCategoryService().retriveAll();
    }

    @GET
    @Path("/filterByName/{name}")
    @Produces("application/json")
    public List<SubCategory> filterByName(@PathParam("name") final String name) {
        return getSubCategoryService().filterByName(name);
    }

    public SubCategoryService getSubCategoryService() {
        return subCategoryService;
    }

    public void setSubCategoryService(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }
}
