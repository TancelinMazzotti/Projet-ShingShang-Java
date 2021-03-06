package fr.shingshang.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import fr.shingshang.model.enumeration.PuissancePion;
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
	
	/**
	 * Cr�ation d'une nouvelle partie de Shing - Shang
	 * @param nomJ1
	 *	Nom du joueur 1
	 * @param nomJ2
	 * 	Nom du joueur 2
	 * @throws IOException
	 * @throws ValeurAttributException
	 */
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
	/**
	 * Sauvegarde la partie courante
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void sauvegarderPartie() throws FileNotFoundException, IOException {
		GestionSauvegarde.sauvegarder(this.cheminSauvegarde, this);
	}
	/**
	 * Sauvegarde la partie courante
	 * @param pathFile
	 * 	Chemin de la sauvegarde
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void sauvegarderPartie(String pathFile) throws FileNotFoundException, IOException {
		GestionSauvegarde.sauvegarder(pathFile, this);
	}
	/**
	 * Charge la partie choisie
	 * @param pathFile
	 * 	Chemin de la sauvegarde
	 * @return
	 * 	Renvoie une partie de Shing - Sahnag charg�
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ShingShang chargerPartie(String pathFile) throws ClassNotFoundException, FileNotFoundException, IOException {
		return GestionSauvegarde.charger(pathFile);
	}
	/**
	 * G�n�ration des pions sur le plateau pour une nouvelle partie
	 * @param joueur
	 * @throws IOException
	 */
	private void generationPion(Joueur joueur) throws IOException
	{
		File fichierCharger = new File("res/defaut-placement.pattern");

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
	/**
	 * Met le joueur suivant dans joueur actuel
	 * @return
	 * 	Renvoie le nouveau joueur actuel
	 */
	public Joueur changerJoueur(){
		this.joueurActuel = this.getJoueurSuivant();
		return this.joueurActuel;
	}
	/**
	 * Test si le joueur actuel � remport� la partie
	 * @return
	 *  true si il a gagn� sinon false
	 */
	public boolean testVictoireJoueurActuel(){
		boolean victoire = false;
		boolean dragonTrouve = false;

		if (joueurActuel.getNumero() == 2)
		{
			if(plateau.getPortail1J1().getPionCase() != null){
				if(plateau.getPortail1J1().getPionCase().getJoueur() == joueur2 && plateau.getPortail1J1().getPionCase().getPuissance() == PuissancePion.DRAGON)
					victoire = true;
			}
			if(plateau.getPortail2J1().getPionCase() != null){
				if(plateau.getPortail2J1().getPionCase().getJoueur() == joueur2 && plateau.getPortail2J1().getPionCase().getPuissance() == PuissancePion.DRAGON)
					victoire = true;
			}
			
			for(int i = 0 ; i < joueur1.getListPion().size(); i++)
			{
				if (joueur1.getListPion().get(i).getPuissance() == PuissancePion.DRAGON)
					dragonTrouve =  true;
			}
			if (!dragonTrouve) victoire = true;
		}
		else if (joueurActuel.getNumero() == 1)
		{
			if(plateau.getPortail1J2().getPionCase() != null){
				if(plateau.getPortail1J2().getPionCase().getJoueur() == joueur1 && plateau.getPortail1J2().getPionCase().getPuissance() == PuissancePion.DRAGON)
					victoire = true;
			}

			if(plateau.getPortail2J2().getPionCase() != null){
				if(plateau.getPortail2J2().getPionCase().getJoueur() == joueur1 && plateau.getPortail2J2().getPionCase().getPuissance() == PuissancePion.DRAGON)
					victoire = true;
			}
			for(int i = 0 ; i < joueur2.getListPion().size(); i++)
			{
				if (joueur2.getListPion().get(i).getPuissance() == PuissancePion.DRAGON)
					dragonTrouve =  true;
			}
			if (!dragonTrouve) victoire = true;
		}
		return victoire;
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
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	public String getCheminSauvegarde() {
		return cheminSauvegarde;
	}

	public void setCheminSauvegarde(String cheminSauvegarde) throws ValeurAttributException {
		if(cheminSauvegarde == null)
			throw new ValeurAttributException();
		if(cheminSauvegarde == "" || cheminSauvegarde.length() == 0)
			throw new ValeurAttributException();
		
		this.cheminSauvegarde = cheminSauvegarde;
	}

}
