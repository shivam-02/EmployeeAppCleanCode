package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.EmployeeApp.dao.DepartmentDAO;
import com.EmployeeApp.dao.EmployeeDAO;
import com.EmployeeApp.daoInterface.EmployeeDAOInterface;
import com.EmployeeApp.model.Employee;
import com.EmployeeApp.model.EmployeeJson;
import com.EmployeeApp.model.EmployeeVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet
{

   private static final long serialVersionUID = 1L;
   static Logger log = Logger.getLogger(AddEmployee.class.getName());
   private int id;
   private String name;
   private Date startDate;
   private Date endDate;
   private String description;
   private double salary;
   private String address;
   private String city;
   private String state;
   private String country;
   private String d;
   int type=-1;
   private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
   int status=0;
	
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
	   log.info(new Date()+":: in UpdateEmployee info :: " +UpdateEmployee.class);
	   PrintWriter out= response.getWriter();
       try
       {	
	   
       EmployeeDAOInterface employeeDAOInterface=EmployeeDAO.getDAOObject();
	   String jsonString=request.getHeader("employeeDataJsonObject");
	   System.out.println(jsonString);
       Gson gson =new Gson();
	   EmployeeJson employeeJson=gson.fromJson(jsonString,EmployeeJson.class);
	   
   //  Employee employee=new Employee(name,description,address,city,state,country,salary, startDate, endDate);
       int status=employeeDAOInterface.updateEmployee(employeeJson);
	   if(status==0)
	     {
	        out.println("{");
	        out.println("\"success\": \"true\",");
	        out.println("\"message\": \"Employee Updated Succesfully\"");
	        out.println("}");
	     }
	   else
	     {
		   	out.println("{");
		    out.println("\"success\": \"false\",");
		    out.println("\"message\": \"Unable to update..\"");
		    out.println("}");  
	     }	
	   }
	   catch(Exception e)
		 {
			log.error("unable to update "+e.getMessage() );
			out.println("{");
		    out.println("\"success\": \"false\",");
		    out.println("\"message\": \"Unable to update..\"");
		    out.println("}");
		  }
		
	}

}
