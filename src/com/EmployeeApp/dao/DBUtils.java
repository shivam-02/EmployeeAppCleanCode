package com.EmployeeApp.dao;

import java.sql.*;

import org.apache.log4j.Logger;



public class DBUtils
{
	static Logger log = Logger.getLogger(DBUtils.class.getName());

	public static Connection getConnection() 
	{
	    Connection connection=null;
	    try
	      {
         Class.forName("com.mysql.jdbc.Driver");
         connection=  DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","root");
         log.info("Connection made "+connection);
	      }
	      catch(Exception e)
	      {
		  log.error("unable to create connection "+e.getMessage());
	      }
	 return connection;
	}
}

	
