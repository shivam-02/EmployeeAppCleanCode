package com.EmployeeApp.daoInterface;

public interface EmployeeTypeDetailDAOInterface {
	
	public void addType(int id,int type);
	public int  updateEmployeeType(int type,int id);
	public int deleteEmployeeTypeDetailById(int id);

}
