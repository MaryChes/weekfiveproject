package com.weekfiveproject;

import java.sql.*;
import java.util.ArrayList;

public class DAO 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";
	
	public static ArrayList<Bakery> ourBakery = new ArrayList<>();
	
	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;
	
	public static void connToDB() 
	{

		try {
			Class.forName(JDBC_DRIVER);
			
			System.out.println("Trying to connect to the DB...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to DB.");

		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("Connection failed");
			e.printStackTrace();
		}

	}//connect

	public static void viewDB() 
	{
		connToDB();
		try 
		{
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM bakery.bakery;");

			while (RES_SET.next()) 
			{
				Bakery itemInDB = new Bakery();

				itemInDB.setBakeryID((RES_SET.getString("bakery_id")));
				itemInDB.setType(RES_SET.getString("type"));
				itemInDB.setCalories(RES_SET.getString("calories"));
				itemInDB.setPrice(RES_SET.getString("price"));
				itemInDB.setTopping(RES_SET.getString("topping"));

				ourBakery.add(itemInDB);
			}

			for (Bakery bakery : ourBakery) 
			{
				System.out.println(bakery);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	} //view
	
	
	private static String insertIntoTable = "INSERT INTO `bakery`.`bakery`" + "(type, calories, price, topping)"
			+ " VALUES " + "(?, ?, ?, ?)";
	
	public static void writeToDB (Bakery bakery)
	{
		Bakery itemToAdd = new Bakery();
		
		itemToAdd = bakery;
		
		try 
		{
			connToDB();
		
			PREP_STMT = CONN.prepareStatement(insertIntoTable);
		
			PREP_STMT.setString(1, itemToAdd.getType());
			PREP_STMT.setString(2, itemToAdd.getCalories());
			PREP_STMT.setString(3, itemToAdd.getPrice());
			PREP_STMT.setString(4, itemToAdd.getTopping());
		
			System.out.println(PREP_STMT);
		
			PREP_STMT.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	
	}//write
	
	
	private static String deleteFromTable(int id)
	{
		return "DELETE FROM `bakery`.`bakery` WHERE bakery_id = " + id + ";";  
	}
	
	public static void deleteFromDB(int id)
	{
		connToDB();
		
		try
		{
			PREP_STMT = CONN.prepareStatement(deleteFromTable(id));
			
			PREP_STMT.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}//delete
	
	private static String modifyfromDB = "UPDATE `bakery`.`bakery`"
			+ "SET"
			+" type= ?, calories= ?, price= ?, topping= ?"
			+ " WHERE "
			+ "`bakery_id`"
			+ "= ?";
	
	public static void updateToDB()
	{
		Bakery itemToModify = new Bakery();
		connToDB();
		
		try
		{
			PREP_STMT = CONN.prepareStatement(modifyfromDB);
			
			PREP_STMT.setString(1, itemToModify.getType());
			PREP_STMT.setString(2, itemToModify.getCalories());
			PREP_STMT.setString(3, itemToModify.getPrice());
			PREP_STMT.setString(4, itemToModify.getTopping());
			PREP_STMT.setString(5, itemToModify.getBakeryID());
			
			PREP_STMT.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}//update
	
}//end class
