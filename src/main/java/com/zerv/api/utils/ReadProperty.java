package com.zerv.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperty {

	public String getProperty(String Key) {
		Properties prop = new Properties();
		InputStream input = null;
		String value = "";
		try {

			prop.load(new FileInputStream(new File("./src/main/resources/config/config.properties")));

			value = prop.getProperty(Key);
			// get the property value and print it out

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return value;

	}

}
