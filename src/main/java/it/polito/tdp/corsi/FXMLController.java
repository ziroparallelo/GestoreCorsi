/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	//1 Controlli 
    	
    	String perido = txtPeriodo.getText();
    	int periodoNumerico;
    
    	try {
    	periodoNumerico = Integer.parseInt(txtPeriodo.getText());
    	} catch(NumberFormatException nfe) {
    		txtRisultato.setText("Inserisci un perido numerico!");
    		return;
    	}
    	
    	if(periodoNumerico < 1 || periodoNumerico > 2)
    	{
    		txtRisultato.setText("Inserisci 1 o 2");
    		return;
    	}
    	
    	//2 Operazione
    	
    	List<Corso> corsi = this.model.getCorsoByPeriodo(periodoNumerico);
    	
    	//3 Aggiornamento
    	
    	
    	for(Corso c : corsi)
    	{
    		txtRisultato.appendText(c +"\n");
    	}
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	//1 Controlli 
    	
    	String perido = txtPeriodo.getText();
    	int periodoNumerico;
    
    	try {
    	periodoNumerico = Integer.parseInt(txtPeriodo.getText());
    	} catch(NumberFormatException nfe) {
    		txtRisultato.setText("Inserisci un perido numerico!");
    		return;
    	}
    	
    	if(periodoNumerico < 1 || periodoNumerico > 2)
    	{
    		txtRisultato.setText("Inserisci 1 o 2");
    		return;
    	}
    	
    	//2 Operazione
    	
    	Map<Corso, Integer> iscritti = this.model.getIscritti(periodoNumerico);
    	
    	//3 Aggiornamento
    	
    	
    	for(Corso c : iscritti.keySet())
    	{
    		txtRisultato.appendText(c +" "+iscritti.get(c)+"\n");
    	}
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	
txtRisultato.clear();
    	
    	//1 Controlli 
    	
    	String codins = txtCorso.getText();
    	
    	if(codins == null || codins.equals(""))
    	{
    		txtRisultato.setText("Inserisci codice corso");
    		return;
    	}
    	
    	//Controllo se il corso esista!
    	
    	//2 Operazione
    	
    	List<Divisione> divisioni = this.model.getDivisioneStudenti(codins);

    	Collections.sort(divisioni);
    	//3 Aggiornamento
    	
    	
    	for(Divisione d : divisioni) 
    		txtRisultato.appendText(d +"\n");


    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	//1 Controlli 
    	
    	String codins = txtCorso.getText();
    	
    	if(codins == null || codins.equals(""))
    	{
    		txtRisultato.setText("Inserisci codice corso");
    		return;
    	}
    	
    	//Controllo se il corso esista!
    	
    	//2 Operazione
    	
    	List<Studente> studenti = this.model.getStudenti(codins);
    	
    	//3 Aggiornamento
    	
    	
    	for(Studente s : studenti) 
    		txtRisultato.appendText(s +"\n");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}