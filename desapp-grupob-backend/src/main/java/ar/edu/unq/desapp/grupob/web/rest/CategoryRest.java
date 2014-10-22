package ar.edu.unq.desapp.grupob.web.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.SubCategory;
import ar.edu.unq.desapp.grupob.services.CategoryService;

@Service
@Path("/categories")
public class CategoryRest {
    private CategoryService categoryService;

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Category> getAllCategories() throws JsonGenerationException,
            JsonMappingException, IOException {
        return getCategoryService().retriveAll();
    }

    @GET
    @Path("/filterByName/{name}")
    @Produces("application/json")
    public List<Category> filterByName(@PathParam("name") final String name) {
        return getCategoryService().filterByName(name);
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
