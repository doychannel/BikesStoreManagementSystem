/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import tool.inputter;

import java.io.*;
import java.util.Date;
import java.util.HashMap;

import model.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
// import java.util.Iterator;
// import java.util.List;

/**
 *
 * @author Duy
 */
public class ProductList extends HashMap<String, Product> {
   
    private final String TIEU_DE =  "|---------------------------------------------------------|\n"+
                                    "|ID   | Product Name      | Brand  | Cat  | Model | Price |\n"+
                                    "|-----|-------------------|--------|------|-------|-------|\n";
    

    public ProductList() {
        super();
    }

    public ArrayList<Product> toList() {
        return new ArrayList<Product>(this.values());
    }

    public void printFromFile() {
        if (this.isEmpty()) {
            System.out.println("Empty file list!!");

        }

        System.out.println("Displaying list order by: price DESC - name ASC");

        ArrayList<Product> r = sortByPriceName(this.toList());

        showList(r);

    }

    public void loadFromFile(String fileName) {

        File f = new File(fileName);
        if (f.exists()) {
            System.out.println("File Product.txt existed");
        } else {
            System.out.println("File not existed");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] index = line.split(",");
                String id = index[0].trim();
                String name = index[1].trim();
                String brand_id = index[2].trim();
                String category_id = index[3].trim();
                int model_year = Integer.parseInt(index[4].trim());
                double list_price = Double.parseDouble(index[5].trim());

                Product product = new Product(id, name, brand_id, category_id, model_year, list_price);
                this.put(product.getId(), product);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Product> sortByModelYear(ArrayList re) {
        Comparator<Product> com = (Product p1, Product p2) -> {
            return Integer.compare(p1.getModelYear(), p2.getModelYear());
        };
        Collections.sort(re, com);
        return re;
    }

    public ArrayList<Product> sortByPriceName(ArrayList re) {
        /**
         * sort price desc, name length asc
         */

        Comparator com = (Comparator<Product>) (Product p1, Product p2) -> {
            double r = p2.getListPrice() - p1.getListPrice();
            if (r > 0) {
                return 1;
                //p2 come before p1- desc
            }
            if (r < 0) {
                return -1;
            } else {
                return p1.getName().compareTo(p2.getName());//length ordered asc
            }
        };
        Collections.sort(re, com);
        return re;
    }

    public void saveToFile(String fName) {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File not Exist");
        } else {

            try {

                FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw);

                for (Product x : this.toList()) {
                    System.out.println("Writing to file... " + x.toTextFile());
                    pw.println(x.toTextFile());
                    pw.flush();
                }

                pw.close();
                fw.close();
                System.out.println("Saved Successfully!");

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    public void createProduct() {

        inputter sc = new inputter();
        Product pro = new Product();
//-----
        String id = "";
        do {
            id = sc.getStringNotNull("Enter ID for a product", "Not Entered");
            if (isExistId(id)) {
                System.out.println("ID existed, re-enter: ");
            }
        } while (isExistId(id));
        pro.setId(id);
//-----
        String name = sc.getStringNotNull("Enter name: ", "Not Entered");
        pro.setName(name);
//-----

        int temp = 0;
        do {
            temp = sc.inputInt("Enter model year:");
            if (!isValidYear(temp)) {
                System.out.println("INVALID model year, re-enter: ");
            }
        } while (!isValidYear(temp));

        pro.setModelYear(temp);
//----

        String brand_id = "";
        do {

            brand_id = sc.getStringNotNull("Enter Brand ID (Brand ID must exist in file Brand.txt)", "Not Entered");
            if (!isExistBrandId(brand_id)) {
                System.out.println("Not existed in Brand.txt, re-enter");
            }
        } while (!isExistBrandId(brand_id)); //enter the same as in file Brand
        pro.setBrandId(brand_id);
//----
        String cat_id = "";
        do {

            cat_id = sc.getStringNotNull("Enter Category_ID (Category.txt)", "Not Entered");
            if (!isExistCatId(cat_id)) {
                System.out.println("Not existed in Category.txt, re-enter");
            }
        } while (!isExistCatId(cat_id));//enter the same as in file
        pro.setCatId(cat_id);
//---
        double listPrice = .2f;
        do {
            listPrice = sc.inputDouble("Enter price:");
            if (String.valueOf(listPrice).isEmpty()) {
                System.out.println("Not Entered, Re-enter price");
            }
        } while (listPrice <= 0);
        pro.setListPrice(listPrice);
//----

        this.put(pro.getId(), pro);
        System.out.println("Product added successfully!");

    }

    public Product getProductbyName(String name){
      
        for(Product i:this.toList()){
            if(i.getName().trim().toLowerCase().contains(name.toLowerCase().trim())){
                return i;
            }
        }
        return null;
    }

    public void searchByName(String prdName) {
        ProductList find = new ProductList();
        Product p=this.getProductbyName(prdName);
       if(p!=null){ 
           
                find.put(p.getId(), p);
            }
        
        if (!find.isEmpty()) {
            System.out.println("List containing the searched name");
            showList(find.toList());

            System.out.println("----------SORTED BY ASCENDING MODEL YEAR");

            ArrayList<Product> r = sortByModelYear(find.toList()); //sort the find list and then show it out
            showList(r);
        } else {
            System.out.println("Not found products");
        }
    }

    public boolean updateProduct(String id) {
        boolean found=false;
        inputter sc=new inputter();
        
        if(this.getProduct(id)!=null){
             Product pro=this.getProduct(id);
            System.out.println("Update product with ID:"+pro.getId());
           
            found=true;
            
            String name = sc.inputString("Enter name: ");
            if(name.isEmpty()) name=pro.getName();

            pro.setName(name);
//-----

        String temp  = sc.inputString("Enter model year:");        
        
        do{
            temp  = sc.inputString("Enter model year:");
         
           if (temp.isEmpty()) {
            
            pro.setModelYear(pro.getModelYear()); break;
        } 
            if (!isValidYear(Integer.parseInt(temp))) {
                System.out.println("INVALID model year, re-enter: ");
            } 
            

        }while (!isValidYear(Integer.parseInt(temp)));
        
            
        pro.setModelYear(Integer.parseInt(temp));
//----

        String brand_id = "";
        do {

            brand_id = sc.inputString("Enter Brand ID (Brand ID must exist in file Brand.txt)");
            if(brand_id.isEmpty()) {
                brand_id=pro.getBrandId(); break;
            }
       
            if (!isExistBrandId(brand_id)) {
                System.out.println("Not existed in Brand.txt, re-enter");
            }
           
        
        } while (!isExistBrandId(brand_id)); //enter the same as in file Brand
        pro.setBrandId(brand_id);
//----
        String cat_id = "";
        do {

            cat_id = sc.inputString("Enter Category_ID (Category.txt)");
            if(cat_id.isEmpty()) {
                cat_id=pro.getCatId(); break;
            }

            if (!isExistCatId(cat_id)) {
                System.out.println("Not existed in Category.txt, re-enter");
            }
            
        } while (!isExistCatId(cat_id));//enter the same as in file

        pro.setCatId(cat_id);
//---
        String listPrice = sc.inputString("Enter price:");
        double price=0.0;
        if(listPrice.isEmpty()) {
            pro.setListPrice(pro.getListPrice());
        } else {
        do {
            listPrice = sc.inputString("Enter price:");
            price=Double.parseDouble(listPrice);
            if(price<=0) {
                System.out.println("Invalid price, re-enter");
            }
        } while (price<= 0);
    }
                    pro.setListPrice(price);
//----

            System.out.println("After Update");
            System.out.println(pro.toTextFile());
        }
        return found;
    }

    public boolean isValidYear(int year) {
        Date temp = new Date();
        return year > 0 && year <= temp.getYear() + 1900;
    }

    public boolean deleteProduct(String id) {
        boolean found = false;
        inputter inp = new inputter();
            if (this.getProduct(id)!=null) {
                Product p=this.getProduct(id);
                int c = inp.inputInt("Confirm 1 to delete permanently\n");
                
                if (c == 1) {
                    this.remove(p.getId(), p);
                    System.out.println("DELETED!!!");
                }
                else System.out.println("Deletion process cancelled");
                found = true;
            }

        
        return found;

    }

    public boolean isExistBrandId(String brand_id) {
        BrandList b = new BrandList();
        boolean found = false;

        if (b.getBrandId(brand_id) != null) {
            found = true;
        }

        return found;
    }

    public boolean isExistCatId(String Catid) {
        CategoryList c = new CategoryList();
        boolean found = false;

        if (c.getCatId(Catid) != null) {
            found = true;
        }

        return found;
    }

    public Product getProduct(String id) {

        for (Product p : this.toList()) {
            if (p != null && p.getId() != null) {
                if (p.getId().equalsIgnoreCase(id)) {
                    return p;
                }
            }
        }

        return null;
    }

    public boolean isExistId(String id) {
        return getProduct(id) != null;
    }

    public void showAll() {
        System.out.println(TIEU_DE);
        for (Product i : this.toList()) {
            System.out.println(i);
        }
        System.out.println("----------------------------------------------------------");

    }

    public void showList(ArrayList<Product> list) {
        System.out.println(TIEU_DE);
        for (Product i : list) {
            System.out.println(i);
        }
        System.out.println("----------------------------------------------------------");

    }

}
