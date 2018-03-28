package fr.shingshang.model.pion;

import java.io.Serializable;
import java.util.List;

import fr.shingshang.model.Deplacement;
import fr.shingshang.model.Joueur;
import fr.shingshang.model.enumeration.PuissancePion;
import fr.shingshang.model.plateau.CasePlateau;
import fr.shingshang.model.plateau.Plateau;

public abstract class Pion implements Serializable{
	private static final long serialVersionUID = -7632733406514627195L;
	protected CasePlateau casePlateau;
	protected Joueur joueur;
	protected PuissancePion puissance;
	protected String nom;
	protected String codeNom;
	
	public Pion(CasePlateau casePlateau, Joueur joueur) {
		this.casePlateau = casePlateau;
		this.joueur = joueur;
		this.puissance = PuissancePion.AUTRE;
		this.nom = "Autre";
		this.codeNom = "A";
	}
	
	abstract public List<Deplacement> listDeplacementPossible(Plateau plateau);
	
	public String toString() {
		return this.nom+"\t["+this.getX()+":"+this.getY()+"]\tJoueur: "+this.joueur.getNumero();
	}
	
	public int getX() {
		return casePlateau.getX();
	}
	public int getY() {
		return casePlateau.getY();
	}
	public CasePlateau getCasePlateau(){
		return this.casePlateau;
	}
	public void setCasePlateau(CasePlateau casePlateau){
		this.casePlateau = casePlateau;
	}
	public Joueur getJoueur() {
		return this.joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	public PuissancePion getPuissance() {
		return puissance;
	}
	public String getCodeNom() {
		return this.codeNom;
	}
		
}
