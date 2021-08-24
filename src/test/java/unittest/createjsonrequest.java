package unittest;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.JSONObject;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class createjsonrequest {

	@SuppressWarnings({ "null" })
	public Response acquisitionInfo() {
		RequestSpecification request=null;
		 Response response;
		JSONObject requestParams = new JSONObject();
		/*
		 * JSONObject acqtrigger=new JSONObject(); JSONObject browtypelk=new
		 * JSONObject(); JSONObject contentsizelk=new JSONObject(); JSONObject
		 * contenttypelk=new JSONObject(); JSONObject fileprilk=new JSONObject();
		 * JSONObject pubcatlk=new JSONObject(); JSONObject ftptypelk=new JSONObject();
		 * JSONObject recmethodlk=new JSONObject(); JSONObject scheinfolk=new
		 * JSONObject(); JSONObject translk=new JSONObject();
		 */
		try
		{
		requestParams.put("attachment", "Yes");
		requestParams.put("branchprocessingneeded", "Yes");
		requestParams.put("branchprocessingneeded", "Yes");
		requestParams.put("checkduplicates", "Yes");
		requestParams.put("contentMetadata", "Yes");
		requestParams.put("createdBy", "Satya");
		requestParams.put("dataRetention", "test");
		requestParams.put("daysofWeek", "test");
		requestParams.put("exceptions", "test");
		requestParams.put("dataRetention", "test");
		}
		catch (JSONException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		request.body(requestParams.toString());
	    response = request.post();
		return response;
	}

	public Response formatInfo(RequestSpecification request) {
		
		 Response response=null;
		// JSONObject requestParams = new JSONObject();
		JSONObject availableformatlk = new JSONObject();
		JSONObject obj2 = new JSONObject();
        try
        {
		//availableformatlk.put("availableLookupId", "1");
		availableformatlk.put("availableLookupValue", "PDF");
		
		obj2.put("availableFormatLookup", availableformatlk);

		obj2.put("publicatioid", "91604730805");
		
		JSONArray jsonArray = new JSONArray();

		jsonArray.put(obj2);
		
		System.out.println(jsonArray.toString());
		

		request.body(jsonArray.toString());
		response = request.post();
		System.out.println(response.body().prettyPrint());
		
		
		
        }
        catch (JSONException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
        return response;
	}

	public static void main(String[] args) {

		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config/config.properties")));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try
		{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("SessionToken", prop.getProperty("SessionToken"));
		headers.put("Roles", prop.getProperty("Roles"));
		headers.put("Content-Type", prop.getProperty("Content-Type"));
		//RequestSpecification requestSearchAPI = api.setHeaderDetails(prop.getProperty("BaseUrl"), headers);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
