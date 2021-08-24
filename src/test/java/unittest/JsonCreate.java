package unittest;


import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonCreate {
	
	   @SuppressWarnings("unchecked")
	public static void main(String[] args) 
		
	 {
		JSONObject jo1 = new JSONObject();		    
		
		
		
		JSONObject jo2 = new JSONObject();	
		
		jo2.put("transmissionTypeLookupId", "1");
		jo2.put("transmissionTypeLookupValue", "string");
		jo1.put("transmissionType", jo2);
		
		
		JSONObject jo3 = new JSONObject();
		jo3.put("receiptMethodLookupId", "1");
		jo3.put("receiptMethodLookupValue", "string");
		jo1.put("receiptMethod", jo3);
			   	   	   
		JSONObject jo4 = new JSONObject();
		jo4.put("categoryLookupId", "1");
		jo4.put("categoryLookupValue", "string");
		jo1.put("publisherCategory", jo4);	 
		
		
		
		JSONObject jo5 = new JSONObject();
		jo5.put("ftpTypeLookupId", "1");
		jo5.put("ftpTypeLookupValue", "string");
		jo1.put("ftpType", jo5);
		
		
		JSONObject jo6 = new JSONObject();
		jo6.put("filePriorityLookupId", "1");
		jo6.put("filePriorityLookupValue", "string");
		jo1.put("filePriority", jo6);
		
		
		JSONObject jo7 = new JSONObject();
		jo7.put("contentSizeLookupId", "1");
		jo7.put("contentSizeLookupValue", "string");
		jo1.put("contentSize", jo7);
		
		
		JSONObject jo8 = new JSONObject();
		jo8.put("browserLookupId", "1");
		jo8.put("browserLookupValue", "string");
		jo1.put("browserType", jo8);
		
		
		JSONObject jo9 = new JSONObject();
		jo9.put("triggerLookupId", "1");
		jo9.put("triggerLookupValue", "string");
		jo1.put("acquisitionTrigger", jo9);
		
		
		JSONArray ja = new JSONArray();
		ja.put("string");
		jo1.put("attachments", ja);
		
		jo1.put("batchProcessingNeeded", "true");
		jo1.put("checkDuplicates", "true");
		jo1.put("contentMetadata", "true");
		jo1.put("cronExpression", "string");
		jo1.put("dataRetention", "string");
		jo1.put("exceptions", "string");
		jo1.put("paidContent", "true");
		jo1.put("password", "string");
		jo1.put("publisherCode", "strin");
		jo1.put("publisherSchemaVersion", "string");
		jo1.put("receiptUrl", "string");
		jo1.put("resolutionTime", "2019-05-16T04:52:09.505Z");
		jo1.put("restrictTimezone", "true");
		jo1.put("sensitiveData", "true");
		jo1.put("sleepTime", "string");
		jo1.put("trustCertificate", "true");
		jo1.put("userId", "string");
		
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();
	       JsonParser jp = new JsonParser();
	       JsonElement je = jp.parse(jo1.toString());
	       String prettyJsonString = gson.toJson(je);	   
		
		System.out.println(prettyJsonString);
		
			 
			    }
			   
		}


	        	     
	    



