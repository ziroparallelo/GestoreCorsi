package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO 
{
	public List<Studente> getStudentiByCorso(String codins)
	{
		String sql ="select s.matricola, s.cognome, s.nome, s.CDS "
				+ "from studente s, iscrizione i "
				+ "where s.matricola = i.matricola and i.codins = ?";
		
		List <Studente> result = new ArrayList<Studente>();
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codins); //Non serve mettere '?' perché lo fa lui
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				result.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return result;
		} catch (SQLException e)
		{
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	//Mappa di stringhe e interi
	//Lista di oggetti di una classe di appoggio
	//Voler ordinare per CDS? -> Meglio la lista che è 
	//migliore da ordinare
	
	public List<Divisione> getDivisioneStudenti(String divisione)
	{
		String sql = "select s.CDS, COUNT(*) as n "
				+ "from studente s, iscrizione i "
				+ "where i.matricola = s.matricola AND i.codins = ? AND s.cds <> '' "
				+ "group by s.CDS";
		
		List<Divisione> result = new ArrayList<Divisione>();
try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, divisione); //Non serve mettere '?' perché lo fa lui
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				result.add(new Divisione(rs.getString("CDS"), rs.getInt("n")));
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return result;
		} catch (SQLException e)
		{
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
		
	}
	
}
