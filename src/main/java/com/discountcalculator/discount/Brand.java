/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.discountcalculator.discount;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * Domain Class for Brand information.
 * @author Rajkamal
 * @version 1.0
 */
public class Brand implements Discountable {
    private static final Logger LOG = Logger.getLogger(Brand.class.getName());
    
    private String name;
    private float discount;
    private static final Map<String, Brand> brandMap = new HashMap();
    
    static { }
    
    public static void  initialise() { }

    public Brand () { }
    
    public Brand (String name) {
        this.name = name;
    }
    
    public Brand (String name, float discount) {
        this.name = name;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscount (float discount) {
        this.discount = discount;
    }
    
    public static Brand getBrand(String brandName) {
        return Brand.brandMap.get(brandName);
    }
    
    
    public static void addBrand(String brandName, Brand brand) {
        Brand.brandMap.put(brandName, brand);
    }
    
    /**
     * Return discount calculated according to Brand rule.
     * @return 
     */
    @Override
    public float getCalculatedDiscount () {
        return this.discount;
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
        final Brand other = (Brand) obj;
        if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" 
                + this.name
                + ","
                + this.discount
                + "]";
    }
    
    public static void displayData() {
        LOG.info("===========================================");
        LOG.info("********** BRANDS ********");
        LOG.info("===========================================");
        
        Set keys = Brand.brandMap.keySet();
        Iterator iterator = keys.iterator();
        while(iterator.hasNext()){
            String name = iterator.next().toString();
            Brand b = Brand.brandMap.get(name);
            
            LOG.info("[" + name + "] ==> " + b);
        }
    }
}
