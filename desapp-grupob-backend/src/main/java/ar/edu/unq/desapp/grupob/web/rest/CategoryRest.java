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

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.services.CategoryService;

@Service
@Path("/categoryService")
public class CategoryRest {
	
    private static final int HTTP_OK_CREATED = 201;

    private static final int HTTP_OK = 200;

    private static final int HTTP_DELETE = 204;

    
    @Autowired
    private CategoryService categoryService;
    
    @GET
    @Path("/categories")
    @Produces("application/json")
    public List<Category> getCategories() {
        return getCategoryService().retriveAll();
    }

    @GET
    @Path("/categoriesForNewOperation")
    @Produces("application/json")
    public List<Category> getCategoriesForNewOperation() {
        List<Category> categoryListWithoutCompras = getCategoryService().retriveAll();
        categoryListWithoutCompras.remove(0);
        return categoryListWithoutCompras;
    }
    
    @GET
    @Path("/byId/{id}")
    @Produces("application/json")
    public Category getCategoryById(@PathParam("id") final int id) {
        return getCategoryService().getById(id) ;
    }
    
    @GET
    @Path("/byName/{name}")
    @Produces("application/json")
    public Response getCategoryByName(@PathParam("name") final String name) {
        Category category = getCategoryService().getByName(name);
        if (category == null ){
        	return Response.ok().status(HTTP_OK).build();
        }
        else {
        	return Response.ok().status(Status.CONFLICT).entity("Categoria con nombre '"+name+"' ya existente").build();
        }
    }

    @GET
    @Path("/filterByName/{name}")
    @Produces("application/json")
    public List<Category> filterByName(@PathParam("name") final String name) {
        return getCategoryService().filterByName(name);
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveCategory(@Multipart(value = "category", type = "application/json") final String jsonCategory) {
        try {
            getCategoryService().save(parseCategory(jsonCategory));
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.status(HTTP_OK_CREATED).build();
    }
    
    @PUT
    @Path("/{categoryId}")
    @Consumes("application/json")
    public Response updateCategory(@PathParam("categoryId") final String categoryId, final String jsonCategory) {
        try {
            Category categoryBeforeToBeUpdated = getCategoryService().getById(new Integer (categoryId));
            
            Category categoryToBeUpdated = parseCategory(jsonCategory);
            categoryToBeUpdated.setSubcategories(categoryBeforeToBeUpdated.getSubcategories());
            
            getCategoryService().update(categoryToBeUpdated);
        } catch (Exception e) {
            return Response.status(Status.METHOD_NOT_ALLOWED).build();
        }
        return Response.ok().status(HTTP_OK).build();
    }

    @DELETE
    @Path("/{categoryId}")
    public Response deleteCategory(@PathParam("categoryId") final String categoryId) {
        try {
            Category categoryToBeDeleted = getCategoryService().getById(new Integer(categoryId));
            getCategoryService().delete(categoryToBeDeleted);
        } catch (Exception e) {
            return Response.status(Status.CONFLICT).entity("Hay una OPERACION que la utiliza").build();
        }
        return Response.ok().status(HTTP_DELETE).build();
    }

    private Category parseCategory(final String jsonCategory) throws Exception {
        Category newCategory = new Category();
        ObjectMapper mapper = new ObjectMapper();
        newCategory = mapper.readValue(jsonCategory, Category.class);
        return newCategory;
    }
    
    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
