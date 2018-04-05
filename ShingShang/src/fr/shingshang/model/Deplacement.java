package fr.shingshang.model;

import java.util.List;

import fr.shingshang.model.execption.DeplacementException;
import fr.shingshang.model.pion.Pion;
import fr.shingshang.model.plateau.CasePlateau;

public class Deplacement {
	private CasePlateau depart;
	private CasePlateau destination;
	private Pion pion;
	private boolean estUnSaut;
	private CasePlateau casePionEliminer;
	
	public Deplacement(CasePlateau depart,CasePlateau destination,
			boolean estUnSaut,CasePlateau casePionEliminer) throws DeplacementException{
		if(depart == null && destination == null)
			throw new DeplacementException("La case de depart et de destiantion est inconnue");
		else if(depart == null)
			throw new DeplacementException("La case de depart est inconnue");
		else if(destination == null)
			throw new DeplacementException("La case de destination est inconnue");
		
		this.depart = depart;
		this.destination = destination;
		this.pion = depart.getPionCase();
		this.estUnSaut = estUnSaut;
		this.casePionEliminer = casePionEliminer;
	}
	
	public static Deplacement rechercheDestinantionListDeplacement(List<Deplacement> listDeplacement, int x, int y) throws DeplacementException{
		boolean estDedans = false;
		Deplacement deplacement = null;
		for(int i = 0; i < listDeplacement.size(); i++)
		{
			if (listDeplacement.get(i).getDestination().getX() == x && listDeplacement.get(i).getDestination().getY() == y)
			{
				deplacement = listDeplacement.get(i);
				estDedans = true;
			}
		}
		if (!estDedans) throw new DeplacementException("Deplacement innexistant");
		
		return deplacement;
	}
	public void deplacerPion() throws DeplacementException{
		if(depart == null && destination == null)
			throw new DeplacementException("La case de depart et de destiantion est inconnue");
		else if(depart == null)
			throw new DeplacementException("La case de depart est inconnue");
		else if(destination == null)
			throw new DeplacementException("La case de destination est inconnue");
		else if(depart.getPionCase() != pion)
			throw new DeplacementException("Le pion a deplacer n'est pas sur la case de depart");
		
		
		this.destination.setPionCase(this.pion);
		this.pion.setCasePlateau(this.destination);
		this.depart.setPionCase(null);
	}
	
	public CasePlateau getDepart() {
		return depart;
	}
	public CasePlateau getDestination() {
		return destination;
	}
	public boolean isEstUnSaut() {
		return estUnSaut;
	}
	public CasePlateau getCasePionEliminer() {
		return casePionEliminer;
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