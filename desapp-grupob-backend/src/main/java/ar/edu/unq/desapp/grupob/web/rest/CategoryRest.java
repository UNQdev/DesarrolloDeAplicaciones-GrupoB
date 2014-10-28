package ar.edu.unq.desapp.grupob.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.services.CategoryService;

@Service
@Path("/categoryService")
public class CategoryRest {
    private CategoryService categoryService;

    @GET
    @Path("/categories")
    @Produces("application/json")
    public List<Category> getCategories() {
        return getCategoryService().retriveAll();
    }

    @GET
    @Path("/category/{id}")
    @Produces("application/json")
    public Category getCategory(@PathParam("id") final int id) {
        Category category = getCategoryService().getById(id);
        return category;
    }

    @GET
    @Path("/filterByName/{name}")
    @Produces("application/json")
    public List<Category> filterByName(@PathParam("name") final String name) {
        return getCategoryService().filterByName(name);
    }

    @GET
    @Path("/save/{name}")
    @Produces("application/json")
    public Category saveCategory(@PathParam("name") final String name) {
        Category category = new Category(name);
        getCategoryService().save(category);
        return category;
    }
    
    @GET
    @Path("/update/{id}/{name}")
    @Produces("application/json")
    public Category updateCategory(@PathParam("id") final int id,
            @PathParam("name") final String name) {
        Category category = getCategoryService().getById(id);
        getCategoryService().update(category);
        return category;
    }

    @GET
    @Path("/delete/{id}")
    @Produces("application/json")
    public Category deleteCategory(@PathParam("id") final int id) {
        Category category = this.getCategory(id);
        getCategoryService().delete(category);
        return category;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
