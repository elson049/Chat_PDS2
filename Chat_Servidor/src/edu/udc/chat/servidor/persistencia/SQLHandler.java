package edu.udc.chat.servidor.persistencia;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLHandler {
	private Connection con;
	
	public SQLHandler(){
		con = Conexao.getConexao();
	}
	
	public ResultSet executarConsulta(String sql) {
		ResultSet rs = null;
		try {
			Statement s = con.createStatement();
			s.executeQuery(sql);
			con.commit();
			rs = s.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void executarSQLs(String sqls[]) {
		try {
			Statement s = con.createStatement();
			for(String sql : sqls) {
				s.addBatch(sql);
			}
			s.executeBatch();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
