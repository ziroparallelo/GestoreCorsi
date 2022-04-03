package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	//Bisogna crearsi un riferimento al DAO
	//1. O mi creo l'oggetto ogni volta che mi serve
	//2. Oppure mi creo l'attributo DAO nel modello stesso
	
	//La 2 è la scelta più furba
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	//Fa solo da passa carte
	
	public Model() 
	{
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}
  

	public List<Corso> getCorsiByPeriodo(int periodo)
	{
		return this.corsoDao.getCorsiByPeriodo(periodo);
	}
	
	public Map<Corso, Integer> getIscritti (int periodo)
	{
		return this.corsoDao.getIscritti(periodo);
	}
	
	public List<Studente> getStudentiByCorso(String codins)
	{
		return this.studenteDao.getStudentiByCorso(codins);
	}
	
	public List<Divisione> getDivisioneStudenti(String codins)
	{
		return this.studenteDao.getDivisioneStudenti(codins);
	}
}
