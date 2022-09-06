package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public static Connection getConnection() {
		
		String url = "jdbc:mysql://localhost:3306/iscritticorsi?user=root&password=rootroot";
		try {
			Connection conn = DriverManager.getConnection(url);
			return conn;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRORE di connessione al database");
			e.printStackTrace();
			return null;
		}
		
	}
}
