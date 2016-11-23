package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.EmployeeApp.util.ResourcePropertiesMap;
import com.google.gson.Gson;


@WebServlet("/Properties")
public class PropertiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log = Logger.getLogger(PropertiesServlet.class);
       
   
    public PropertiesServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		log.info(new Date()+":: in PropertiesServlet info :: " +PropertiesServlet.class);
		response.setContentType("application/json");
		PrintWriter pw= response.getWriter();
		HashMap<String,String> hm= new HashMap<String,String>();
	    hm= ResourcePropertiesMap.getMap();	
	    System.out.println(hm);
		Gson gson= new Gson();
		String prop= gson.toJson(hm);
		System.out.println(prop);
		pw.write(prop);
		pw.flush();
	}
	
	

}
