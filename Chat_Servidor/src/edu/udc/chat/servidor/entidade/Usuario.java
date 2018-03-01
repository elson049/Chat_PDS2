package edu.udc.chat.servidor.entidade;

public class Usuario {

	public String email;
	public String apelido;
	public boolean isAdmin;
	
	public Usuario(String email, String apelido, boolean isAdmin){
		this.email = email;
		this.apelido = apelido;
		this.isAdmin = isAdmin;
	}
	
}
