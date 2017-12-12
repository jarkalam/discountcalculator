/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.discountcalculator.discount;

import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author Rajkamal
 */
public class CategoryTest extends TestCase {
    
    public CategoryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CategoryTest.class);
        return suite;
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        InputReader.readCategoryInfo("resources/categories.csv");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of findNode method, of class Category.
     */
    public void testFindNode() {
        System.out.println("findNode");
        String searchNode1 = "Jeans";
        String searchNode2 = "Dresses";
        Category rootCategory = Category.getRootCategory();
        Category expResult1 = Category.getCategory("Jeans", rootCategory);
        Category expResult2 = Category.getCategory("Dresses", rootCategory);
        Category result1 = Category.findNode(searchNode1, rootCategory);
        Category result2 = Category.findNode(searchNode2, rootCategory);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getCalculatedDiscount method, of class Category.
     */
    public void testGetCalculatedDiscount() {
        System.out.println("getCalculatedDiscount");
        Category instance1 = Category.getCategory("Casuals", Category.getRootCategory());
        Category instance2 = Category.getCategory("Footwear", Category.getRootCategory());
        float expResult1 = 30.0F;
        float expResult2 = 50.0F;
        float result1 = instance1.getCalculatedDiscount();
        float result2 = instance2.getCalculatedDiscount();
        assertEquals(expResult1, result1, 0.0);
        assertEquals(expResult2, result2, 0.0);
    }
}
