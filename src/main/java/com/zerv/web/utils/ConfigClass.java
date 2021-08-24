package com.zerv.web.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigClass {
	
	public static String filepath = "./src/main/resources/config/config.properties";
	public static ConfigClass config;
	public static Properties prop;

	public ConfigClass() throws IOException
	{
		FileInputStream fis = new FileInputStream(filepath);
		prop = new Properties();
		prop.load(fis);
	}

	public static ConfigClass getConfigFile()
	{
		try {
			config = new ConfigClass();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}

	public String getUrl()
	{
		return prop.getProperty("BaseUrl");
	}

	public String getBrowser()
	{
		return prop.getProperty("Browsername");
	}

}
