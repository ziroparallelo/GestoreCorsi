package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	//Pattern simbleton
	//voglio che l'unico accesso
	//al DBsia questo
	public static Connection getConnection()
	{
		//Stringa di connessione
		try {
		String url = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root123.";
		return DriverManager.getConnection(url);
		}
		catch(SQLException e) {
			System.err.println("Errore di connessione");
			e.printStackTrace();
			return null;
		}
		
	}

}
