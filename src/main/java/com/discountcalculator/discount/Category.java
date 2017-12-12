/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.discountcalculator.discount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;

/**
 * Domain Class for Category information.
 * @author Rajkamal
 * @version 1.0
 */
public class Category implements Discountable {
    private static final Logger LOG = Logger.getLogger(Category.class.getName());

    private String name;
    private float discount;
    private Category parent;
    private List<Category> children;
    private static final Category rootCategory = new Category("Apparel", 0.0f);
    private static String searchNode;
    
    
    static { }
    
    public static void initialise() { }
    
    /**
     * No arg constructor
     */
    public Category() { }
    
    /**
     * Constructor
     * @param name Category name.
     */
    public Category(String name) {
        this.name = name;
    }
    
    /**
     * Constructor
     * @param name Category name.
     * @param discount Discount applicable.
     * @param name 
     */
    public Category(String name, float discount) {
        this.name = name;
        this.discount = discount;
    }
    
    /**
     * Constructor
     * @param name Category name.
     * @param float Discount applicable.
     * @param Category Parent Category.
     * @param name 
     */
    public Category(String name, float discount, Category parent) {
        this.name = name;
        this.discount = discount;
        this.parent = parent;
    }
    
    /**
     * @return String Category name.
     */
    public String getName() {
        return name;
    }

    /**
          * @param name Category name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return float Discount applicable.
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * @param discount Discount applicable.
     */
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    /**
     * @return List<Category> Child Categories.
     */
    public List<Category> getChildren() {
        return children;
    }
    
    /**
     * @return boolean boolean value indicating presence of child categories.
     */
    public boolean hasChildren() {
        return (this.children != null) 
                    && (this.children.size() > 0);
    }

    /**
     * @param child Child Category to be added to hierarchy.
     */
    public void addChild(Category child) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(child);
    }

    /**
     * @return Category Parent Category.
     */
    public Category getParent() {
        return parent;
    }

    /**
     * @param parent Sets parent Category in hierarchy.
     */
    public void setParent(Category parent) {
        this.parent = parent;
    }

    /**
     * @return Category The root of the Category hierarchy.
     */
    public static Category getRootCategory() {
        return rootCategory;
    }
    
    /**
     * Finds the Category node in the hierarchy of Category(s).
     * @param categoryName Name of the Category to find.
     * @param category Root element of the hierarchy.
     * @return 
     */
    public static Category getCategory(String categoryName, Category category) {
        LOG.info("Comparing Category  [" + categoryName + "] with [" 
                        + category.getName() + "]");
        
        do {
            category = findNode(categoryName, Category.getRootCategory());
        } while (category == null);
        LOG.info("Returning [" + (category == null? "null": category.name) +"].");
        return category;
    }
    
    /**
     * Search the hierarchy recursively for searchNode.
     * @param category
     * @return 
     */
    public static Category findNode(String searchNode, Category category) {
        LOG.info("FindNode() - [" + searchNode + "] with [" + (category == null? "null":category.name) + "]");
        if ((category != null)
                && (searchNode.equalsIgnoreCase(category.name))) {
            LOG.info("FOUND returning [" + category.name + "]");
            return category;
        }
        
        if(category.hasChildren()) {
            List<Category> children = category.children;
            for (Category cat:children) {
                category = findNode(searchNode, cat);
                if ((category != null)
                    && (searchNode.equals(category.name))) {
                    LOG.info("FOUND returning [" + category.name + "]");
                    return category;
                }
            }
        }
        
        if((category != null)
                && (searchNode.equals(category.name))) {
            LOG.info("Returning [" + category.name + "]");
            return category;
        }
        
        LOG.info("NOT FOUND Returning [null]");
        return null;
    }

    /**
     * Return the applicable discount for the Category element.
     * @return 
     */
    @Override
    public float getCalculatedDiscount () {
        //LOG.info("Inside Category::getCalculatedDiscount().");
        float parentDiscount = (this.parent == null? 
                0.0f:this.parent.getCalculatedDiscount());
        //LOG.info("discount ==> [" + discount + "]");
        //LOG.info("Parent discount ==> [" + parentDiscount + "]");
        return (parentDiscount > this.discount?
                parentDiscount:this.discount);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
        }
        if (!Objects.equals(this.parent, other.parent)) {
            return false;
        }
        if (!this.children.containsAll(other.children)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "["
                + this.name 
                + ", "
                + this.discount
                + " - "
                + (this.parent != null ? this.parent.name:"#")
                + "]";
    }
    
    public static void displayData(Category category) {
        
        LOG.info("Category ==> " + category);
        List<Category> children = category.getChildren();
        if(children != null) {
            for(int index = 0;index < children.size();index++) {
                displayData(children.get(index));
            }
        }
        
    }
}
