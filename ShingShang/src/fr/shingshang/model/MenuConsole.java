package fr.shingshang.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.shingshang.model.execption.CaseBloqueException;
import fr.shingshang.model.execption.DeplacementException;
import fr.shingshang.model.execption.HorsPlateauException;
import fr.shingshang.model.execption.ValeurAttributException;
import fr.shingshang.model.pion.Pion;
import fr.shingshang.model.plateau.CasePlateau;
import fr.shingshang.model.plateau.Plateau;

public class MenuConsole {
	private static Scanner scannerInt = new Scanner(System.in);
	private static Scanner scannerString = new Scanner(System.in);
	
	public static void afficherBanniere(){
		System.out.print("   _____ _    _ _____ _   _  _____    _____ _    _          _   _  _____ \n");
		System.out.print("  / ____| |  | |_   _| \\ | |/ ____|  / ____| |  | |   /\\   | \\ | |/ ____|\n");
		System.out.print(" | (___ | |__| | | | |  \\| | |  __  | (___ | |__| |  /  \\  |  \\| | |  __ \n");
		System.out.print("  \\___ \\|  __  | | | | . ` | | |_ |  \\___ \\|  __  | / /\\ \\ | . ` | | |_ |\n");
		System.out.print("  ____) | |  | |_| |_| |\\  | |__| |  ____) | |  | |/ ____ \\| |\\  | |__| |\n");
		System.out.print(" |_____/|_|  |_|_____|_| \\_|\\_____| |_____/|_|  |_/_/    \\_\\_| \\_|\\_____|\n");
		System.out.print("\n\n\n");
	}
	public static int menuPrincipal()
	{
		boolean init = false;
		int choix = 0;
		
		MenuConsole.afficherBanniere();
		
		while(!init)
		{
			System.out.println("  1) Nouvelle partie");
			System.out.println("  2) Charger partie");
			System.out.print("\nVotre choix: ");
			
			try {
				choix = scannerInt.nextInt();
				init = true;
			}
			catch(InputMismatchException e) 
			{
				System.out.println("Erreur de saisie !");
				scannerInt.nextLine();
			}
			
		}
		return choix;
		
	}
	public static ShingShang menuNouvellePartie(){
		ShingShang shingShang = new ShingShang();
		boolean init = false;
		System.out.println("\n***** Nouvelle partie *****\n");
		
		while(!init)
		{
			System.out.print("Nom joueur 1: ");
			String nomJ1 = scannerString.nextLine();
			
			System.out.print("Nom joueur 2: ");
			String nomJ2 = scannerString.nextLine();
			
			System.out.print("Nom de la sauvegarde: ");
			String nomSave = scannerString.nextLine();
	
			try {
				shingShang.nouvellePartie(nomJ1, nomJ2);
				shingShang.sauvegarderPartie(nomSave+".save");
				shingShang.setCheminSauvegarde(nomSave+".save");
				init = true;
			} catch (FileNotFoundException e) {
				System.out.println("Erreur nom de fichier !");
			} catch (IOException e) {
				System.out.println("Echec de creation de la partie !");
			} catch (ValeurAttributException e) {
				e.printStackTrace();
			}
		}
		return shingShang;
	}
	public static ShingShang menuChargerPartie(){
		ShingShang shingShang = new ShingShang();
		boolean init = false;
		System.out.println("\n***** Charger partie *****\n");
		
		while(!init)
		{
			System.out.print("Nom de la sauvegarde: ");
			String nomSave = scannerString.nextLine();
			try {
				shingShang = ShingShang.chargerPartie(nomSave+".save");
				shingShang.setCheminSauvegarde(nomSave+".save");
				init = true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("Fichier introuvable");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ValeurAttributException e) {
				e.printStackTrace();
			}
		}
		return shingShang;
	}
	public static Pion menuSelectionnerPion(ShingShang shingShang)
	{
		boolean valide = false;
		int x, y;
		Pion pion = null;
		System.out.println(shingShang.getJoueurActuel().getNom()+" à vous de jouer !");
		
		while(!valide)
		{
			try {
				System.out.print("Entrez x : ");
				x = scannerInt.nextInt();
				System.out.print("Entrez y : ");
				y = scannerInt.nextInt();
				pion =  shingShang.getPionJoueurActuel(x,y);
				if (pion != null)
					valide = true;
				else
					System.out.println("Erreur: Case vide");
			}
			catch(InputMismatchException e) {
				System.out.println("Erreur de saisie !");
				scannerInt.nextLine();
			}
			catch (HorsPlateauException e) {
				System.out.println(e.getMessage());
			} catch (CaseBloqueException e) {
				System.out.println(e.getMessage());
			}
		}
		return pion;
	}
	public static Deplacement menuSelectionnerDeplacement(List<Deplacement> listeDeplacement){
		boolean valide = false;
		int x, y;
		Deplacement deplacement = null;
		System.out.println("Choisissez la destination");
		
		while(!valide)
		{
			
			try {
				System.out.print("Entrez x : ");
				x = scannerInt.nextInt();
				System.out.print("Entrez y : ");
				y = scannerInt.nextInt();
				deplacement = Deplacement.rechercheDestinantionListDeplacement(listeDeplacement, x, y);
				if(deplacement != null)
					valide = true;
			} catch (DeplacementException e) {
				System.out.println(e.getMessage());
			}
		}
		return deplacement;
	}
	
	public static void afficherPlateau(Plateau plateau){
		System.out.println("\n  |0|1|2|3|4|5|6|7|8|9|");
		System.out.println("-----------------------");
		for(int y = 0; y < Plateau.TAILLE_PLATEAU; y++) 
		{
			System.out.print(y+"| ");
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++) 
			{
				try {
					CasePlateau caseAfficher = plateau.getTabCasePlateauIndex(x, y);
					if(caseAfficher.getPionCase() != null)
						System.out.print(caseAfficher.getPionCase().getCodeNom()+" ");
					else if(caseAfficher.estUnPortail())
						System.out.print("P ");
					else if(caseAfficher.getPionCase() == null)
						System.out.print("* ");
				} 
				catch (HorsPlateauException e) {
					e.printStackTrace();
				} 
				catch (CaseBloqueException e) {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void afficherPlateau(Plateau plateau, List<Deplacement> listeDeplacement){
		System.out.println("\n  |0|1|2|3|4|5|6|7|8|9|");
		System.out.println("-----------------------");
		for(int y = 0; y < Plateau.TAILLE_PLATEAU; y++) 
		{
			System.out.print(y+"| ");
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++) 
			{
				try {
					CasePlateau caseAfficher = plateau.getTabCasePlateauIndex(x, y);
					if(caseAfficher.getPionCase() != null)
						System.out.print(caseAfficher.getPionCase().getCodeNom()+" ");
					else if(caseAfficher.estUnPortail())
						System.out.print("P ");
					else if(caseAfficher.getPionCase() == null)
					{
						boolean estDestination = false;
						for (int i = 0; i < listeDeplacement.size(); i++)
						{
							if (caseAfficher == listeDeplacement.get(i).getDestination())
								estDestination = true;
						}
						if (estDestination) System.out.print("X ");
						else System.out.print("* ");
					}
				} 
				catch (HorsPlateauException e) {
					e.printStackTrace();
				} 
				catch (CaseBloqueException e) {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
