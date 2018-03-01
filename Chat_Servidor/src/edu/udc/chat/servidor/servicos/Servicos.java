package edu.udc.chat.servidor.servicos;

import edu.udc.chat.servidor.entidade.Mensagem;
import edu.udc.chat.servidor.entidade.Sala;
import edu.udc.chat.servidor.entidade.Usuario;
import edu.udc.chat.servidor.persistencia.*;

public class Servicos implements InterfaceServicos{

	@Override
	public void criarUsuario(Usuario usuario, String senha) {
		if(usuario == null) return;
		if(usuario.email == "" || usuario.apelido == "" || senha == "") return;
		
		String isAdmin;
		if(usuario.isAdmin) isAdmin = "true";
		else { isAdmin = "false"; }
		
		String sql = "INSERT INTO `usuario`(`e-mail`, `apelido`, `senha`, `is_admin`) VALUES "
				+ "('" + usuario.email + "','" + usuario.apelido + "','" + senha + "'," + isAdmin + ")";
		
		String[] sqls = new String[1];
		sqls[0] = sql;
		
		SQLHandler handler = new SQLHandler();
		handler.executarSQLs(sqls);
	}

	@Override
	public void criarSala(Sala sala) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void criarMensagem(Mensagem mensagem) {
		// TODO Auto-generated method stub
		
	}

	/*
	public static void main(String args[]) {
		Usuario usuario = new Usuario("email@teste","usuario",false);
		Servicos s = new Servicos();
		s.criarUsuario(usuario, "1234");
		System.out.println("Usuario criado com sucesso!");
	}
	*/
}
