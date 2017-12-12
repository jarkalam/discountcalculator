/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.discountcalculator.discount;

import org.apache.log4j.Logger;


/**
 * Domain Class for Brand information.
 * @author Rajkamal
 * @version 1.0
 */
public class Product implements Discountable {
    private static final Logger LOG = Logger.getLogger(Product.class.getName());
    
    private Category category;
    private Brand brand;
    private float price;

    public Product() { }
    
    /**
     * Populate Brand & Category properties.
     * @param brandName
     * @param categoryName
     * @param price 
     */
    public Product (String brandName, String categoryName, float price) {
        this.brand = Brand.getBrand(brandName);
        LOG.info("Searching BRAND [" + brandName+ "] Found [" + (brand==null? "null":brand.getName()) + "]");
        assert this.brand != null: "ERROR: Brand not found.";
        // brand.getCalculatedDiscount();
        
        if (categoryName.equals(Category.getRootCategory().getName())) {
            this.category = Category.getRootCategory();
        }
        else {
            this.category = Category.findNode(categoryName, Category.getRootCategory());
        }
        LOG.info("Searching CATEGORY [" + categoryName+ "] Found [" + category==null? "null":category.getName() + "]");
        assert this.category != null: "ERROR: Category not loaded.";
        // category.getCalculatedDiscount();
        
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Return the discount calculated acording to Brand & Category rule.
     * @return 
     */
    @Override
    public float getCalculatedDiscount() {
        float categoryDiscount = 0.0f;
        float brandDiscount = 0.0f;
        
        if (this.category!= null)
            categoryDiscount = this.category.getCalculatedDiscount();
        LOG.info("Category Discount ==> [" + categoryDiscount + "]");
        if (this.brand != null)
            brandDiscount = this.brand.getCalculatedDiscount();
        LOG.info("Brand Discount ==> [" + brandDiscount + "]");
            
        return categoryDiscount > brandDiscount? 
                    categoryDiscount:brandDiscount;
    }
    
    @Override
    public String toString() {
        return this.category.getName()
                + this.brand.getName();
    }
}
