package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.EmployeeApp.dao.DepartmentDAO;
import com.EmployeeApp.daoInterface.DepartmentDAOInterface;
import com.EmployeeApp.model.Department;
import com.EmployeeApp.result.ExtDepartmentResult;
import com.EmployeeApp.result.ExtTypeResult;
import com.google.gson.Gson;


@WebServlet("/departmentList")
public class DepartmentList extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(LogoutServlet.class.getName());
	private String callback;
    
   
    public DepartmentList() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		log.info(new Date()+":: in DepartmentList info :: " +DepartmentList.class);
		DepartmentDAOInterface departmentDAOInterface = DepartmentDAO.getDAOObject();
		List<Department> departmentList= departmentDAOInterface.getDepartmentList();
		callback = request.getParameter("callback");
        ExtDepartmentResult extDeptResult=new ExtDepartmentResult(departmentList);
		PrintWriter pw=response.getWriter();
		Gson gson = new Gson();
		String jsonInString = callback + "(" + gson.toJson(extDeptResult) + ")";
		pw.print(jsonInString);
		pw.flush();
	}

}
