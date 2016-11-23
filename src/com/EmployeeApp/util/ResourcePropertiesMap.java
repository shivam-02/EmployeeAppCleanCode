package com.EmployeeApp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.EmployeeApp.servlet.LoginServlet;

public class ResourcePropertiesMap
{
	static Logger log = Logger.getLogger(ResourcePropertiesMap.class.getName());
	
	public static HashMap<String, String> getMap()
	{
		log.info(new Date()+" in Resource Properties Map getMap method "+ ResourcePropertiesMap.class);
		Properties prop = new Properties();
		InputStream input = null;
        HashMap<String, String> propertiesMap= new HashMap<String,String>();
		try {
			input = new FileInputStream("C://EmployeeApp2//src//resources.properties");
			// load a properties file
			prop.load(input);
			propertiesMap.put("saveEmployeeMessage", prop.getProperty("saveEmployeeMessage"));
			log.info("Properties Hash Map " +propertiesMap);
			} 
			catch (IOException ex)
			{
			log.error("unable to get properties map "+ex.getMessage());
			ex.printStackTrace();
			}
			finally
			{
			if (input != null)
			  {
				try {
					input.close();
					}catch (IOException e)
					{
					log.error("unable to close resources "+e.getMessage());
					e.printStackTrace();
					}
			  }
			}
		return propertiesMap;
	}
}