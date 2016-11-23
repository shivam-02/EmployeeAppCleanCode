package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.EmployeeApp.daoInterface.EmployeeTypeDAOInterface;
import com.EmployeeApp.model.EmployeeType;

public class EmployeeTypeDAO implements EmployeeTypeDAOInterface{
	
	static Logger log = Logger.getLogger(EmployeeTypeDAO.class.getName());
	private Connection connection=DBUtils.getConnection();
	
	public EmployeeTypeDAO()
	{
		
	}
	
	
	public static  EmployeeTypeDAO getDAOObject() {
		
		return new EmployeeTypeDAO();
	}
	
	public List<EmployeeType> getListTypes()
	{
		log.info(new Date()+ " in getListTypes "+EmployeeTypeDAO.class);
		List<EmployeeType> listTypes=new ArrayList<EmployeeType>();
		
		try{
			
			PreparedStatement ps;
			ps=connection.prepareStatement("select * from employee_type");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			 {
				
				EmployeeType employeeType=new EmployeeType();
				employeeType.setId(rs.getInt("id"));
				employeeType.setTypeName(rs.getString("type_name"));
				listTypes.add(employeeType);
			 }
			
			
		   }catch(SQLException se)
		    {
			log.error("Unable to get list types..."+se.getMessage());
			System.out.println("Unable to get list types...");
			se.printStackTrace();
		    }
		
		
		
		
	return listTypes;
	}

}
