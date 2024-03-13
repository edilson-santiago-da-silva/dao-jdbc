package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// criação de métodos estáticos para conectar e desconectar o banco de dados
public class DB {

	private static Connection conn = null;
	
	public static Connection getConnection() { //conexão com o banco de dados
		if(conn == null) {
			try {
				Properties props = loadProperties(); //pega-se as propriedades do banco de dados usando o metodo abaixo loadProprties() 
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}	
		}
		return conn;
	}
	
	public static void closedConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}
	}
	
	private static Properties loadProperties() { // metodo para carregar e guardar as informacoes de conexao do banco, dentro do arquivo db.properties
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props =	new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) { // tratando uma possivel exceçao com a classe DbException
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) { //método para fechamento do Statement
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResult(ResultSet rs) { //método para fechamento do ResultSet
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
