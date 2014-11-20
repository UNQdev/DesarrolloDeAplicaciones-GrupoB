package ar.edu.unq.desapp.grupob.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.SubCategory;
import ar.edu.unq.desapp.grupob.services.CategoryService;
import ar.edu.unq.desapp.grupob.services.SubCategoryService;

@Service
@Path("/subcategoryService")
public class SubCategoryRest {

    private static final int HTTP_OK_CREATED = 201;

    private static final int HTTP_OK = 200;

    private static final int HTTP_DELETE = 204;
    
    @Autowired
    private SubCategoryService subCategoryService;
    private CategoryService categoryService;
    
    @GET
    @Path("/subcategories")
    @Produces("application/json")
    public List<SubCategory> getSubCategories() {
        return getSubCategoryService().retriveAll();
    }
    
    @GET
    @Path("/byCategoryId/{categoryId}")
    @Produces("application/json")
    public List<SubCategory> getSubCategoriesByFatherId(@PathParam("categoryId") final int categoryId) {
        Category category = getCategoryService().getById(categoryId);
        return category.getSubcategories();
    }
    
    @GET
    @Path("/byName/{name}")
    @Produces("application/json")
    public Response getCategoryByName(@PathParam("name") final String name) {
        SubCategory category = getSubCategoryService().getByName(name);
        if (category != null ){
           return Response.serverError().status(400).build();
        }
        else {
            return Response.ok().status(HTTP_OK).entity(category).build();
        }
    }

    @GET
    @Path("/filterByName/{name}")
    @Produces("application/json")
    public List<SubCategory> filterByName(@PathParam("name") final String name) {
        return getSubCategoryService().filterByName(name);
    }
    
    @POST
    @Path("/save/{categoryFatherId}")
    @Consumes("application/json")
    public Response saveCategory(@PathParam("categoryFatherId") final int categoryFatherId,  final String jsonSubCategory) {
        try {
            SubCategory subcategory = parseSubCategory(jsonSubCategory);
            Category father = getCategoryService().getById(categoryFatherId);
            father.createSubCategory(subcategory);
            getCategoryService().update(father);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.status(HTTP_OK_CREATED).build();
    }
    
    @PUT
    @Path("/{subCategoryId}")
    @Consumes("application/json")
    public Response updateCategory(@PathParam("subCategoryId") final String subCategoryId, final String jsonSubCategory) {
        try {
            getSubCategoryService().update(parseSubCategory(jsonSubCategory));
        } catch (Exception e) {
            return Response.status(Status.METHOD_NOT_ALLOWED).build();
        }
        return Response.ok().status(HTTP_OK).build();
    }

    @DELETE
    @Path("/{subCategoryId}")
    public Response deleteCategory(@PathParam("subCategoryId") final String subCategoryId) {
        try {
            SubCategory subcategoryToBeDeleted = getSubCategoryService().getById(new Integer(subCategoryId));
            getSubCategoryService().delete(subcategoryToBeDeleted);
        } catch (Exception e) {
            return Response.status(Status.CONFLICT).build();
        }
        return Response.ok().status(HTTP_DELETE).build();
    }

    
    private SubCategory parseSubCategory(final String jsonSubCategory) throws Exception {
        SubCategory newSubCategory = new SubCategory();
        ObjectMapper mapper = new ObjectMapper();
        newSubCategory = mapper.readValue(jsonSubCategory, SubCategory.class);
        return newSubCategory;
    }
    
    public SubCategoryService getSubCategoryService() {
        return subCategoryService;
    }

    public void setSubCategoryService(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
