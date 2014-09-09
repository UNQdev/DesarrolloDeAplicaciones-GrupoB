package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import org.junit.Test;
import ar.edu.unq.desapp.grupob.builders.SubCategoryBuilder;

public class SubcategoryTest {
    @Test
    public void testSubcategoryConstructorWithoutName() {
        SubCategoryBuilder builder = SubCategoryBuilder.aSubCategoryBuilder();
        SubCategory subcategory = builder.build();
        assertEquals(subcategory.getName(), "no name");
    }

    @Test
    public void testSubcategoryConstructorWithName() {
        SubCategoryBuilder builder = SubCategoryBuilder.aSubCategoryBuilder();
        SubCategory subcategory = builder.withName("Publicidad").build();
        assertEquals(subcategory.getName(), "Publicidad");
    }
}
