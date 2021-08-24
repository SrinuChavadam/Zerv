package unittest;


import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonCreateTransformInstructions {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
			 
		JSONArray ja = new JSONArray();
        String formats[]=null;
        String formatss[]=null;	
        String formatsss[]=null;	
		String fileNames=".xml,readfpdf,mediafile,source.pdf,.mov";
		String transforms="transform,transform,transform,transform,transform";
		String deliveredFolder="1,2,3,4";
		JSONArray ja1 = new JSONArray();
		
		if (fileNames.contains(",")||fileNames.length()==1||transforms.contains(",")||transforms.length()==1)	{ 
				formats = fileNames.split(",");	
				formatss = transforms.split(",");	
		  for (int k = 0; k < formats.length; k++) {		
		
			  JSONObject jo1 = new JSONObject();
		      jo1.put("fileName", formats[k]);
		      jo1.put("transform", formatss[k]);		
	          ja1.put(jo1);				   	   
		    }
		  }   
				 		 
		 if (deliveredFolder.contains(",")||deliveredFolder.length()==1)	{ 
				formatsss = deliveredFolder.split(",");					
		    for (int i = 0; i < formatsss.length; i++) {
		
			JSONObject jo = new JSONObject();	
			JSONObject jo2 = new JSONObject();
		    jo2.put("deliveredFolderLookupId", formatsss[i]);
		    jo.put("deliveredFolderLookup", jo2);	
		    jo.put("fileDetailsDTOS", ja1);
		    ja.put(jo);
		      }
		   }	  
	   		  	    
	    JSONObject root_jo = new JSONObject();
	    root_jo.put("transformInstructionsDTOS", ja);
	    root_jo.put("fileTypes", "string");
	    root_jo.put("filesIncluded", "true");
	    
	       Gson gson = new GsonBuilder().setPrettyPrinting().create();
	       JsonParser jp = new JsonParser();
	       JsonElement je = jp.parse(root_jo.toString());
	       String prettyJsonString = gson.toJson(je);	   
		
		System.out.println(prettyJsonString);
	 

		
	}

}
