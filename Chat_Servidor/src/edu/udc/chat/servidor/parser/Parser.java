package edu.udc.chat.servidor.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.udc.chat.servidor.socketmanagement.UsuarioServer;

public class Parser {
	
	public static void parseJSON(String json,UsuarioServer us) {	
		JsonParser jsonParser = new JsonParser();
		JsonObject rootObj = jsonParser.parse(json).getAsJsonObject();
		switch(rootObj.get("Tipo").getAsString()) {
		case "Mensagem":
			break;
		case "Login":
			break;
		case "Logout":
			break;
		case "MudancaSala":
			break;
		default:
			break;
		}
	}
	
	
}
