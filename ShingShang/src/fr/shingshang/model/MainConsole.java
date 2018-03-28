package fr.shingshang.model;

import java.util.List;

import fr.shingshang.model.execption.DeplacementException;
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
		
		MenuConsole.afficherPlateau(shingShang.getPlateau());
		Pion pionJoueur = MenuConsole.menuSelectionnerPion(shingShang);
		List<Deplacement> listeDeplacement = pionJoueur.listDeplacementPossible(shingShang.getPlateau());
		MenuConsole.afficherPlateau(shingShang.getPlateau(), listeDeplacement);
		Deplacement deplacement = MenuConsole.menuSelectionnerDeplacement(listeDeplacement);
		try {
			deplacement.deplacerPion();
			MenuConsole.afficherPlateau(shingShang.getPlateau());
		} catch (DeplacementException e) {
			System.out.println(e);
		}
		
		
		
		System.out.println("****** FIN PROGRAMME *****");
	}


}
