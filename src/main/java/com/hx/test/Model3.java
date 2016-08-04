package com.hx.test;

import  javax.persistence.Entity;  
import  javax.persistence.GeneratedValue;  
import  javax.persistence.Id;  
import  javax.persistence.Table;  
import  javax.validation.constraints.NotNull;  
  
@Entity   
@Table (name= "t_Model3" )  
public   class  Model3 {  
  
    private   int  id;  
      
    private  String name;  
  
    @Id   
    @GeneratedValue   
    public   int  getId() {  
        return  id;  
    }  
  
    public   void  setId( int  id) {  
        this .id = id;  
    }  
  
    @NotNull (message= "model3的名称不能为空！" )  
    public  String getName() {  
        return  name;  
    }  
  
    public   void  setName(String name) {  
        this .name = name;  
    }  
      
}  
 
