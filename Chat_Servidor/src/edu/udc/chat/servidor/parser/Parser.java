package edu.udc.chat.servidor.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.udc.chat.servidor.entidade.Usuario;
import edu.udc.chat.servidor.servicos.Servicos;
import edu.udc.chat.servidor.socketmanagement.SalaServer;
import edu.udc.chat.servidor.socketmanagement.Servidor;
import edu.udc.chat.servidor.socketmanagement.UsuarioServer;

public class Parser {
	
	public static void parseJSON(String json,UsuarioServer us) {	
		JsonParser jsonParser = new JsonParser();
		JsonObject rootObj = jsonParser.parse(json).getAsJsonObject();
		switch(rootObj.get("Tipo").getAsString()) {
		case "Mensagem":
			Parser.dealWithMensagem(rootObj.get("Mensagem").getAsString(), us);
			break;
		case "Login":
			Parser.login(rootObj.get("Email").getAsString(), rootObj.get("Senha").getAsString(), us);
			break;
		case "Logout":
			Parser.logout(us);
			break;
		case "MudancaSala":
			break;
		default:
			break;
		}
	}
	
	protected static void dealWithMensagem(String mensagem, UsuarioServer us) {
		us.broadcastMensagem(mensagem);
	}
	
	protected static void login(String email, String senha, UsuarioServer us) {
		Servicos s = new Servicos();
		Usuario user = s.login(email, senha);
		if(user != null) {
			us.setUsuario(user);
			//TODO enviar quais salas disponíveis;
		}
		else {
			//TODO criar mensagem de erro para enviar para o usuario
		}
	}
	
	protected static void logout(UsuarioServer us) {
		//TODO enviar mensagem a todos avisando que a sala tem um a menos
		us.fecharUsuarioServer();
	}
	
	//Função usada também para primeira entrada em sala
	protected static void dealWithMudancaSala(String nomeSala, UsuarioServer us) {
		SalaServer sala = Servidor.getSalaServerByNome(nomeSala);
		if(sala != null) {
			us.setSalaServer(sala);
			//TODO avisar a todos, incluse o que chegou, sobre as novas informações da sala.
		}
	}
	
}
