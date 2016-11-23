package core.java;

import java.util.ArrayList;
import java.util.Arrays;

public class CoreJava {

	public static void main(String[] args)
	{
		String str="string , with ,comma";
		ArrayList aList= new ArrayList(Arrays.asList(str.split(",")));
		for(int i=0;i<aList.size();i++)
		{
		    System.out.println(" -->"+aList.get(i));
		}
          
	}
}
