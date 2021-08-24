package unittest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class contenthub {
    public static void main(String... args) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(new TypeToken<Map <String, Object>>(){}.getType(),  new MapDeserializerDoubleAsIntFix());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        try (FileReader fileReader = new FileReader("C:\\Users\\42131\\Downloads\\publications.all.1.2019-10-22-0645.2019-10-22-0700.inc")) {
            @SuppressWarnings("rawtypes")
			Map jsonMap = gson.fromJson(fileReader, new TypeToken<Map<String, Object>>(){}.getType());
            @SuppressWarnings("unchecked")
			List<Map> publisherTypes =(List<Map>) jsonMap.get("dataItemXactions");
            //System.out.println(publisherTypes);
            
            for(Map x:publisherTypes)
            {
            	System.out.println(x.get("action"));
            	Map aa=(Map) x.get("dataItem");
            	//System.out.println(aa);
            	List<Map> cc =(List<Map>)aa.get("factValues");
            	System.out.println(cc);
            		           
            	
            }
            /*List relationships = gson.fromJson(getJsonObject().toString(), List.class);
            publisherTypes.forEach(publisherType -> {
                try {
                    publisherType.put("publisherRelationships", relationships);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });*/
            //System.out.println(gson.toJson(publisher));
            //FileUtils.writeStringToFile(new File("C:\\Users\\42131\\Desktop\\test.json"),prettyJsonString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*JsonObject jsonObject = new JsonObject();
        jsonMap.put("some key",jsonObject);
        String prettyJsonString = gson.toJson(je);
       // FileUtils.writeStringToFile(new File("C:\\Users\\42131\\Desktop\\test.json"),prettyJsonString);*/
    }

  /*  private static JSONArray getJsonObject() throws Exception{
        JSONObject jO = new JSONObject();
        JSONObject jO3 = new JSONObject();
        jO.put("publisherRelationshipTypeId", "1");
        JSONObject jO1 = new JSONObject();
        jO1.put("publisherRelationshipType",jO);
        jO1.put("validFrom", "2017-06-01T23:28:56.782Z");
        JSONArray aa=new JSONArray();
        aa.put(jO1);
        JSONObject jO2 = new JSONObject();
        jO2.put("parentPublisherTypeId", "22370");
        jO2.put("childPublishers",aa);
        JSONArray aa1=new JSONArray();
        aa1.put(jO2);
        jO3.put("publisherRelationships", aa1);
        System.out.println(jO3);
        return aa1;
    }*/

    public static class MapDeserializerDoubleAsIntFix implements JsonDeserializer<Map<String, Object>> {

        @Override  @SuppressWarnings("unchecked")
        public Map<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return (Map<String, Object>) read(json);
        }

        public Object read(JsonElement in) {

            if(in.isJsonArray()){
                List<Object> list = new ArrayList<Object>();
                JsonArray arr = in.getAsJsonArray();
                for (JsonElement anArr : arr) {
                    list.add(read(anArr));
                }
                return list;
            }else if(in.isJsonObject()){
                Map<String, Object> map = new LinkedTreeMap<String, Object>();
                JsonObject obj = in.getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entitySet = obj.entrySet();
                for(Map.Entry<String, JsonElement> entry: entitySet){
                    map.put(entry.getKey(), read(entry.getValue()));
                }
                return map;
            }else if( in.isJsonPrimitive()){
                JsonPrimitive prim = in.getAsJsonPrimitive();
                if(prim.isBoolean()){
                    return prim.getAsBoolean();
                }else if(prim.isString()){
                    return prim.getAsString();
                }else if(prim.isNumber()){
                    Number num = prim.getAsNumber();
                    // here you can handle double int/long values
                    // and return any type you want
                    // this solution will transform 3.0 float to long values
                    if(Math.ceil(num.doubleValue())  == num.longValue())
                        return num.longValue();
                    else{
                        return num.doubleValue();
                    }
                }
            }
            return null;
        }
    }

}