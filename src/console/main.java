/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.util.Scanner;

import business.ProductList;
import model.Product;
import tool.inputter;
/**
 *
 * @author Duy
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) {
         final String filename = "src/data/Product.txt";
        
        inputter sc = new inputter();
        ProductList list = new ProductList();
        list.loadFromFile(filename);

        String[] options = {
            "Create new product", "Search Product info by name, return a list of all products",
            "Update Product", "Delete Product",
            "Save to file", "Print list products from the file"};

        int choice = 0;

        do {
            System.out.println("\nBike Stores Management System");
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    /*
                    Product p = new Product ("1", "bike", "b001", "c002", 2024, 200.00);
                    list.put("1", p); 
                           fakedata */
                    
                    list.createProduct();

                    break;
                case 2:
                    String prdName = sc.getStringNotNull("Enter product name to search: ","Enter valid String");
                    list.searchByName(prdName);
                    break;
                case 3://done
                    String prdID = sc.inputString("Enter product ID to update: ");
                    if (list.updateProduct(prdID)) {
                        System.out.println("Update Process Done");
                    }
                    else {
                        System.out.println("Product not exist!!");
                    }
                      
                    break;
                case 4:
                    String id = sc.inputString("Enter Product ID to delete: ");
                    if (list.deleteProduct(id)) {
                        
                        System.out.println("--------LIST AFTER DELETION------");
                        list.showAll();
                    } else {
                        System.out.println("Product not exist!!");
                    }
                    break;
                case 5:
                    list.saveToFile(filename);
                    break;
                case 6:
                    list.printFromFile();
                    break;
                
                default:
                    if (list.size() > 0) {

                        String response = sc.inputString("Save changes Y/N?").toUpperCase();
                        if (response.startsWith("Y")) {
                            list.saveToFile(filename);
                        }
                    }
                    System.out.println("GoodBye!!!");
            }
        } while (choice > 0 && choice <= options.length);
    }

}
