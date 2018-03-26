package fr.shingshang.modele;

import java.util.List;

public class MainApp {

	private static ShingShang shingShang;
	
	public static void main(String[] args) {
		int choix = 0;
		shingShang = new ShingShang();
		
		
		while(choix < 1 || choix > 2)
		{
			choix = Menu.menuPrincipal();
		}
		
		switch (choix){
		case 1:
			shingShang = Menu.menuNouvellePartie();
			break;
		case 2:
			shingShang = Menu.menuChargerPartie();
			break;
		}
		
		// Selection d'un pion
		System.out.println(shingShang.getPlateau());
		System.out.println(shingShang.getJoueurActuel().getNom()+" à vous de jouer !");
		Pion pionJoueur = Menu.menuSelectionnerPion(shingShang);
		
		// Recuperation list deplacement possible
		List<Deplacement> maListe = pionJoueur.listDeplacementPossible(shingShang.getPlateau());
		// Afficher deplacement
		for(int i = 0; i < maListe.size(); i++)
			System.out.println(maListe.get(i));
		
		System.out.println(shingShang.getPlateau());
		System.out.println("****** FIN PROGRAMME *****");
	}


}
