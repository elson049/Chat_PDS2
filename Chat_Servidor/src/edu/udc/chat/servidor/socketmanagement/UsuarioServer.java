package edu.udc.chat.servidor.socketmanagement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import edu.udc.chat.servidor.parser.*;

import edu.udc.chat.servidor.entidade.Usuario;

public class UsuarioServer extends Thread {

	private Usuario usuario;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private SalaServer salaServer;
	
	public UsuarioServer(Usuario usuario, Socket socket, SalaServer salaServer) throws Exception {
		if(usuario == null) throw new Exception("Usuario nulo");
		if(socket == null) throw new Exception("Socket nulo");
		this.usuario = usuario;
		this.socket = socket;
		this.salaServer = salaServer;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		this.start();
	}
	
	public UsuarioServer(Socket socket) throws Exception {
		if(socket == null) throw new Exception("Socket nulo");
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		this.start();
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setSalaServer(SalaServer salaServer) {
		this.salaServer = salaServer;
	}
	

	public void enviarMensagem(String mensagem) {
		try {
			out.writeUTF(mensagem);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	public void fecharUsuarioServer() {
		salaServer.removerUsuario(this);
		this.interrupt();
		try {
			this.in.close();
			this.out.close();
			this.socket.close();
			this.usuario = null;
			this.salaServer = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		while(!this.isInterrupted()) {
			try {
				String mensagem = in.readUTF();
				Parser.parseJSON(mensagem, this);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void broadcastMensagem(String mensagem) {
		if(salaServer != null) {
			salaServer.broadcastMensagem(mensagem);
		}
	}
	
	
}
