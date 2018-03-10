package fr.shingshang.game;

public class Deplacement {
	private CasePlateau depart;
	private CasePlateau destination;
	private boolean estUnSaut;
	private Pion pionEliminer;
	
	public Deplacement(CasePlateau depart,CasePlateau destination,
			boolean estUnSaut,Pion pionEliminer) {
		this.depart = depart;
		this.destination = destination;
		this.estUnSaut = estUnSaut;
		this.pionEliminer = pionEliminer;
		
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
	public Pion getPionEliminer() {
		return pionEliminer;
	}
	public void setPionEliminer(Pion pionEliminer) {
		this.pionEliminer = pionEliminer;
	}
	
	

}
