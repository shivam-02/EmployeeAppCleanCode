package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.EmployeeApp.daoInterface.EmployeeDepartmentDetailDAOInterface;

public class EmployeeDepartmentDetailDAO implements EmployeeDepartmentDetailDAOInterface {
	
	static Logger log = Logger.getLogger(EmployeeDepartmentDetailDAO.class.getName());
	private Connection connection=DBUtils.getConnection();

	public EmployeeDepartmentDetailDAO() {
		super();
	}
	
public static  EmployeeDepartmentDetailDAO getDAOObject() {
		
		return new EmployeeDepartmentDetailDAO();
	}


public void deleteEmployeeDepartmentDetail(int id)
{
	log.info(new Date()+ " in deleteEmployeeDepartmentDetail "+EmployeeDepartmentDetailDAO.class);
	try
	{
		PreparedStatement ps=connection.prepareStatement("delete from employee_department_detail where employee_id=?");
		ps.setInt(1, id);
		ps.executeUpdate();
		
	}catch(Exception e)
	{
		log.error("unable to delete from employee_department_detail "+e.getMessage());
		System.out.println("unable to delete from employee_department_detail");
		e.printStackTrace();
	}
}


public void updateEmployeeDepartmentDetail(ArrayList<Integer>departmentId,int id)
{  

	log.info(new Date()+ " in updateEmployeeDepartmentDetail "+EmployeeDepartmentDetailDAO.class);
    int status=-1;
	for(int i=0; i< departmentId.size(); i++)
	{
	try
	    {
		PreparedStatement ps;
		ps= connection.prepareStatement("update employee_department_detail set department_id=? where employee_id=?");
		ps.setInt(1, departmentId.get(i));
		ps.setInt(2, id);
		status= ps.executeUpdate();	
	    }
	catch(Exception e)
		{
		log.error("Unable to insert department "+e.getMessage());
		System.out.println("Unable to insert department");
		e.printStackTrace();
	    }
	 }
}

public void addEmployeeDepartmentDetail(ArrayList<Integer> list, int id)
{
	log.info(new Date()+ " in addEmployeeDepartmentDetail "+EmployeeDepartmentDetailDAO.class);
	int status;
	
	for(int i=0; i< list.size(); i++)
	{ 
	try
	   {
		PreparedStatement ps;
		ps= connection.prepareStatement("insert into employee_department_detail(employee_id,department_id) values(?,?) ");
		ps.setInt(1,id);
		System.out.println(list.get(i));
		ps.setInt(2, list.get(i));
		ps.executeUpdate();
	   }
	   catch(Exception e)
	   {
		log.error("Unable to insert department "+e.getMessage());
		System.out.println("Unable to insert department");
		e.printStackTrace();
	   }	
    }

}
}
