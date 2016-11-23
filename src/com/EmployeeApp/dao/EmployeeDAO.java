package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.model.EmployeeJson;
import com.EmployeeApp.model.EmployeeType;
import com.EmployeeApp.model.EmployeeVO;
import com.EmployeeApp.dao.DBUtils;
import com.EmployeeApp.daoInterface.EmployeeDAOInterface;
import com.EmployeeApp.daoInterface.EmployeeTypeDetailDAOInterface;

public class EmployeeDAO implements EmployeeDAOInterface {
	
	static Logger log = Logger.getLogger(EmployeeDAO.class.getName());
	private Connection connection=DBUtils.getConnection();
	
	public EmployeeDAO()
	{
		
	}
	
	
	public static  EmployeeDAO getDAOObject() {
		
		return new EmployeeDAO();
	}
	
	public int deleteEmployeeById(int id)
	{
		log.info(new Date()+ " in deleteEmployeeById "+EmployeeDAO.class);
		int status=0;
		try
		{
			PreparedStatement ps;
			ps=connection.prepareStatement("delete from employee where id=?");
			ps.setInt(1, id);
			status=ps.executeUpdate();
			
		}catch(Exception e)
		{
			log.error("unable to delete employees "+e.getMessage());
			System.out.println("unable to delete employees");
			e.printStackTrace();
		}
	return status;
	}
	
	
	public int addEmployee(Employee employee)
	{
		log.info(new Date()+ " in addEmployee "+EmployeeDAO.class);  
		int status=0;
		try{
			PreparedStatement ps=connection.prepareStatement("insert into employee(name,start_date,end_date,description,salary,address,city,state,country) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, employee.getName());
			ps.setDate(2, new java.sql.Date(employee.getStartDate().getTime()));
			ps.setDate(3, new java.sql.Date(employee.getEndDate().getTime()));
			ps.setString(4, employee.getDescription());
			ps.setDouble(5, employee.getSalary());
			ps.setString(6, employee.getAddress());
			ps.setString(7,employee.getCity());
			ps.setString(8, employee.getState());
			ps.setString(9,employee.getCountry());
			status=ps.executeUpdate();
				
		    }catch(SQLException se)
		     {
		     log.error("Unable to add..." +se.getMessage());
			 System.out.println("Unable to add...");
			 se.printStackTrace();
		     }
		    
		return status;
	}
	
	public int getCurrentEmployeeId(String name)
	{
		log.info(new Date()+ " in getCurrentEmployeeId "+EmployeeDAO.class);
		int id=-1;
		try
		{
			PreparedStatement ps=connection.prepareStatement("select id from employee where name=?");
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				id=rs.getInt("id");
			}
			
		}catch(Exception e)
		{
			log.error("unable to get current employee" +e.getMessage());
			System.out.println("unable to get current employee");
			e.printStackTrace();
		}
	return id;
	}
	
	public List<EmployeeVO> getEmployeeByType(int employeeTypeId,int start,int limit)
	{
		log.info(new Date()+ " in getEmployeeByType "+EmployeeDAO.class);
		List<EmployeeVO> employees=new ArrayList<EmployeeVO>();
		boolean isFound=false;
		try{
			PreparedStatement ps;
			if(employeeTypeId==4)
			{
				ps=connection.prepareStatement("select employee.id as id, name , start_date as startDate, end_Date as endDate, type_name as type, salary, address, city, state, country from employee,employee_type,employee_type_detail where employee.id=employee_type_detail.employee_id and employee_type.id=employee_type_detail.type_id limit ?,?");
				ps.setInt(1, start);
				ps.setInt(2, limit);
			}
			else
			{
				ps=connection.prepareStatement("select employee.id as id, name , start_date as startDate, end_Date as endDate, type_name as type, salary, address, city, state, country from employee,employee_type,employee_type_detail where employee.id=employee_type_detail.employee_id and employee_type.id=employee_type_detail.type_id and  employee_type.id=? limit ?,?");
				ps.setInt(1, employeeTypeId);
				ps.setInt(2,start);
				ps.setInt(3, limit);
			}
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				isFound=false;
				EmployeeVO employeeVO=new EmployeeVO();
				employeeVO.setId(rs.getInt("id"));
				employeeVO.setName(rs.getString("name"));
				employeeVO.setStartDate(rs.getDate("startDate"));
				employeeVO.setEndDate(rs.getDate("endDate"));
				employeeVO.setType(rs.getString("type"));
				employeeVO.setSalary(rs.getDouble("salary"));
				employeeVO.setAddress(rs.getString("address"));
				employeeVO.setCity(rs.getString("city"));
				employeeVO.setState(rs.getString("state"));
				employeeVO.setCountry(rs.getString("country"));
				employees.add(employeeVO);
			}
			
			
			
		  }
		 catch(SQLException se)
		 {
			log.info("unable to fetch..."+se.getMessage());
			System.out.println("unable to fetch...");
			se.printStackTrace();
		 }
		
	return employees;
		
	}
	
	
	public int getEmployeeCount(int employeeTypeId)
	{
		log.info(new Date()+ " in getEmployeeCount "+EmployeeDAO.class);
		int count=0;
		try
		{	
		PreparedStatement ps;
		if(employeeTypeId==4)
		 {
		ps=connection.prepareStatement("select count(*) from employee,employee_type,employee_type_detail where employee.id=employee_type_detail.employee_id and employee_type.id=employee_type_detail.type_id");
	    ResultSet rs=ps.executeQuery();
		while(rs.next())
		   {
			 count=rs.getInt(1);
		   }
		 
		  }
		
		else
		  {
			ps=connection.prepareStatement("select count(*) from employee,employee_type,employee_type_detail where employee.id=employee_type_detail.employee_id and employee_type.id=employee_type_detail.type_id and employee_type.id=?");
			ps.setInt(1, employeeTypeId);
			ResultSet rs=ps.executeQuery();
			 while(rs.next())
			 {
				 count=rs.getInt(1);
			 }
		
		  }
	     }catch(SQLException se)
		  {
	       log.error("Unable to fetch count..."+se.getMessage());
		   System.out.println("Unable to fetch count...");
		   se.printStackTrace();
		   }
		
	return count;
	}
	
	
	/*public List<EmployeeType> getListTypes()
	{
		log.info(new Date()+ " in getListTypes "+EmployeeDAO.class);
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
	}*/
	
	/*public void addType(int id,int type)
	{
		log.info(new Date()+ " in addType "+EmployeeDAO.class);
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
	}*/
 
    public int updateEmployee(EmployeeJson employeeJson)
    { 
    	log.info(new Date()+ " in updateEmployee "+EmployeeDAO.class);
    	DateFormat df=new SimpleDateFormat("MM/dd/YYYY");
    	int type=-1;
    	int count=-2;
    	int status;
    	try
    	{
    		PreparedStatement ps= connection.prepareStatement("update employee set name=?,start_date=?,end_date=?,description=?,salary=?,address=?,city=?,state=?,country=? where id=?");
		    ps.setString(1, employeeJson.getName());
			ps.setDate(2, new java.sql.Date(df.parse(employeeJson.getStartDate()).getTime()));
			ps.setDate(3, new java.sql.Date(df.parse(employeeJson.getEndDate()).getTime()));
			ps.setString(4, employeeJson.getDescription());
			ps.setDouble(5, employeeJson.getSalary());
			ps.setString(6, employeeJson.getAddress());
			ps.setString(7,employeeJson.getCity());
			ps.setString(8, employeeJson.getState());
			ps.setString(9,employeeJson.getCountry());
			ps.setInt(10, employeeJson.getId());
		    status=ps.executeUpdate();
		    if(status!=0)count++;
			type=employeeJson.getType();
			if(type!=-1)
			  {
			   EmployeeTypeDetailDAOInterface employeeTypeDetailDAOInterface= EmployeeTypeDetailDAO.getDAOObject();
			   status=employeeTypeDetailDAOInterface.updateEmployeeType(type,employeeJson.getId());
			   if(status!=0)count++;
			   System.out.println("in update dao 2");
			  }
		  
    	}
    	catch(Exception e)
    	{
		 log.error("unable to update Employee " +e.getMessage());
    	}
	return count;
    }




	
	
}
