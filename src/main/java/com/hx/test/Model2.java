package com.hx.test;

import  javax.persistence.Entity;  
import  javax.persistence.GeneratedValue;  
import  javax.persistence.GenerationType;  
import  javax.persistence.Id;  
import  javax.persistence.ManyToOne;  
import  javax.persistence.Table;  
import  javax.persistence.TableGenerator;  
import  javax.validation.Valid;  
import  javax.validation.constraints.Max;  
import  javax.validation.constraints.Min;  
import  javax.validation.constraints.NotNull;  
import  javax.validation.constraints.Size;  
  
import  org.hibernate.validator.constraints.Email;  
import  org.hibernate.validator.constraints.URL;  
  
@Entity   
@Table (name= "t_Model2" )  
@TableGenerator (name= "mytable" ,initialValue= 1 ,allocationSize= 1 )  
public   class  Model2 {  
  
    private   int  id;  
    private  String name;  
    private  String address;  
    private  String phoneNumber;  
    private  String email;  
    private   int  age;  
    private  Model3 model3;  
    private  String url;  
  
    @Id   
    @GeneratedValue (generator= "mytable" ,strategy=GenerationType.TABLE)  
    public   int  getId() {  
        return  id;  
    }  
  
    public   void  setId( int  id) {  
        this .id = id;  
    }  
  
    @NotNull (message= "姓名不能为空！" )  
    public  String getName() {  
        return  name;  
    }  
  
    public   void  setName(String name) {  
        this .name = name;  
    }  
  
    @NotNull (message= "地址不能为空！" )  
    public  String getAddress() {  
        return  address;  
    }  
  
    public   void  setAddress(String address) {  
        this .address = address;  
    }  
  
    @Size (max= 11 ,min= 11 ,message= "长度只能为11位！" )  
    public  String getPhoneNumber() {  
        return  phoneNumber;  
    }  
  
    public   void  setPhoneNumber(String phoneNumber) {  
        this .phoneNumber = phoneNumber;  
    }  
  
    @Email (message= "email地址无效！" )  
    @NotNull (message= "email地址不能为空！" )  
    public  String getEmail() {  
        return  email;  
    }  
  
    public   void  setEmail(String email) {  
        this .email = email;  
    }  
  
    @NotNull (message =  "Model3不能为空！" )  
    @Valid   
    @ManyToOne   
    public  Model3 getModel3() {  
        return  model3;  
    }  
  
    public   void  setModel3(Model3 model3) {  
        this .model3 = model3;  
    }  
  
    @Min (value= 18 ,message= "必须年满18岁！" )  
    @Max (value= 30 ,message= "年龄不能大于30岁！" )  
    public   int  getAge() {  
        return  age;  
    }  
  
    public   void  setAge( int  age) {  
        this .age = age;  
    }  
  
    @URL (message= "无效的URL地址" )  
    @NotNull (message =  "URL不能为空！" )  
    public  String getUrl() {  
        return  url;  
    }  
  
    public   void  setUrl(String url) {  
        this .url = url;  
    }

	public void add(Model2 model) {
		// TODO Auto-generated method stub
		
	}  
      
}  
