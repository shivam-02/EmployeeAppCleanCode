package com.EmployeeApp.daoInterface;

import java.util.ArrayList;

public interface EmployeeDepartmentDetailDAOInterface {
	
	public void deleteEmployeeDepartmentDetail(int id);
	public void updateEmployeeDepartmentDetail(ArrayList<Integer>departmentId,int id);
	public void addEmployeeDepartmentDetail(ArrayList<Integer> list, int id);

}
