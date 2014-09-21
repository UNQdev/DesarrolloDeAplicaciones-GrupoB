package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.CategoryManager;

public class CategoryManagerTest {

    @Test
    public void testCategoryManagerConstructor (){
        CategoryManager categoryManager = new CategoryManager();
        
        assertNotNull(categoryManager.getCategories());
    }
    
    @Test
    public void testCategoryManagerCreateCategory (){
        CategoryManager categoryManager = new CategoryManager();
        Category category = mock(Category.class);
        
        categoryManager.createCategory(category);
        
        assertTrue(categoryManager.getCategories().contains(category));
    }
    
    @Test
    public void testCategoryManagerModifyCategory() {
        CategoryManager categoryManager = new CategoryManager();
        Category categoryOriginal = mock(Category.class);
        Category categoryModified = mock(Category.class);
        
        categoryManager.createCategory(categoryOriginal);
        assertEquals(categoryManager.getCategories().size(), 1);
        
        categoryManager.modifyCategory(categoryOriginal, categoryModified);
        
        assertEquals(categoryManager.getCategories().size(), 1);
        assertTrue(categoryManager.getCategories().contains(categoryModified));
        assertFalse(categoryManager.getCategories().contains(categoryOriginal));
    }
    
    @Test
    public void testCategoryManagerDeleteCategory() {
        CategoryManager categoryManager = new CategoryManager();
        Category category = mock(Category.class);
        
        categoryManager.createCategory(category);
        assertTrue(categoryManager.getCategories().contains(category));
        
        categoryManager.deleteCategory(category);
        assertFalse(categoryManager.getCategories().contains(category));
    }
}
