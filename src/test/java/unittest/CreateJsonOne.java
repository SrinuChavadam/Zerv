package unittest;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CreateJsonOne {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		   JSONArray ja = new JSONArray();
		   JSONObject jo1 = new JSONObject();
		   JSONObject jo2 = new JSONObject();		   	   	   
		   jo1.put("processedFormatId", "273");		  
		   jo1.put("processedFormatLookup", jo2);
		   jo1.put("publicationId","91604712883999");
		   jo2.put("processedLookupId", "4");	
		   ja.put(jo1);			
		
		   Gson gson = new GsonBuilder().setPrettyPrinting().create();
	       JsonParser jp = new JsonParser();
	       JsonElement je = jp.parse(ja.toString());
	       String prettyJsonString = gson.toJson(je);	   
	       System.out.println(prettyJsonString);		
		
	}

}
