package fr.shingshang.model;

import java.util.List;

import fr.shingshang.model.pion.Pion;

public class MainConsole {

	private static ShingShang shingShang;
	
	public static void main(String[] args) {
		int choix = 0;
		shingShang = new ShingShang();
		
		
		while(choix < 1 || choix > 2)
		{
			choix = MenuConsole.menuPrincipal();
		}
		
		switch (choix){
		case 1:
			shingShang = MenuConsole.menuNouvellePartie();
			break;
		case 2:
			shingShang = MenuConsole.menuChargerPartie();
			break;
		}
		
		// Selection d'un pion
		System.out.println(shingShang.getPlateau());
		System.out.println(shingShang.getJoueurActuel().getNom()+" à vous de jouer !");
		Pion pionJoueur = MenuConsole.menuSelectionnerPion(shingShang);
		
		// Recuperation list deplacement possible
		List<Deplacement> maListe = pionJoueur.listDeplacementPossible(shingShang.getPlateau());
		// Afficher deplacement
		for(int i = 0; i < maListe.size(); i++)
			System.out.println(maListe.get(i));
		
		System.out.println(shingShang.getPlateau());
		System.out.println("****** FIN PROGRAMME *****");
	}


}
