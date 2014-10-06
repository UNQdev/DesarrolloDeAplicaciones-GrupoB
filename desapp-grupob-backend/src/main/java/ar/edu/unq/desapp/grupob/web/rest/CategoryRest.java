package ar.edu.unq.desapp.grupob.web.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.services.CategoryService;
import ar.edu.unq.desapp.grupob.utils.HibernateUtils;
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
    public String getAllCategories() throws JsonGenerationException,
            JsonMappingException, IOException {
        
        Category ventas = new Category();
        ventas.setName("Ventas");
        categoryService.save(ventas);
        Category proveedores = new Category();
        proveedores.setName("Proveedores");
        categoryService.save(proveedores);
        
        List<Category> categorias = getCategoryService().retriveAll();
        return JSONObject.getInstance().ObjectToJSON(categorias);
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
