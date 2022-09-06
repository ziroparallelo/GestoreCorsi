package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getStudentiByCorso(String codins) {
		
		List<Studente> studenti = new ArrayList<Studente>();
		
		String sql = "select s.matricola, s.cognome, s.nome, s.CDS "
				+ "from studente s, iscrizione i "
				+ "where s.matricola = i.matricola "
				+ "and i.codins = ?";
		
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				studenti.add(new Studente(rs.getInt("matricola"), 
						rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return studenti;
			
		} catch (SQLException e) {
			System.out.println("ERRORE nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	//Suppongo di voler ordinare i valori per CDS allora mi conviene usare una Lista,
	//di un oggetto il quale devo crearmi la classe
	public List<Divisione> getDivisioneStudente(String codins){
		
		String sql = "select s.CDS, COUNT(*) as n "
				+ "from iscrizione i, studente s "
				+ "where i.matricola = s.matricola and i.codins = ? and s.CDS <> \"\" "
				+ "GROUP BY s.CDS ";
		//Devo usare i caratteri di escape per inserire ""
		//Altrimenti devo usare ' '
		
		List<Divisione> result = new ArrayList<Divisione>();
		

		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				result.add(new Divisione(rs.getString("CDS"), rs.getInt("n")));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("ERRORE nel DAO");
			e.printStackTrace();
			return null;
		}
		
	}

}
