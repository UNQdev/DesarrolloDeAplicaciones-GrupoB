package ar.edu.unq.desapp.grupob.web.rest;

import java.io.IOException;
import java.util.ArrayList;
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
import ar.edu.unq.desapp.grupob.utils.JSONObject;

@Service
@Path("/categories")
public class CategoryRest {
    private CategoryService categoryService;

    @GET
    @Path("/")
    @Produces("application/json")
    public String getCategoryById() throws JsonGenerationException,
            JsonMappingException, IOException {
        List<Category> categorias = new ArrayList<Category>();
        categorias.add(new Category("Proveedores", null));
        categorias.add(new Category("Ventas", null));
        return JSONObject.getInstance().ObjectToJSON(categorias);
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Category> getAllCategories() throws JsonGenerationException,
            JsonMappingException, IOException {

        SubCategory peliculas = new SubCategory("Peliculas");
        SubCategory insumos = new SubCategory("Insumos");
        
        Category ventas = new Category();
        ventas.setName("Ventas");
        ventas.createSubCategory(peliculas);
        categoryService.save(ventas);
        
        Category proveedores = new Category();
        proveedores.setName("Proveedores");
        proveedores.createSubCategory(insumos);
        categoryService.save(proveedores);

        List<Category> categorias = getCategoryService().retriveAll();
        for (Category category : categorias) {
            System.out.println(category.getName());
            for (SubCategory subcategory : category.getSubcategories()){
                System.out.println(subcategory.getName());
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
