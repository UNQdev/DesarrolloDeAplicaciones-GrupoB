package ar.edu.unq.desapp.grupob.web.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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

        SubCategory peliculas = new SubCategory("Peliculas");
        SubCategory insumos = new SubCategory("Insumos");
        SubCategory insumos2 = new SubCategory("Insumos2");

        Category ventas = new Category("Ventas");
        ventas.createSubCategory(peliculas);
        ventas.createSubCategory(insumos2);
        categoryService.save(ventas);

        Category proveedores = new Category("Proveedores");
        proveedores.createSubCategory(insumos);
        proveedores.createSubCategory(peliculas);
        proveedores.createSubCategory(insumos2);
        categoryService.save(proveedores);

        Category sueldos = new Category("Sueldos");
        sueldos.createSubCategory(peliculas);
        sueldos.createSubCategory(new SubCategory("premios"));
        categoryService.save(sueldos);
        
        List<Category> categorias = getCategoryService().retriveAll();
        
        for (Category category : categorias) {
            System.out.println("\n"+category.getName());
            for (SubCategory subcategory : category.getSubcategories()) {
                System.out.println("-- "+subcategory.getName());
            }

        }

        return categorias;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
