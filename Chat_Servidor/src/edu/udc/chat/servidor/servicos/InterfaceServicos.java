package edu.udc.chat.servidor.servicos;
import edu.udc.chat.servidor.entidade.*;

public interface InterfaceServicos {
	public void criarUsuario(Usuario usuario, String senha);
	public void criarSala(Sala sala);
	public void criarMensagem(Mensagem mensagem);
	//TODO servicos relacionados ao envio e recebimento das mensagens
}
