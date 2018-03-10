package fr.shingshang.game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class ShingShang implements Serializable{
	private static final long serialVersionUID = 5545356964013348585L;
	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurActuel;
	
	public ShingShang() {
		
	}
	
	public void nouvellePartie(String nomJ1, String nomJ2) throws IOException {
		this.plateau = new Plateau();
		
		this.joueur1 = new Joueur(1,nomJ1);
		this.joueur1.generationPion();
		
		this.joueur2 = new Joueur(2,nomJ2);
		this.joueur2.generationPion();
		
		this.plateau.ajouterPion(this.joueur1.getListPion());
		this.plateau.ajouterPion(this.joueur2.getListPion());
		
		this.joueurActuel = this.joueur1;
		
	}
	
	public void sauvegarderPartie(String pathFile) throws FileNotFoundException, IOException {
		GestionSauvegarde saveShingShang = new GestionSauvegarde();
		saveShingShang.sauvegarder(pathFile, this);
	}
	
	public ShingShang chargerPartie(String pathFile) throws ClassNotFoundException, FileNotFoundException, IOException {
		GestionSauvegarde loadShingShang = new GestionSauvegarde();
		ShingShang shingShang = null;
		shingShang = loadShingShang.charger(pathFile);
		
		return shingShang;
	}

	public Plateau getPlateau() {
		return plateau;
	}
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	public Joueur getJoueur1() {
		return joueur1;
	}
	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}
	public Joueur getJoueur2() {
		return joueur2;
	}
	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

}
