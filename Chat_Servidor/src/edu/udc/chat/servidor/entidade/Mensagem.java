package edu.udc.chat.servidor.entidade;

import java.util.Date;

public class Mensagem {
	public Usuario usuario = null;
	public Sala sala = null;
	public String mensagem;
	public Date dataMensagem = null;
	
	public Mensagem(Usuario usuario, Sala sala, String mensagem, Date dataMensagem){
		this.usuario = usuario;
		this.sala = sala;
		this.mensagem = mensagem;
		this.dataMensagem = dataMensagem;
	}
}
