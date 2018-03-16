package edu.udc.chat.servidor.socketmanagement;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import edu.udc.chat.servidor.entidade.Sala;
import edu.udc.chat.servidor.servicos.Servicos;

public class Servidor extends Thread{

	
	private ServerSocket ssListener;
	private static List<SalaServer> salas;
	private int porta;
	private List<UsuarioServer> usuariosSemSala;
	
	
	public Servidor(int porta) throws Exception {
		this.porta = porta;
		this.ssListener = new ServerSocket(this.porta);
		Servicos servicos = new Servicos();
		salas = new ArrayList<SalaServer>();
		ArrayList<Sala> s = servicos.getSalasAtivas();
		for(Sala aux : s) {
			SalaServer ss = new SalaServer(aux,this);
			salas.add(ss);
		}
		usuariosSemSala = new ArrayList<UsuarioServer>();
		
		this.start();
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Socket s = ssListener.accept();
				UsuarioServer us = new UsuarioServer(s);
				usuariosSemSala.add(us);
			}	
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public void removeUsuarioSemSala(UsuarioServer us) {
		for(UsuarioServer aux : usuariosSemSala) {
			if(aux.getSocket().getInetAddress().toString().equals(us.getSocket().getInetAddress().toString())) {
				usuariosSemSala.remove(aux);
			}
		}
	}
	
	public static SalaServer getSalaServerByNome(String nomeSala) {
		for(SalaServer sala : salas) {
			if(sala.getNomeSala().equals(nomeSala)) {
				return sala;
			}
		}
		
		return null;
	}
	
	
	
}
