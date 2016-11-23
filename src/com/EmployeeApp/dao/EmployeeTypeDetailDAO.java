package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import org.apache.log4j.Logger;

import com.EmployeeApp.daoInterface.EmployeeTypeDetailDAOInterface;

public class EmployeeTypeDetailDAO implements EmployeeTypeDetailDAOInterface {
	
	static Logger log = Logger.getLogger(EmployeeTypeDetailDAO.class.getName());
	private Connection connection=DBUtils.getConnection();
	
	public EmployeeTypeDetailDAO()
	{
		
	}
	
	
	public static  EmployeeTypeDetailDAO getDAOObject() {
		
		return new EmployeeTypeDetailDAO();
	}
	
	
	public void addType(int id,int type)
	{
		log.info(new Date()+ " in addType "+EmployeeTypeDetailDAO.class);
		try
		{
			PreparedStatement ps=connection.prepareStatement("insert into employee_type_detail(employee_id,type_id)values(?,?)");
			ps.setInt(1,id);
			ps.setInt(2, type);
			ps.executeUpdate();
			
		}catch(Exception e)
		{
			log.error("unable to add type..."+e.getMessage());
			System.out.println("unable to add type...");
			e.printStackTrace();		
		}
	}
	
	public int  updateEmployeeType(int type,int id)
	{
		int status=0;
		log.info(new Date()+ " in addType "+EmployeeTypeDetailDAO.class);
		try
		{
			PreparedStatement ps=connection.prepareStatement("update employee_type_detail set type_id=? where employee_id=?");
			ps.setInt(1, type);
			 ps.setInt(2,id);
			 status=ps.executeUpdate();
			
		}catch(Exception e)
		{
			log.error("unable to add type..."+e.getMessage());
			System.out.println("unable to add type...");
			e.printStackTrace();		
		}
		return status;
	}
	
	public int deleteEmployeeTypeDetailById(int id)
	{
		log.info(new Date()+ " in deleteEmployeeById "+EmployeeTypeDetailDAO.class);
		int status=0;
		try
		{
			PreparedStatement ps=connection.prepareStatement("delete from employee_type_detail where employee_id=?");
			ps.setInt(1, id);
			status =ps.executeUpdate();
			
			
		}catch(Exception e)
		{
			log.error("unable to delete employees "+e.getMessage());
			System.out.println("unable to delete employees");
			e.printStackTrace();
		}
	return status;
	}

}
