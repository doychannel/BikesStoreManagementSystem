/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;


/**
 *
 * @author Duy
 */
public class Product implements Serializable{
    private String id;
    private String name;
    private String BrandId;
    private String CatId;
    private int modelYear;
    private double listPrice;
    
    public Product(){}
    
    public Product(String id,String name,String BrandId,
            String CatId,int modelYear,double listPrice){
        
        this.id=id;
        this.name=name;
        this.BrandId=BrandId;
        this.CatId=CatId;
        this.modelYear=modelYear;
        this.listPrice=listPrice;
    }
    public void setId(String id){
    this.id=id;
    }
    public String getId() {
    return id;
    }
    public void setName(String name){
    this.name=name;
}
    public String getName(){
        return name;
    }
    public void setBrandId(String BrandId){
        this.BrandId=BrandId;
    }
    public String getBrandId(){
        return BrandId;
    }
    public void setCatId(String CatId){
        this.CatId=CatId;
    }
    public String getCatId(){
        return CatId;
    }
     public void setModelYear(int modelYear){
        this.modelYear=modelYear;
    }
    public int getModelYear(){
        return modelYear;
    }
    public void setListPrice(double listPrice){
        this.listPrice=listPrice;
    }
    public double getListPrice(){
        return listPrice;
    }
    @Override
    public String toString(){
        return String.format("|%7s|%-20s|%5s|%5s|%6d|%8.2f|",this.id,this.name,this.BrandId,this.CatId,this.modelYear,this.listPrice);
    } 
    public String toTextFile(){
        return String.format("%s, %s, %s, %s, %d, %.2f",this.id,this.name,this.BrandId,this.CatId,this.modelYear,this.listPrice);
    }
}
