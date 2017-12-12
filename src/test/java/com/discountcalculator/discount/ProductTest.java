/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.discountcalculator.discount;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author Rajkamal
 */
public class ProductTest extends TestCase {
    
    public ProductTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ProductTest.class);
        return suite;
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getCategory method, of class Product.
     */
    public void testGetCategory() {
        System.out.println("getCategory");
        Product instance = new Product();
        Category expResult = null;
        Category result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBrand method, of class Product.
     */
    public void testGetBrand() {
        System.out.println("getBrand");
        Product instance = new Product();
        Brand expResult = null;
        Brand result = instance.getBrand();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class Product.
     */
    public void testGetPrice() {
        System.out.println("getPrice");
        Product instance = new Product();
        float expResult = 0.0F;
        float result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCalculatedDiscount method, of class Product.
     */
    public void testGetCalculatedDiscount() {
        System.out.println("getCalculatedDiscount");
        Product instance = new Product();
        float expResult = 0.0F;
        float result = instance.getCalculatedDiscount();
        assertEquals(expResult, result, 0.0);
    }

}
