package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.builders.CategoryBuilder;

public class CategoryTest {

    @Test
    public void testCategoryConstructorWithoutNameAndSubCategory() {
        CategoryBuilder builder = CategoryBuilder.aCategoryBuilder();
        Category category = builder.build();

        assertEquals(category.getName(), "no name");
        assertTrue(category.getSubcategories().isEmpty());
    }

    @Test
    public void testCategoryConstructorWithNameAndSubCategory() {
        CategoryBuilder builder = CategoryBuilder.aCategoryBuilder();
        Category category = builder.withName("Proveedores")
                .withSubCategoryName("Oficina").build();

        assertEquals(category.getName(), "Proveedores");
        assertEquals(category.getSubcategories().size(), 1);
    }
    
    @Test
    public void testCategoryCreateSubCategory() {
        CategoryBuilder builder = CategoryBuilder.aCategoryBuilder();
        Category category = builder.build();
        SubCategory subcategory = mock(SubCategory.class);
        category.createSubCategory(subcategory);
        
        assertTrue(category.getSubcategories().contains(subcategory));
    }
    
    @Test
    public void testCategoryModifySubCategory() {
        CategoryBuilder builder = CategoryBuilder.aCategoryBuilder();
        Category category = builder.build();
        SubCategory subcategoryOriginal = mock(SubCategory.class);
        SubCategory subcategoryModified = mock(SubCategory.class);
        
        category.createSubCategory(subcategoryOriginal);
        assertEquals(category.getSubcategories().size(), 1);
        
        category.modifySubcategory(subcategoryOriginal, subcategoryModified);
        
        assertEquals(category.getSubcategories().size(), 1);
        assertTrue(category.getSubcategories().contains(subcategoryModified));
        assertFalse(category.getSubcategories().contains(subcategoryOriginal));
    }
    
    @Test
    public void testCategoryDeleteSubCategory() {
        CategoryBuilder builder = CategoryBuilder.aCategoryBuilder();
        Category category = builder.build();
        SubCategory subcategory = mock(SubCategory.class);
        
        category.createSubCategory(subcategory);
        assertTrue(category.getSubcategories().contains(subcategory));
        
        category.deleteSubcategory(subcategory);
        assertFalse(category.getSubcategories().contains(subcategory));
    }
    
}
