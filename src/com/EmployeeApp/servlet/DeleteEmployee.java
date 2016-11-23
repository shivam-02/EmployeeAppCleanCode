package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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

@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(AddEmployee.class.getName());
	
    public DeleteEmployee() {
        super();
        
    }

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	{
		log.info(new Date()+":: in DeleteEmployee info :: " +DeleteEmployee.class);
		try
		{
		String empIds=request.getParameter("empIds");
		String [] empIdList=empIds.split(",");
		System.out.println(empIdList);
		int count=empIdList.length;
		
		PrintWriter out = response.getWriter();
		for(int i=0;i<empIdList.length;i++)
		{
		
			int empId=Integer.parseInt(empIdList[i]);
			 EmployeeDepartmentDetailDAOInterface employeeDepartmentDetailDAOInterface = EmployeeDepartmentDetailDAO.getDAOObject();
			 employeeDepartmentDetailDAOInterface.deleteEmployeeDepartmentDetail(empId);
			 EmployeeTypeDetailDAOInterface  employeeTypeDetailDAOInterface =EmployeeTypeDetailDAO.getDAOObject();
			 employeeTypeDetailDAOInterface.deleteEmployeeTypeDetailById(empId);
			EmployeeDAOInterface employeeDAOInterface=EmployeeDAO.getDAOObject();
			int status= employeeDAOInterface.deleteEmployeeById(empId);
			if(status!=0)count--;
		    }
	    
		
		if(count==0)
		{
		 out.println("{");
	     out.println("\"success\": \"true\",");
	     out.println("\"message\": \"Employees Deleted Successfully\"");
	     out.println("}");
	     }
		else
	    {
		out.println("{");
        out.println("\"success\": \"false\",");
        out.println("\"message\": \"Unable to delete selected employees..\"");
        out.println("}");
	    }
		}catch(Exception e)
	     {
			System.out.println("unable to delete Selected Employees");
			e.printStackTrace();
		     }
	}
}
