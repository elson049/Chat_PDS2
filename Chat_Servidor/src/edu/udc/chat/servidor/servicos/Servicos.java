package edu.udc.chat.servidor.servicos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.udc.chat.servidor.entidade.Mensagem;
import edu.udc.chat.servidor.entidade.Sala;
import edu.udc.chat.servidor.entidade.Usuario;
import edu.udc.chat.servidor.persistencia.*;

public class Servicos implements InterfaceServicos{

	@Override
	public void criarUsuario(Usuario usuario, String senha) {
		//Bloco de Verificação
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
		//Bloco de verificação
		if(sala == null) return;
		if(sala.maxUsuarios <= 0 || sala.nome == "") return;
		
		
		SQLHandler handler = new SQLHandler();
		String sql = "SELECT sala.is_ativo FROM sala WHERE sala.nome = \"" + sala.nome + "\"";
		ResultSet rs = handler.executarConsulta(sql);
		try {
			if(rs.next()) {
				sql = "UPDATE sala SET max_usuarios = " + sala.maxUsuarios + " WHERE nome = \"" + sala.nome + "\"";
				if(!rs.getBoolean(1)) {
					sql = "UPDATE sala SET is_ativo = true, max_usuarios = " + sala.maxUsuarios + " WHERE nome = \"" + sala.nome + "\"";
				}
				rs.close();
				String[] sqls = new String[1];
				sqls[0] = sql;
				handler.executarSQLs(sqls);
			}
			else {
				sql = "INSERT INTO sala (nome, max_usuarios, is_ativo) VALUES ("
						+ "\"" + sala.nome + "\","
						+ sala.maxUsuarios + ","
						+ "true)";
				rs.close();
				String[] sqls = new String[1];
				sqls[0] = sql;
				handler.executarSQLs(sqls);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void criarMensagem(Mensagem mensagem) {
		//Bloco de verificação
		if(mensagem == null) return;
		if(mensagem.dataMensagem == null || mensagem.mensagem == "" || mensagem.sala == null || mensagem.usuario == null) return;
		
		//Falta Data
		//INSERT INTO mensagem (id_sala, email, mensagem) VALUES ((SELECT id_sala FROM sala WHERE nome = 'Lobby'), (SELECT email FROM usuario WHERE apelido = 'usuario'), 'mensagem')
		
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String data = ft.format(mensagem.dataMensagem);
		
		String sql = "INSERT INTO mensagem (id_sala, email, mensagem, data_hora) VALUES "
				+ "((SELECT id_sala FROM sala WHERE nome = \"" + mensagem.sala.nome + "\"), "
				+ "\"" + mensagem.usuario.email + "\","
				+ "\"" + mensagem.mensagem + "\","
				+ "\"" + data +"\")";
		System.out.println(sql);
		String sqls[] = new String[1];
		sqls[0] = sql;
		
		SQLHandler handler = new SQLHandler();
		handler.executarSQLs(sqls);
		
	}

	
	
	@Override
	public ArrayList<Sala> getSalasAtivas() {
		String sql = "SELECT * FROM sala WHERE is_ativo = true";
		SQLHandler handler = new SQLHandler();
		ResultSet rs = handler.executarConsulta(sql);
		ArrayList<Sala> salas = new ArrayList<>();
		try {
			while(rs.next()) {
				Sala sala = new Sala(rs.getString("nome"),rs.getInt("max_usuarios"));
				salas.add(sala);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salas;
	}
	
	public static void main(String args[]) {
		Sala sala = new Sala("Lobby",9);
		Usuario usuario = new Usuario("email@teste", "usuario", true);
		Mensagem mensagem = new Mensagem(usuario, sala, "Mensagem", null);
		Servicos servicos =  new Servicos();
		servicos.criarMensagem(mensagem);
	}

	@Override
	public Usuario login(String email, String senha) {
		String sql = "SELECT * FROM usuario WHERE email = \"" + email + "\" AND senha = \"" + senha + "\"";
		SQLHandler handler = new SQLHandler();
		ResultSet rs = handler.executarConsulta(sql);
		try {
			if(rs.next()) {
				Usuario usuario =  new Usuario(rs.getString("email"), rs.getString("apelido"), rs.getBoolean("is_admin"));
				return usuario;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
