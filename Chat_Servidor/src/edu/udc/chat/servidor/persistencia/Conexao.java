package edu.udc.chat.servidor.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection con = null;
	
	public static String dbName = "chat_db";
	public static String dbUser = "root";
	public static String dbPassword = "";
	public static String dbUrl = "jdbc:mysql://localhost/" + dbName;
	
	{
		try {
				Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConexao() {
		try {
			if(con == null || con.isClosed()) {
				con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			}
			con.setAutoCommit(false);
		}
		catch(SQLException e) {
			e.printStackTrace();
			con = null;
		}
		return con;
	}
	
}
