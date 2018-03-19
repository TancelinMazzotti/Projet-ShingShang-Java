package fr.shingshang.game;

import java.io.Serializable;
import java.util.List;

import fr.shingshang.game.enumeration.PuissancePion;

public abstract class Pion implements Serializable{
	private static final long serialVersionUID = -7632733406514627195L;
	protected CasePlateau casePlateau;
	protected Joueur joueur;
	protected PuissancePion puissance;
	protected String nom;
	
	public Pion(CasePlateau casePlateau, Joueur joueur) {
		this.casePlateau = casePlateau;
		this.joueur = joueur;
		this.puissance = PuissancePion.AUTRE;
		this.nom = "Autre";
	}
	
	abstract public List<Deplacement> listDeplacementPossible(CasePlateau tabCasePlateau[][]);
	
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
	public void setPuissance(PuissancePion puissance) {
		this.puissance = puissance;
	}
		
}
