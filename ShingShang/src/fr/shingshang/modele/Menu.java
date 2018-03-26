package fr.shingshang.modele;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.shingshang.modele.execption.CaseBloqueException;
import fr.shingshang.modele.execption.HorsPlateauException;

public class Menu {
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
		
		Menu.afficherBanniere();
		
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
				init = true;
			} catch (FileNotFoundException e) {
				System.out.println("Erreur nom de fichier !");
			} catch (IOException e) {
				System.out.println("Echec de creation de la partie !");
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
				init = true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("Fichier introuvable");
			} catch (IOException e) {
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
		
		while(!valide)
		{
			try {
				System.out.print("Entrez x : ");
				x = scannerInt.nextInt();
				System.out.print("Entrez y : ");
				y = scannerInt.nextInt();
				pion =  shingShang.getPionJoueurActuel(x,y);
				valide = true;
			}
			catch(InputMismatchException e) 
			{
				System.out.println("Erreur de saisie !");
			}
			catch (HorsPlateauException e) {
				e.printStackTrace();
			} catch (CaseBloqueException e) {
				e.printStackTrace();
			}
		}
		return pion;
	}

}
