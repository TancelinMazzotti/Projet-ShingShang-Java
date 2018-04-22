package model.joueur;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.shingshang.model.Joueur;
import fr.shingshang.model.execption.ValeurAttributException;

public class TestJoueur {
	private static Joueur joueur;
	
	@Test
	public void test01Constructeur() throws ValeurAttributException{
		int numero = 1;
		String nom = "Joueur 1";
		joueur = new Joueur(numero,nom);
		assertEquals(numero,joueur.getNumero());
		assertEquals(nom,joueur.getNom());
		assertNotEquals(joueur.getListPion(),null);
	}
	@Test(expected = ValeurAttributException.class)
	public void test02Constructeur() throws ValeurAttributException{
		int numero = 1;
		String nom = "";
		joueur = new Joueur(numero,nom);
	}
	@Test(expected = ValeurAttributException.class)
	public void test03Constructeur() throws ValeurAttributException{
		int numero = 0;
		String nom = "Joueur 0";
		joueur = new Joueur(numero,nom);
	}
	@Test(expected = ValeurAttributException.class)
	public void test04Constructeur() throws ValeurAttributException{
		int numero = 0;
		String nom = "";
		joueur = new Joueur(numero,nom);
	}
	@Test(expected = ValeurAttributException.class)
	public void test05Constructeur() throws ValeurAttributException{
		int numero = 3;
		String nom = "Joueur 3";
		joueur = new Joueur(numero,nom);
	}
	public void test06Constructeur() throws ValeurAttributException{
		int numero = 3;
		String nom = "";
		joueur = new Joueur(numero,nom);
	}

	
}
