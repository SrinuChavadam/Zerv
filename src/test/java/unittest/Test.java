package unittest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zerv.database.utils.DatabaseUtils;

public class Test {


	public static void main(String[] args) throws Exception {
		
		DatabaseUtils db=new DatabaseUtils();
		
			 
			 String query="select password_provided,processed_as_print,file_access from format_evaluation where publication_id='91604786813'";
				List<String> TimeLinessDbValue = db.getRowList(query);
				System.out.println(TimeLinessDbValue);
					
				
		

}
}