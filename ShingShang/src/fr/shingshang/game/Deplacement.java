package fr.shingshang.game;

public class Deplacement {
	private CasePlateau depart;
	private CasePlateau destination;
	private boolean estUnSaut;
	private CasePlateau casePionEliminer;
	
	public Deplacement(CasePlateau depart,CasePlateau destination,
			boolean estUnSaut,CasePlateau casePionEliminer) {
		this.depart = depart;
		this.destination = destination;
		this.estUnSaut = estUnSaut;
		this.casePionEliminer = casePionEliminer;
		
	}
	
	public CasePlateau getDepart() {
		return depart;
	}
	public void setDepart(CasePlateau depart) {
		this.depart = depart;
	}
	public CasePlateau getDestination() {
		return destination;
	}
	public void setDestination(CasePlateau destination) {
		this.destination = destination;
	}
	public boolean isEstUnSaut() {
		return estUnSaut;
	}
	public void setEstUnSaut(boolean estUnSaut) {
		this.estUnSaut = estUnSaut;
	}
	public CasePlateau getCasePionEliminer() {
		return casePionEliminer;
	}
	public void setCasePionEliminer(CasePlateau casePionEliminer) {
		this.casePionEliminer = casePionEliminer;
	}
	
	public String toString(){
		String message = new String();
		message += "### DEPLACEMENT ###\n";
		message += "Deapart: "+depart.toString()+"\n";
		message += "Destination: "+destination.toString()+"\n";
		message += "Saut: "+estUnSaut+"\n";
		return message;
	}
	

}
