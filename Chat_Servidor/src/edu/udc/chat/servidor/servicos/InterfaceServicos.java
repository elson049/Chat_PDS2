package edu.udc.chat.servidor.servicos;
import java.util.ArrayList;

import edu.udc.chat.servidor.entidade.*;

public interface InterfaceServicos {
	public void criarUsuario(Usuario usuario, String senha);
	public void criarSala(Sala sala);
	public void criarMensagem(Mensagem mensagem);
	public ArrayList<Sala> getSalasAtivas();
	public Usuario login(String email, String senha);
	//TODO servicos relacionados ao envio e recebimento das mensagens
}
