package model.joueur;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.shingshang.model.Joueur;
import fr.shingshang.model.execption.ValeurAttributException;

public class TestJoueur {
	private static Joueur joueur;
	
	public void initJoueur(){
		int numero = 1;
		String nom = "Joueur 1";
		try {
			joueur = new Joueur(numero,nom);
		} catch (ValeurAttributException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test001Constructeur() throws ValeurAttributException{
		int numero = 1;
		String nom = "Joueur 1";
		joueur = new Joueur(numero,nom);
		assertEquals(numero,joueur.getNumero());
		assertEquals(nom,joueur.getNom());
		assertNotEquals(joueur.getListPion(),null);
	}
	@Test(expected = ValeurAttributException.class)
	public void test002Constructeur() throws ValeurAttributException{
		int numero = 1;
		String nom = "";
		joueur = new Joueur(numero,nom);
	}
	@Test(expected = ValeurAttributException.class)
	public void test003Constructeur() throws ValeurAttributException{
		int numero = 0;
		String nom = "Joueur 0";
		joueur = new Joueur(numero,nom);
	}
	@Test(expected = ValeurAttributException.class)
	public void test004Constructeur() throws ValeurAttributException{
		int numero = 0;
		String nom = "";
		joueur = new Joueur(numero,nom);
	}
	@Test(expected = ValeurAttributException.class)
	public void test005Constructeur() throws ValeurAttributException{
		int numero = 3;
		String nom = "Joueur 3";
		joueur = new Joueur(numero,nom);
	}
	public void test006Constructeur() throws ValeurAttributException{
		int numero = 3;
		String nom = "";
		joueur = new Joueur(numero,nom);
	}

}
