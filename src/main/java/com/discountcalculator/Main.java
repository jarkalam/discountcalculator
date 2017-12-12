/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.discountcalculator;

import com.discountcalculator.discount.Brand;
import com.discountcalculator.discount.Category;
import com.discountcalculator.discount.InputReader;
import com.discountcalculator.discount.Product;
import java.util.List;
import org.apache.log4j.Logger;


/**
 *
 * @author Rajkamal
 */
public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    public Main(){ 
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LOG.info("Start of execution.");
        // TODO code application logic here
        InputReader.readBrandInfo(args[0]);
        InputReader.readCategoryInfo(args[1]);
        
        LOG.info("===========================================");
        LOG.info("FETCHED DATA");
        Brand.displayData();
        LOG.info("===========================================");
        LOG.info("********** CATEGORIES ********");
        LOG.info("===========================================");
        Category.displayData(Category.getRootCategory());
        LOG.info("===========================================");
        
        Product[] p = InputReader.readItems();
        
        List[] userSelection = InputReader.readUserSelection();
        
        float price[] = new float[userSelection.length];
        
        for(int index = 0;index < userSelection.length;index++) {
            price[index] = 0.0f;
            for(int index1=0;index1 < userSelection[index].size();index1++) {
                int productIndex = ((Integer)userSelection[index].get(index1)).intValue() - 1;
                LOG.info("Fetching price for Product[" + (productIndex+1) + "]");
                price[index] += (100 - p[productIndex].getCalculatedDiscount()) / 100 * p[productIndex].getPrice();
            }
        }
        
        for(int index = 0;index< price.length;index++)
            System.out.println(price[index]);
    }
}
