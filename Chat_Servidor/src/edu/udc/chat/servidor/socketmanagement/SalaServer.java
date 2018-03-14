package edu.udc.chat.servidor.socketmanagement;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import edu.udc.chat.servidor.entidade.Sala;
import edu.udc.chat.servidor.entidade.Usuario;

public class SalaServer {

	private List<UsuarioServer> usuarios;
	private Sala sala;
	
	public SalaServer(Sala sala) throws Exception {
		if(sala == null) throw new Exception("Sala nula");
		this.sala = sala;
		this.usuarios = new ArrayList<UsuarioServer>();
	}
	
	public void adicionarUsuario(UsuarioServer usuarioServer) {
		usuarioServer.setSalaServer(this);
		if(!isOnList(usuarioServer.getUsuario())) {
			this.usuarios.add(usuarioServer);
		}	
	}
	
	private boolean isOnList(Usuario usuario) {
		if(usuario == null) return false;
		for(UsuarioServer us : usuarios) {
			if(us.getUsuario().email.equals(usuario.email)) return true;
		}
		return false;
	}
	
	
	
}
