package com.EmployeeApp.daoInterface;

import java.util.List;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.model.EmployeeJson;
import com.EmployeeApp.model.EmployeeType;
import com.EmployeeApp.model.EmployeeVO;

public interface EmployeeDAOInterface {
	
	
	public int deleteEmployeeById(int id);
	public int addEmployee(Employee employee);
	public int getCurrentEmployeeId(String name);
	public List<EmployeeVO> getEmployeeByType(int employeeTypeId,int start,int limit);
	public int getEmployeeCount(int employeeTypeId);
	
	
	public int updateEmployee(EmployeeJson employeeJson);

}
