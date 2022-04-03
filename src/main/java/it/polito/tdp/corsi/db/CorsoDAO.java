package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	
	public List<Corso> getCorsiByPeriodo(int periodo)
	{
		//Pattern DAO
		
		//Mi copio la query dal client
		//Occhio agli spazi e agli /n da cancellare
		String sql = "select * "
				+ "from corso "
				+ "where pd = ?";
		
		List<Corso> result = new ArrayList<Corso>();
		//Codice che accede al DB
		//Puo dare vita ad eccezioni
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			//La nostra query ha parametri?
			//Si il periodo richiesto dall'utente 
			//(? nella query)
			
			//Posizione del parametro
			//Parte da 1
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			//Due righe immaginarie 1 all'inizio e 1 alla fine
			while(rs.next()) //La riga successiva c'è?
			{
				//Devo mettere tipo di dato e il nome
				//della colonna del DB e non della 
				//clase Java !!!
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), 
						rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			
			st.close();
			rs.close();
			conn.close();
			return result;
			
		} catch(SQLException e)
		{
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	//Utilizzo una Litsa perché questa
	//si ordina più facilmente, quindi creo una
	//Classe con attributi voto e nIscritti
	//per poter risalire velocemente al n di Iscritti
	
	//Ora vediamo con la Mappa anche se è 
	//più difficile riordinarla
	public Map<Corso, Integer> getIscritti(int periodo)
	{
		String sql="select c.codins, c.crediti, c.nome, c.pd, count(*) as n "
				+ "from corso c, iscrizione i "
				+ "where c.codins = i.codins and c.pd = ? "
				+ "group by c.codins, c.crediti, c.nome, c.pd";
		
		Map<Corso, Integer> result = new HashMap<Corso, Integer>();
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				result.put(new Corso(rs.getString("codins"), 
						rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd")), rs.getInt("n"));
			}
			rs.close();
			st.close();
			conn.close();
			
			return result;
		} catch(SQLException e)
		{
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
		
	}

}
