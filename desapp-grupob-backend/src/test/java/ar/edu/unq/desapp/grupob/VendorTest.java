package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.Vendor;
import ar.edu.unq.desapp.grupob.model.builders.VendorBuilder;

public class VendorTest {

    @Test
    public void testVendorConstructorDefault() {
        VendorBuilder builder = VendorBuilder.aVendorBuilder();
        Vendor vendor = builder.build();

        assertEquals(vendor.getName(), "no name");
        assertEquals(vendor.getTaxCode(), "0");
    }

    @Test
    public void testVendorConstructorFull() {
        VendorBuilder builder = VendorBuilder.aVendorBuilder();
        String name = "proveedor";
        String taxCode = "1111";

        Vendor vendor = builder.withName(name).withTaxCode(taxCode).build();

        assertEquals(vendor.getName(), name);
        assertEquals(vendor.getTaxCode(), taxCode);
    }

}
