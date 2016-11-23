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

import com.EmployeeApp.dao.EmployeeDAO;
import com.EmployeeApp.daoInterface.EmployeeDAOInterface;
import com.EmployeeApp.model.EmployeeVO;
import com.EmployeeApp.result.ExtResult;
import com.google.gson.Gson;


@WebServlet("/ListEmployee")
public class ListEmployee extends HttpServlet
{
	//Variables should have vertical density
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(ListEmployee.class.getName());
	private String callback;
	private int start;
	private int limit;
	private int employeeTypeId;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		log.info(new Date()+":: in ListEmployee info :: " +ListEmployee.class);
		if(request.getParameter("id")==null || request.getParameter("id").isEmpty())
		{
		employeeTypeId=4;
		}
		else
		{
		employeeTypeId=Integer.parseInt(request.getParameter("id"));
		}
		
		start=Integer.parseInt(request.getParameter("start"));
		limit=Integer.parseInt(request.getParameter("limit"));
		EmployeeDAOInterface employeeDAOInterface=EmployeeDAO.getDAOObject();
		List<EmployeeVO> employees=employeeDAOInterface.getEmployeeByType(employeeTypeId,start,limit);
		int totalCount=employeeDAOInterface.getEmployeeCount(employeeTypeId);
		ExtResult extResult=new ExtResult(totalCount,employees);
		callback = request.getParameter("callback");
		PrintWriter pw=response.getWriter();
		Gson gson = new Gson();
		String jsonInString = callback + "(" + gson.toJson(extResult) + ")";
		System.out.println(jsonInString);
		pw.print(jsonInString);
		pw.flush();
		
		
	}

}
