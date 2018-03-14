package edu.udc.chat.servidor.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parser {
	
	public static JsonObject parseJSON(String json) {	
		JsonParser jsonParser = new JsonParser();
		JsonObject rootObj = jsonParser.parse(json).getAsJsonObject();
		return rootObj;
	}
	
	
}
