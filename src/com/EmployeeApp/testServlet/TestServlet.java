package com.EmployeeApp.testServlet;

import java.io.File;
import java.io.PrintWriter;

import org.junit.Test;

import com.EmployeeApp.model.EmployeeJson;
import com.EmployeeApp.servlet.AddEmployee;
import com.EmployeeApp.servlet.DeleteEmployee;
import com.google.gson.Gson;

import javax.servlet.http.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestServlet {
	
	@Test
	public void testAddServlet(){
		
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpServletResponse response=mock(HttpServletResponse.class);
		when(request.getParameter("name")).thenReturn("john");
		when(request.getParameter("city")).thenReturn("pune");
		when(request.getParameter("type")).thenReturn("1");
		
		
		new AddEmployee().doPost(request,response);
		
		verify(request,atLeast(1)).getParameter("name");
		assertEquals("City does not match",request.getParameter("city"),"pune");
		assertTrue("Invalid type",(request.getParameter("type"))!="-1");
		
	}
	
		@Test
        public void testDeleteServlet(){
		
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpServletResponse response=mock(HttpServletResponse.class);
		when(request.getParameter("empIds")).thenReturn("2,14,5");
		
		
		
		new DeleteEmployee().doPost(request,response);
		
		verify(request,atMost(1)).getParameter("empIds");
		assertEquals("Emp ids value should be comma seperated values",request.getParameter("empIds"),"2,14,5");
		
		
	}
		
		@Test
		public void testUpdateServlet()
		{
			HttpServletRequest request=mock(HttpServletRequest.class);
			HttpServletResponse response=mock(HttpServletResponse.class);
			String jsonString="{\"name\":\"john\",\"city\":\"indore\"}";
			when(request.getHeader("employeeDataJsonObject")).thenReturn(jsonString);
			assertEquals("employeeDataJsonObject should be a valid json",request.getHeader("employeeDataJsonObject"),"{\"name\":\"john\",\"city\":\"indore\"}");
		}
		
		
		@Test
		public void testLoginServlet()
		{
			HttpServletRequest request=mock(HttpServletRequest.class);
			HttpServletResponse response=mock(HttpServletResponse.class);
			when(request.getParameter("username")).thenReturn("admin");
			when(request.getParameter("password")).thenReturn("admin");
			when(request.getParameter("authenticationType")).thenReturn("2");
			assertEquals("Invalid username",request.getParameter("username"),"admin");
			assertEquals("Invalid password",request.getParameter("password"),"admin");
			assertFalse("Invalid authentication type",(Integer.parseInt(request.getParameter("authenticationType"))==0));
			verify(request,atMost(3)).getParameter("password");
		}

}
