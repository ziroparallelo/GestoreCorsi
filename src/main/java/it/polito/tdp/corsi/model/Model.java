package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getCorsoByPeriodo(int periodo) {
		return this.corsoDAO.getCorsoByPeriodo(periodo);
	}
	
	public Map<Corso, Integer> getIscritti(int periodo) {
		return this.corsoDAO.getIscritti(periodo);
	}
	
	public List<Studente> getStudenti(String codins) {
		return this.studenteDAO.getStudentiByCorso(codins);
	}
	
	public List<Divisione> getDivisioneStudenti(String codins){
		return this.studenteDAO.getDivisioneStudente(codins);
	}
	
}
