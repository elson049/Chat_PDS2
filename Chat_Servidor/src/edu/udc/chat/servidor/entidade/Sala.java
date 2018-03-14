package edu.udc.chat.servidor.entidade;

public class Sala {
	public String nome;
	public int maxUsuarios;
	
	public Sala(String nome, int maxUsuarios){
		this.nome = nome;
		this.maxUsuarios = maxUsuarios;
	}
}
