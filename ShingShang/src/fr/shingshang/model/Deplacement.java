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
	
	/**
	 * Cr�ation d'un d�placement
	 * @param depart
	 * 	Case du pion qui va se d�placer
	 * @param destination
	 * 	Case o� le pion va arriver
	 * @param estUnSaut
	 * 	true si c'est un saut et false si c'est un d�placement normal
	 * @param casePionEliminer
	 * 	Case du pion � �liminer si c'est un saut
	 * @throws DeplacementException
	 * 	Aucun param�tre doit �tre null
	 */
	public Deplacement(CasePlateau depart,CasePlateau destination, boolean estUnSaut,CasePlateau casePionEliminer) throws DeplacementException{
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
	
	/**
	 * Recherche un deplacemnt ayant comme destination la case [x;y]
	 * @param listDeplacement
	 * 	Une liste de d�placement
	 * @param x
	 * 	Abscisse destination
	 * @param y
	 * 	Ordonn�e destination
	 * @return
	 * 	Renvoie le premier d�placement respectant les crit�res dans la liste
	 * @throws DeplacementException
	 * 	L�ve une exception si on ne trouve pas de d�placement
	 */
	public static Deplacement rechercheDestinantionListDeplacement(List<Deplacement> listDeplacement, int x, int y) throws DeplacementException{
		Deplacement deplacement = null;
		for(int i = 0; i < listDeplacement.size(); i++)
		{
			if (listDeplacement.get(i).getDestination().getX() == x && listDeplacement.get(i).getDestination().getY() == y)
				deplacement = listDeplacement.get(i);
		}
		if (deplacement == null) throw new DeplacementException("Deplacement innexistant");
		
		return deplacement;
	}
	
	/**
	 * D�place le pion dans la case de d�part dans la case de destination
	 * @throws DeplacementException
	 * 	D�placement impossible
	 */
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

	/**
	 * Supprime le pion � �limniner sur le plateau et dans la liste de pion du joueur
	 */
	public void supprimerPion(){
		if(casePionEliminer != null){
			Pion pionEliminer = this.casePionEliminer.getPionCase();
			Joueur joueurPion = pionEliminer.getJoueur();
			joueurPion.getListPion().remove(pionEliminer);
			this.casePionEliminer.setPionCase(null);
		}
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
