package com.KS.katarias;

public class Product {
  private String id;
  private String description;
  private int price;
  
  public Product(String id,int price)
  {
	  this(id,price,"");
  }
  
  public Product(String id,int price,String description)
  {
	  this.id = id;
	  this.price = price;
	  this.description = description;
  }
  public String getId()
  {
	  return id;
  }
  
  public int getPrice()
  {
	  return price;
  }
  
  public String getDescription()
  {
	  return description;
  }
}
