package fr.shingshang.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
		this.generationPion(joueur1);
		this.joueur2 = new Joueur(2,nomJ2);
		this.generationPion(joueur2);
		
		this.plateau.ajouterPion(this.joueur1.getListPion());
		this.plateau.ajouterPion(this.joueur2.getListPion());
		this.joueurActuel = this.joueur1;
	}
	public void sauvegarderPartie(String pathFile) throws FileNotFoundException, IOException {
		GestionSauvegarde.sauvegarder(pathFile, this);
	}	
	public static ShingShang chargerPartie(String pathFile) throws ClassNotFoundException, FileNotFoundException, IOException {
		return GestionSauvegarde.charger(pathFile);
	}

	private void generationPion(Joueur joueur) throws IOException
	{
		File fichierCharger = new File("src/fr/shingshang/game/pattern/defaut-placement.pattern");

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fichierCharger)));
		int numeroLigne = 0;
		String ligne;
		
		while((ligne=br.readLine())!=null)
		{
			String numeroCase[] = ligne.split(" ");
			for(int i = 0; i < numeroCase.length; i++)
			{
				String stringPion[] = numeroCase[i].split(":");
				if(Integer.parseInt(stringPion[0]) == joueur.getNumero())
				{
					switch(Integer.parseInt(stringPion[1]))
					{
						case 1:
							Singe singe = new Singe(this.getCasePlateau(i, numeroLigne),joueur);
							joueur.ajouterPion(singe);
							break;
						case 2:
							Lion lion = new Lion(this.getCasePlateau(i, numeroLigne),joueur);
							joueur.ajouterPion(lion);
							break;
						case 3:
							Dragon dragon = new Dragon(this.getCasePlateau(i, numeroLigne),joueur);
							joueur.ajouterPion(dragon);
							break;
						default:
							System.out.println("Erreur fichier");
							break;
					}
				}
			}
			numeroLigne++;
		}
		br.close();
	}
	
	public CasePlateau getCasePlateau(int x, int y){
		return this.plateau.getTabCasePlateauIndex(x, y);
	}
	public Pion getPion(int x,int y){
		return this.getCasePlateau(x,y).getPionCase();
	}
	public Pion getPionJoueurActuel(int x, int y){
		if(this.getPion(x,y) != null && this.getPion(x,y).getJoueur() == this.joueurActuel)
		{
			return this.getPion(x,y);
		}
		else return null;
	}
	public Joueur getJoueurSuivant(){
		if(this.joueurActuel == this.joueur1) this.joueurActuel = this.joueur2;
		else this.joueurActuel = this.joueur2;
		return this.joueurActuel;
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
