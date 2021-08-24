package unittest;


import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JasonCreationFormatInfo {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		String availableLookupId="1,2,3,4";
		String processedLookupId="1,2";
		String contentTypeLookupId="1,2";
		
		 String[] formats = null;
		 int k = 0;
		 String prettyJsonString=null;
		  JSONObject jo1 = new JSONObject();
		 
		  JSONArray ja1 = new JSONArray();
		  JSONArray ja2= new JSONArray();
		  JSONArray ja3 = new JSONArray();
		  
		  if (processedLookupId.contains(",")||processedLookupId.length()==1)		{ 
				formats = processedLookupId.split(",");					
		  for (k = 0; k < formats.length; k++) {	
		  
		  JSONObject jo4 = new JSONObject();
		  jo4.put("checked", "true");
		  jo4.put("processedLookupId", formats[k]);
		  jo4.put("processedLookupValue", "string");
		  ja3.put(jo4);
		  jo1.put("processedFormats", ja3);
		  }}
		  
		  if (contentTypeLookupId.contains(",")||contentTypeLookupId.length()==1)		{ 
				formats = contentTypeLookupId.split(",");					
		  for (k = 0; k < formats.length; k++) {			 
		  
		  JSONObject jo3 = new JSONObject();
		  jo3.put("checked", "true");
		  jo3.put("contentTypeLookupId", formats[k]);
		  jo3.put("contentTypeLookupValue", "string");
		  ja2.put(jo3);
		  jo1.put("contentTypes", ja2);
		  
		  }}
		 
				  
		  if (availableLookupId.contains(",")||availableLookupId.length()==1)		{ 
				formats = availableLookupId.split(",");					
		  for (k = 0; k < formats.length; k++) {			 
		  JSONObject jo2 = new JSONObject();
		  jo2.put("checked", "true");
		  jo2.put("availableLookupId", formats[k]);			
		  jo2.put("availableLookupValue", "string");		 
		  ja1.put(jo2);	
		  
		  jo1.put("availableFormats", ja1);
		  
		   Gson gson = new GsonBuilder().setPrettyPrinting().create();
	       JsonParser jp = new JsonParser();
	       JsonElement je = jp.parse(jo1.toString());
	       prettyJsonString = gson.toJson(je);	   
	       
		  }
		  }
		 
		  System.out.println(prettyJsonString);
			
	}
		  
		  
		   	
		  
		  	
	

}
