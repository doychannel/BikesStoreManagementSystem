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
public class Brand {
   private String id;
   private String type;
   private String nation;
          
    public Brand(){}
    public Brand(String id, String type, String nation){
        this.id=id;        
        this.type=type;
        this.nation=nation;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getNation(){
        return nation;
    }
    public void setNation(String nation){
        this.nation=nation;
    }

   @Override
    public String toString(){
        return String.format("%s, %s, %s", getId(),getType(),getNation());
    }
}
