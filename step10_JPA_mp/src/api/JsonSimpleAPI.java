package api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

public class JsonSimpleAPI {

	public static JSONArray toJSONArray(String content) {
		JSONArray j1 = null;
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(content);
			JSONObject jsonObject = (JSONObject) obj;
			j1 = (JSONArray)(jsonObject.get("features"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return j1;
	}
	
	public static JSONObject jsonParser(String content) {

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {

			Object obj = parser.parse(content);

			jsonObject = (JSONObject) obj;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}