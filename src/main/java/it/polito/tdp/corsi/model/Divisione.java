package it.polito.tdp.corsi.model;

public class Divisione implements Comparable<Divisione>{

	private String CDS;
	private Integer n;
	
	public Divisione(String cDS, Integer n) 
	{
		CDS = cDS;
		this.n = n;
	}

	public String getCDS() {
		return CDS;
	}

	public void setCDS(String cDS) {
		CDS = cDS;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return "Divisione [CDS=" + CDS + ", n=" + n + "]";
	}

	@Override
	public int compareTo(Divisione o) 
	{
		return this.CDS.compareTo(o.getCDS());
	}
	
	//Non andranno mai in un hashmap o in un hashset
	//e nemmeno un equals perché è solo una classe di appoggio
	
	
}
