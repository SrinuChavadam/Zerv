package unittest;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ReadJson {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		String filelocation="./InputFiles/TC01.json";
        String text = new String(Files.readAllBytes(Paths.get(filelocation)), StandardCharsets.UTF_8);
        JSONObject j = new JSONObject(text);
        JSONObject updateJson = updateJson(j,"publicationId","8565254523");
        System.out.println(updateJson);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(updateJson.toString());
        String prettyJsonString = gson.toJson(je);
        FileUtils.writeStringToFile(new File("./InputFiles/TC01.json"),prettyJsonString);
	}
	
	public static JSONObject updateJson(JSONObject obj, String keyString, String newValue) throws Exception {
      
        @SuppressWarnings("rawtypes")
		Iterator iterator = obj.keys();
        String key = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();            
            if ((obj.optJSONArray(key) == null) && (obj.optJSONObject(key) == null)) {
                if ((key.equals(keyString))) {
                    // put new value
                    obj.put(key, newValue);
                    return obj;
                }
            }
           
            if (obj.optJSONObject(key) != null) {
                updateJson(obj.getJSONObject(key), keyString, newValue);
            }
            if (obj.optJSONArray(key) != null) {
                JSONArray jArray = obj.getJSONArray(key);
                for (int i = 0; i < jArray.length(); i++) {
                    updateJson(jArray.getJSONObject(i), keyString, newValue);
                }
            }
        }
        return obj;
    }

}
