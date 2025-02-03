/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Duy
 */
public class Category {
    protected String id;
    protected String type;
    public Category(){
        /**
         * Default constructor
         */
    }
    public Category(String id, String type){
        this.id=id;
        this.type=type;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getType(){
        return type;
    }
    @Override
    public String toString(){
        return String.format("%s, %s",getId(),getType());
    }
}
