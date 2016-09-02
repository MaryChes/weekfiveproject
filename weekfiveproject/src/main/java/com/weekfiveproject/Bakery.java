package com.weekfiveproject;

public class Bakery 
{
	private String bakeryID = null;
	private String type = null;
	private String calories = null;
	private String price = null;
	private String topping = null;
	
	public Bakery(String bakeryID, String type, String calories, String price, String topping) 
	{
		super();
		this.bakeryID = bakeryID;
		this.type = type;
		this.calories = calories;
		this.price = price;
		this.topping = topping;
	}

	public Bakery() 
	{
		
	}

	public String getBakeryID() 
	{
		return bakeryID;
	}

	public void setBakeryID(String bakeryID) 
	{
		this.bakeryID = bakeryID;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public String getCalories() 
	{
		return calories;
	}

	public void setCalories(String calories) 
	{
		this.calories = calories;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getTopping() 
	{
		return topping;
	}

	public void setTopping(String topping) 
	{
		this.topping = topping;
	}
	
	
	
	
	
}//class
