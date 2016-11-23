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
import com.EmployeeApp.dao.EmployeeTypeDAO;
import com.EmployeeApp.dao.EmployeeTypeDetailDAO;
import com.EmployeeApp.daoInterface.EmployeeDAOInterface;
import com.EmployeeApp.daoInterface.EmployeeTypeDAOInterface;
import com.EmployeeApp.daoInterface.EmployeeTypeDetailDAOInterface;
import com.EmployeeApp.model.EmployeeType;
import com.EmployeeApp.result.ExtResult;
import com.EmployeeApp.result.ExtTypeResult;
import com.google.gson.Gson;


@WebServlet("/ListEmployeeType")
public class ListEmployeeType extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(ListEmployeeType.class.getName());
	private String callback;
       
   
    public ListEmployeeType() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		log.info(new Date()+":: in ListEmployeeType info :: " +ListEmployeeType.class);
		EmployeeTypeDAOInterface employeeTypeDAOInterface=EmployeeTypeDAO.getDAOObject();
		List<EmployeeType> employeeListTypes=employeeTypeDAOInterface.getListTypes();
		response.setContentType("application/json");
		callback = request.getParameter("callback");
		ExtTypeResult extTypeResult=new ExtTypeResult(employeeListTypes);
		PrintWriter pw=response.getWriter();
		Gson gson = new Gson();
		String jsonInString = callback + "(" + gson.toJson(extTypeResult) + ")";
		System.out.println(jsonInString);
		pw.print(jsonInString);
		pw.flush();	
	}
}
