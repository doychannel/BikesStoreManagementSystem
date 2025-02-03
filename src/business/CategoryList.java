/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import model.Category;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Duy
 */
public class CategoryList extends ArrayList<Category> {
private final String f="src/data/Category.txt";
    public CategoryList() {
        super();
        loadCatFromFile(f);
    }
    
    public Category getCatId(String catId) {
        for(Category c : this){
            if(c.getId().equalsIgnoreCase(catId)){
                return c;
            }
        }
        return null;
    }
    
      public void loadCatFromFile(String fileName){
        if(this.size()>0) this.clear(); //clear all elements in the list
        File f=new File(fileName);

        if(f.exists())
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] CatInfo = line.split(","); 
        
                this.add(new Category(CatInfo[0],CatInfo[1]));
               
                }
                
            } 
         catch (IOException e) {
            e.printStackTrace();
        }
       System.out.println("Category File read successfully!!");
    }
      
}
