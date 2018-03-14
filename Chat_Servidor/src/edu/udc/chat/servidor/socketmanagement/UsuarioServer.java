package edu.udc.chat.servidor.socketmanagement;

import java.net.Socket;

import edu.udc.chat.servidor.entidade.Usuario;

public class UsuarioServer {

	private Usuario usuario;
	private Socket socket;
	private SalaServer salaServer;
	
	public UsuarioServer(Usuario usuario, Socket socket, SalaServer salaServer) throws Exception {
		if(usuario == null) throw new Exception("Usuario nulo");
		if(socket == null) throw new Exception("Socket nulo");
		this.usuario = usuario;
		this.socket = socket;
		this.salaServer = salaServer;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setSalaServer(SalaServer salaServer) {
		this.salaServer = salaServer;
	}
	
	public void enviarMensagem(String mensagem) {
		//TODO código para enviar mensagem
	}
	
	
}
