package com.EmployeeApp.dao;
import com.EmployeeApp.model.Department;
import com.EmployeeApp.model.EmployeeType;
import com.EmployeeApp.dao.DBUtils;
import com.EmployeeApp.daoInterface.DepartmentDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class DepartmentDAO implements DepartmentDAOInterface

{
	static Logger log = Logger.getLogger(DepartmentDAO.class.getName());
	private Connection connection=DBUtils.getConnection();

	public DepartmentDAO() {
		super();
	}
	
public static  DepartmentDAO getDAOObject() {
		
		return new DepartmentDAO();
	}
	
	/*public void deleteEmployeeDepartmentDetail(int id)
	{
		log.info(new Date()+ " in deleteEmployeeDepartmentDetail "+DepartmentDAO.class);
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
	}*/
	
	public List<Department> getDepartmentList()
	{
		log.info(new Date()+ " in getDepartmentList "+DepartmentDAO.class);
		List<Department> departmentList= new ArrayList<Department>();
		try
		{
			PreparedStatement ps;
			ps= connection.prepareStatement("select * from department");
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
			Department department=new Department();
			department.setId(rs.getInt("id"));
			department.setDepartmentName(rs.getString("department_name"));
			departmentList.add(department);	
			}		
		}
		catch(Exception e)
		{
			log.error("Unable to get department list "+e.getMessage());
			System.out.println("Unable to get department list");
			e.printStackTrace();
		}
	   return departmentList;
	}
	
	/*public void updateEmployeeDepartmentDetail(ArrayList<Integer>departmentId,int id)
	{  
	
		log.info(new Date()+ " in updateEmployeeDepartmentDetail "+DepartmentDAO.class);
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
	}*/
	
	/*public void addEmployeeDepartmentDetail(ArrayList<Integer> list, String name)
	{
		log.info(new Date()+ " in addEmployeeDepartmentDetail "+DepartmentDAO.class);
		int status;
		int id=-1;
		for(int i=0; i< list.size(); i++)
		{ 
		try
		   {
			PreparedStatement ps;
			ps= connection.prepareStatement("select id from employee where name=? ");
			ps.setString(1, name);
			ResultSet rs1= ps.executeQuery();
			if(rs1.next())
			    {
				System.out.println(rs1.getInt("id"));
				id=rs1.getInt("id");
			     }
			else
			    {
				System.out.println("No employee with corresponding name exits");
				throw new Exception();
			    }
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
	    }*/
		
	}

	

