package ar.edu.unq.desapp.grupob.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.SubCategory;
import ar.edu.unq.desapp.grupob.services.CategoryService;
import ar.edu.unq.desapp.grupob.services.SubCategoryService;

@Service
@Path("/app")
public class FakeDataRest {

    private CategoryService categoryService;

    @GET
    @Path("/init")
    public void begin() {
        
        // CREATE SUBCATEGORIES
        
        SubCategory insumos = new SubCategory("Insumos");
        SubCategory productos = new SubCategory("Productos");
        
        // CREATE CATEGORIES
        Category ventas = new Category("Ventas");
        Category compras = new Category("Compras");

        ventas.createSubCategory(productos);
        compras.createSubCategory(insumos);
        
        getCategoryService().save(ventas);
        getCategoryService().save(compras);
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
