package fr.shingshang.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import fr.shingshang.model.execption.CaseBloqueException;
import fr.shingshang.model.execption.HorsPlateauException;
import fr.shingshang.model.execption.ValeurAttributException;
import fr.shingshang.model.pion.Dragon;
import fr.shingshang.model.pion.Lion;
import fr.shingshang.model.pion.Pion;
import fr.shingshang.model.pion.Singe;
import fr.shingshang.model.plateau.CasePlateau;
import fr.shingshang.model.plateau.Plateau;

public class ShingShang implements Serializable{
	private static final long serialVersionUID = 5545356964013348585L;
	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurActuel;
	private String cheminSauvegarde;
	
	public ShingShang() {
		
	}
	
	public void nouvellePartie(String nomJ1, String nomJ2) throws IOException, ValeurAttributException {
		this.plateau = new Plateau();
		
		this.joueur1 = new Joueur(1,nomJ1);
		this.joueur2 = new Joueur(2,nomJ2);
		this.generationPion(joueur1);
		this.generationPion(joueur2);
		
		this.plateau.ajouterPion(this.joueur1.getListPion());
		this.plateau.ajouterPion(this.joueur2.getListPion());
		this.joueurActuel = this.joueur1;
	}
	public void sauvegarderPartie() throws FileNotFoundException, IOException {
		GestionSauvegarde.sauvegarder(this.cheminSauvegarde, this);
	}
	
	public void sauvegarderPartie(String pathFile) throws FileNotFoundException, IOException {
		GestionSauvegarde.sauvegarder(pathFile, this);
	}	
	public static ShingShang chargerPartie(String pathFile) throws ClassNotFoundException, FileNotFoundException, IOException {
		return GestionSauvegarde.charger(pathFile);
	}

	private void generationPion(Joueur joueur) throws IOException
	{
		File fichierCharger = new File("src/fr/shingshang/model/pattern/defaut-placement.pattern");

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
					try{
						CasePlateau casePion = this.getCasePlateau(i, numeroLigne);
						Pion pion = null;
						switch(Integer.parseInt(stringPion[1]))
						{
							case 1:
								pion = new Singe(casePion,joueur);
								joueur.ajouterPion(pion);
								break;
							case 2:
								pion= new Lion(casePion,joueur);
								joueur.ajouterPion(pion);
								break;
							case 3:
								pion = new Dragon(casePion,joueur);
								joueur.ajouterPion(pion);
								break;
							default:
								System.out.println("Erreur fichier");
								break;
						}
						
					}
					catch(HorsPlateauException | CaseBloqueException e){
						e.printStackTrace();
					}
				}
			}
			numeroLigne++;
		}
		br.close();
	}
	
	public CasePlateau getCasePlateau(int x, int y) throws HorsPlateauException, CaseBloqueException{
		return this.plateau.getTabCasePlateauIndex(x,y);
	}
	public Pion getPion(int x,int y) throws HorsPlateauException, CaseBloqueException{
		return this.getCasePlateau(x,y).getPionCase();
	}
	public Pion getPionJoueurActuel(int x, int y) throws HorsPlateauException, CaseBloqueException{
		if(this.getPion(x,y) != null && this.getPion(x,y).getJoueur() == this.joueurActuel)
		{
			return this.getPion(x,y);
		}
		else return null;
	}
	public Joueur getJoueurSuivant(){
		if(this.joueurActuel == this.joueur1) return this.joueur2;
		else return this.joueur1;
	}
	public Joueur changerJoueur(){
		this.joueurActuel = this.getJoueurSuivant();
		return this.joueurActuel;
	}
	
	public Plateau getPlateau() {
		return plateau;
	}
	public Joueur getJoueur1() {
		return joueur1;
	}
	public Joueur getJoueur2() {
		return joueur2;
	}
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}

	public String getCheminSauvegarde() {
		return cheminSauvegarde;
	}

	public void setCheminSauvegarde(String cheminSauvegarde) {
		this.cheminSauvegarde = cheminSauvegarde;
	}

}
