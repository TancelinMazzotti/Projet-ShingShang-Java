package fr.shingshang.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestionSauvegardeBis implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nomJoueur1;
	private String nomJoueur2;
	private int joueurActuel;
	private List<int[]>  listPionJoueur1;
	private List<int[]> listPionJoueur2;
	
	public GestionSauvegardeBis() {
		this.nomJoueur1 = "Joueur 1";
		this.nomJoueur2 = "Joueur 2";
		this.joueurActuel = 0;
		this.listPionJoueur1 = new ArrayList<int []>();
		this.listPionJoueur2 = new ArrayList<int []>();
	}
	
	void sauvegarder(ShingShang shingShang) throws FileNotFoundException, IOException {
		System.out.println("Préparation sauvegarde...");
		this.nomJoueur1 = shingShang.getJoueur1().getNom();
		this.nomJoueur2 = shingShang.getJoueur2().getNom();
		this.joueurActuel = shingShang.getJoueur1().getNumero();
		
		for(int i = 0; i < shingShang.getJoueur1().getListPion().size(); i++)
		{
			int pion[] = new int[3];
			pion[0] = shingShang.getJoueur1().getListPion().get(i).getX();
			pion[1] = shingShang.getJoueur1().getListPion().get(i).getY();
			pion[2] = shingShang.getJoueur1().getListPion().get(i).getPuissance().value;
			this.listPionJoueur1.add(pion);
		}
		for(int i = 0; i < shingShang.getJoueur2().getListPion().size(); i++)
		{
			int pion[] = new int[3];
			pion[0] = shingShang.getJoueur2().getListPion().get(i).getX();
			pion[1] = shingShang.getJoueur2().getListPion().get(i).getY();
			pion[2] = shingShang.getJoueur2().getListPion().get(i).getPuissance().value;
			this.listPionJoueur2.add(pion);
		}
		
		System.out.println("Sauvegarde en cours...");
		this.ecritureFichier();
		
		System.out.println("[SUCCESS] Sauvegarde terminé");
	}
	
	void charger(ShingShang shingShang) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Chargement en cours...");
		this.lectureFichier();
		
		System.out.println("Application chargement...");
		Joueur joueur1 = new Joueur(1,this.nomJoueur1);
		Joueur joueur2 = new Joueur(2,this.nomJoueur2);
		Joueur joueurActuel;
		if(this.joueurActuel == 1) {
			joueurActuel = joueur1;
		}
		else {
			joueurActuel = joueur2;
		}
		
		for(int i = 0; i < this.listPionJoueur1.size(); i++)
		{
			Pion pion;
			pion = new Pion(this.listPionJoueur1.get(i)[0],this.listPionJoueur1.get(i)[1]);
			joueur1.ajouterPion(pion);
		}
		
		shingShang.setJoueur1(joueur1);
		shingShang.setJoueur2(joueur2);
		shingShang.setJoueurActuel(joueurActuel);
		System.out.println("[SUCCESS] Chargement terminé");
	}
	private void ecritureFichier() throws FileNotFoundException, IOException {
		File fichierSauvegarde = new File ("partie.save");

		ObjectOutputStream writeStream = new ObjectOutputStream (new FileOutputStream (fichierSauvegarde));
		writeStream.writeObject (this);
		writeStream.close();
	}
	private void lectureFichier() throws FileNotFoundException, IOException, ClassNotFoundException {
		File fichierCharger = new File ("partie.save");
		
		ObjectInputStream readStream = new ObjectInputStream (new FileInputStream (fichierCharger));
		GestionSauvegardeBis gestLoad = (GestionSauvegardeBis) readStream.readObject();
		readStream.close();
		this.nomJoueur1 = gestLoad.getNomJoueur1();
		this.nomJoueur2 = gestLoad.getNomJoueur2();
		this.joueurActuel = gestLoad.getJoueurActuel();
		this.listPionJoueur1 = gestLoad.getListPionJoueur1();
		this.listPionJoueur2 = gestLoad.getListPionJoueur2();
	}

	public String getNomJoueur1() {
		return nomJoueur1;
	}
	public void setNomJoueur1(String nomJoueur1) {
		this.nomJoueur1 = nomJoueur1;
	}
	public String getNomJoueur2() {
		return nomJoueur2;
	}
	public void setNomJoueur2(String nomJoueur2) {
		this.nomJoueur2 = nomJoueur2;
	}
	public int getJoueurActuel() {
		return joueurActuel;
	}
	public void setJoueurActuel(int joueurActuel) {
		this.joueurActuel = joueurActuel;
	}
	public List<int[]> getListPionJoueur1() {
		return listPionJoueur1;
	}
	public void setListPionJoueur1(List<int[]> listPionJoueur1) {
		this.listPionJoueur1 = listPionJoueur1;
	}
	public List<int[]> getListPionJoueur2() {
		return listPionJoueur2;
	}
	public void setListPionJoueur2(List<int[]> listPionJoueur2) {
		this.listPionJoueur2 = listPionJoueur2;
	}
	
}
