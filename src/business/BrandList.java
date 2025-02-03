/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import model.Brand;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Duy
 */
public class BrandList extends ArrayList<Brand> {
    private final String f ="src/data/Brand.txt";
    public BrandList(){
        super();//auto input data when creates object
        loadBrandFromFile(f);
    }

    public Brand getBrandId(String brandId) {
        for(Brand b : this){
            if(b.getId().equalsIgnoreCase(brandId)){
                return b;
            }
        }
        return null;
    }

    private void loadBrandFromFile(String fileName) {
        
        if(this.size()>0) this.clear(); //clear all elements in the list
        File f=new File(fileName);
        if(f.exists()) 

        try {

            BufferedReader reader = new BufferedReader(new FileReader(f)); //Read from file then put data into BufferedReader 
            String line;
            while((line = reader.readLine()) != null){
                String []index = line.split(",");    //split the line by each ","; put each element each line into a String array
                if(index.length == 3){  //if one line contains all 3 elements: id,type,nation
                    
                    this.add(new Brand(index[0],index[1],index[2]));
             
                }
            }
            
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
       System.out.println("Brand.txt read successfully!!");
    }    
    
}
