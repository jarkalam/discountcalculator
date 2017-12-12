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
public class BrandTest extends TestCase {
    
    public BrandTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BrandTest.class);
        return suite;
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        InputReader.readBrandInfo("resources\\brands.csv");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getCalculatedDiscount method, of class Brand.
     */
    public void testGetCalculatedDiscount() {
        System.out.println("getCalculatedDiscount");
        Brand instance = new Brand("Adidas");
        float expResult = 00.0F;
        float result = instance.getCalculatedDiscount();
        assertEquals(expResult, result, 0.0);
    }

}
