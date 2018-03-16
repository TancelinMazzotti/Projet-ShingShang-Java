package fr.shingshang.game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainApp {

	private static ShingShang shingShang;
	private static Scanner scannerInt;
	private static Scanner scannerString;
	
	public static void main(String[] args) {
		int choix = 0;
		boolean init = false;
		scannerInt = new Scanner(System.in);
		scannerString = new Scanner(System.in);
		shingShang = new ShingShang();
		
		while(init != true)
		{
			System.out.println("##### ShingShang #####");
			System.out.println("  1) Nouvelle partie");
			System.out.println("  2) Charger partie");
			System.out.print("\nVotre choix: ");
			try {
				choix = scannerInt.nextInt();
			}
			catch(InputMismatchException e) 
			{
				System.out.println("Erreur de saisie !");
				scannerInt.nextLine();
			}
			
			if (choix == 1)
			{
				// Nouvelle partie
				try {
					System.out.print("Nom joueur 1: ");
					String nomJ1 = scannerString.nextLine();
					System.out.print("Nom joueur 2: ");
					String nomJ2 = scannerString.nextLine();
					System.out.print("Nom de la sauvegarde: ");
					String nomSave = scannerString.nextLine();
					shingShang.nouvellePartie(nomJ1, nomJ2);
					shingShang.sauvegarderPartie(nomSave+".save");
					init = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
			else if(choix == 2){
				// Charger partie
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
			else System.out.println("Erreur de saisie !");
		}
		System.out.println(shingShang.getJoueur1());
		System.out.println(shingShang.getJoueur2());
		
		System.out.println(shingShang.getPlateau());
		
		List<Deplacement> maListe = shingShang.getPlateau().getTabCasePlateauIndex(1, 0).getPionCase().listDeplacementPossible(shingShang.getPlateau().getTabCasePlateau());
		for(int i = 0; i < maListe.size(); i++)
			System.out.println(maListe.get(i));
		System.out.println("****** FIN PROGRAMME *****");
	}
}
