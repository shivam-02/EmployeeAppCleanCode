package com.EmployeeApp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.EmployeeApp.dao.DepartmentDAO;
import com.EmployeeApp.dao.EmployeeDAO;
import com.EmployeeApp.dao.EmployeeDepartmentDetailDAO;
import com.EmployeeApp.dao.EmployeeTypeDetailDAO;
import com.EmployeeApp.daoInterface.DepartmentDAOInterface;
import com.EmployeeApp.daoInterface.EmployeeDAOInterface;
import com.EmployeeApp.daoInterface.EmployeeDepartmentDetailDAOInterface;
import com.EmployeeApp.daoInterface.EmployeeTypeDetailDAOInterface;
import com.EmployeeApp.model.Employee;

import java.text.SimpleDateFormat;


@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(AddEmployee.class.getName());
	private String name;
	private Date startDate;
	private Date endDate;
	private String description;
	private double salary;
	private String address;
	private String city;
	private String state;
	private String country;
	//Names should be meaningful and pronounceable
	//String d;
	String departmentListString;
	int type;
	private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	private int status=0;

	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		log.info(new Date()+":: in AddEmployee info :: " +AddEmployee.class);
	    try
	    {
	    	PrintWriter out = response.getWriter();
	        name=request.getParameter("name");
	        startDate=formatter.parse(request.getParameter("startDate"));
	        endDate=formatter.parse(request.getParameter("endDate"));
	        description=request.getParameter("description");
	        if(request.getParameter("salary")=="" || request.getParameter("salary")==null)
	        {
	        	salary=0.0d;
	        }
	        else
	        {
	        salary=Double.parseDouble(request.getParameter("salary"));
	        }
	        address=request.getParameter("address");
	        city=request.getParameter("city");
	        state=request.getParameter("state");
	        country=request.getParameter("country");
	        //d= request.getParameter("departmentList");
	        departmentListString= request.getParameter("departmentList");
	        type=Integer.parseInt(request.getParameter("type"));
	        //Instead of debugging from console using logging mechanism like log4j
	        //System.out.println(d);
	         ArrayList<String> departmentList= new ArrayList(Arrays.asList(departmentListString.split(",")));
	         ArrayList<Integer> departmentId=new ArrayList<Integer>();
	         for(int i=0;i<departmentList.size();i++)
	         {
	        	 int a=Integer.parseInt(departmentList.get(i));
	        	 departmentId.add(a);
	         }
	       // Constructor with more than 3 arguments should be avoided
	       // Employee employee=new Employee(name,description,address,city,state,country,salary, startDate, endDate);
	         
	         
	         Employee employee=new Employee();
	         employee.setName(name);
	         employee.setDescription(description);
	         employee.setAddress(address);
	         employee.setCity(city);
	         employee.setState(state);
	         employee.setCountry(country);
	         employee.setSalary(salary);
	         employee.setStartDate(startDate);
	         employee.setEndDate(endDate);
	         EmployeeDAOInterface employeeDAOInterface=EmployeeDAO.getDAOObject();
	         status=employeeDAOInterface.addEmployee(employee);
	         int id=employeeDAOInterface.getCurrentEmployeeId(name);
	         EmployeeTypeDetailDAOInterface employeeTypeDetailDAOInterface=EmployeeTypeDetailDAO.getDAOObject();
	         employeeTypeDetailDAOInterface.addType(id,type);
	        EmployeeDepartmentDetailDAOInterface employeeDepartmentDetailDAOInterface = EmployeeDepartmentDetailDAO.getDAOObject();
	        employeeDepartmentDetailDAOInterface.addEmployeeDepartmentDetail(departmentId,id);
	   
	         if(status!=0)
	         {
	         out.println("{");
	         out.println("\"success\": \"true\",");
	         out.println("\"employeeId\":"+id+",");
	         out.println("\"message\": \"Employee Added Successfully\"");
	         out.println("}");
	         }
	         else
	         {
	    	  out.println("{");
		      out.println("\"success\": \"false\",");
		      out.println("\"message\": \"Unable to add..\"");
		      out.println("}"); 
	         }
	   }
	    catch (Exception e)
	    {
	        System.out.println(e);
	        log.error("unable to add "+e.getMessage());
	        e.printStackTrace();
	    }
	
	}
}


