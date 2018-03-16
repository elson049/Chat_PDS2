package edu.udc.chat.servidor.socketmanagement;

import java.util.ArrayList;
import java.util.List;

import edu.udc.chat.servidor.entidade.Sala;
import edu.udc.chat.servidor.entidade.Usuario;

public class SalaServer{

	private List<UsuarioServer> usuarios;
	private Sala sala;
	private int quantidadeUsuarios;
	private Servidor servidor;
	
	
	public SalaServer(Sala sala, Servidor servidor) throws Exception {
		if(sala == null) throw new Exception("Sala nula");
		this.sala = sala;
		this.usuarios = new ArrayList<UsuarioServer>();
		this.quantidadeUsuarios = 0;
		this.servidor = servidor;
	}
	
	public void adicionarUsuario(UsuarioServer usuarioServer) throws Exception{
		if(sala.maxUsuarios <= quantidadeUsuarios) throw new Exception("Atingiu limite máximo de usuarios");
		servidor.removeUsuarioSemSala(usuarioServer);
		usuarioServer.setSalaServer(this);
		if(!isOnList(usuarioServer.getUsuario())) {
			this.usuarios.add(usuarioServer);
		}	
		quantidadeUsuarios++;
	}
	
	private boolean isOnList(Usuario usuario) {
		if(usuario == null) return false;
		for(UsuarioServer us : usuarios) {
			if(us.getUsuario().email.equals(usuario.email)) return true;
		}
		return false;
	}
	
	public void removerUsuario(UsuarioServer usuarioServer) {
		if(isOnList(usuarioServer.getUsuario())) {
			this.usuarios.remove(usuarioServer);
		}	
	}
	
	public void fecharSala() {
		for(UsuarioServer us : usuarios) {
			us.fecharUsuarioServer();
		}
		this.sala = null;
		this.usuarios.clear();
		this.usuarios = null;	
	}
	
	public void broadcastMensagem(String mensagem) {
		for(UsuarioServer us : usuarios) {
			us.enviarMensagem(mensagem);
		}
	}
	
	public String getNomeSala() {
		return this.sala.nome;
	}
	
}
