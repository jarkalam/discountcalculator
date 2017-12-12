/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.discountcalculator.discount;

/**
 * 
 * @author Rajkamal
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;


public class InputReader {
    private static final Logger LOG = Logger.getLogger(InputReader.class.getName());
    
    private static String line = "";
    private static final String csvSplitBy = ",";
    private static final int COLUMN_ONE = 0;
    private static final int COLUMN_TWO = 1;
    private static final int COLUMN_THREE = 2;
    private static final int COLUMN_FOUR = 3;

    /**
     * ead Brand information from csv file (brands.csv).
     * @param brandFile 
     */
    public static void readBrandInfo(String brandFile) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(brandFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] brands = line.split(csvSplitBy);
                brands = removeWhitespace(brands);

                LOG.info("Brand [Name = " + brands[COLUMN_ONE] 
                                + " , discount =" 
                                + brands[COLUMN_TWO] + "]");
                Brand b = new Brand(brands[COLUMN_ONE], 
                        Float.parseFloat(brands[COLUMN_TWO]
                                .substring(0,brands[COLUMN_TWO].length()-1)));
                 Brand.addBrand(brands[COLUMN_ONE], b);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Exception: IOException");
            System.err.println(e.toString());
        }
    }
    
    /**
     * Read Category information from csv file (categories.csv).
     * @param categoryFile 
     */
    public static void readCategoryInfo(String categoryFile) {

        Map<String,String> categoryMap = new HashMap();
        try (BufferedReader br = new BufferedReader(new FileReader(categoryFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] categories = line.split(csvSplitBy);
                categories = removeWhitespace(categories);

                LOG.info("Category [Name = " 
                                + categories[COLUMN_TWO] 
                                + " , discount =" 
                                + categories[COLUMN_THREE].substring(0,
                                        categories[COLUMN_THREE].length()-1) + "]");
                Category category = new Category(categories[COLUMN_TWO], 
                            Float.parseFloat(categories[COLUMN_THREE]
                                    .substring(0,categories[COLUMN_THREE].length()-1)));
                categoryMap.put(categories[COLUMN_ONE],categories[COLUMN_TWO]);
                if((categories[3] != null)
                        && (!categories[COLUMN_FOUR].equals("NIL"))) {
                    String parent = categoryMap.get(categories[COLUMN_FOUR]);
                    Category parentCategory = Category.findNode(parent
                                            , Category.getRootCategory());
                    if(parentCategory != null) {
                        parentCategory.addChild(category);
                        category.setParent(parentCategory);
                    }
                }
            }
       } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Exception: IOException");
            System.err.println(e.toString());
        }

    }
    
    /**
     * Read User selection.
     * @return Corresponding Product instances.
     */
    public static Product[] readItems() {
        
        System.out.println("Enter data in prescribed format: ");
        String input = System.console().readLine();
        Product[] products = new Product[Integer.parseInt(input)];
        
        for(int index = 0;index < Integer.parseInt(input);index++)  {

            String[] items = System.console().readLine().split(csvSplitBy);
            items = removeWhitespace(items);
            assert items[0] != null: "Brand name cannot be empty";
            assert items[1] != null: "Category name cannot be empty";
            assert items[2] != null: "Price cannot be empty";
            
            LOG.info("[Brand] ==> " + items[1] + " [Category] ==> " + items[2] + " Price ==> " + items[3]);
            products[index] = new Product(items[1], items[2]
                                    , Float.parseFloat(items[3]));
        }
        return products;
    }
    
    /**
     * Reade user input in the decided format.
     * @return List[] containing user input values. 
     */
    public static List[] readUserSelection() {
        
        System.out.println("User choice:");
        String input = System.console().readLine();
        List[] choice = new List[Integer.parseInt(input)];
        for(int index = 0;index < choice.length;index++) {
            choice[index] = new ArrayList();
            String[] items = System.console().readLine().split(csvSplitBy);
            items = removeWhitespace(items);
            for(int index1 = 0;index1 < items.length;index1++) {
                choice[index].add(Integer.parseInt(items[index1]));
            }
        }
        return choice;
    }

    /**
     * Remove trailing whitespace characters from String[].
     * @param strings User input (csv files).
     * @return Strings with leading/trailing whitespace removed.
     */
    public static String[] removeWhitespace(String[] strings) {
        for(int index = 0;index < strings.length;index++) {
            strings[index] = strings[index].trim();
        }
        return strings;
    }
}
