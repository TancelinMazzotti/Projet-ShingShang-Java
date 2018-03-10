package fr.shingshang.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Joueur implements Serializable{
	private static final long serialVersionUID = -3098217927160158983L;
	private int numero;
	private String nom;
	private List<Pion> listPion;
	
	public Joueur(int numero, String nom) {
		this.numero = numero;
		this.nom = nom;
		this.listPion = new ArrayList<Pion>();
	}
	
	public void generationPion() throws IOException
	{
		File fichierCharger = new File("src/fr/shingshang/game/pattern/defaut-placement.pattern");

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fichierCharger)));
		int numeroLigne = 0;
		String ligne;
		while((ligne=br.readLine())!=null)
		{
			String numeroCase[] = ligne.split(" ");
			for(int i = 0; i < numeroCase.length; i++)
			{
				String stringPion[] = numeroCase[i].split(":");
				if(Integer.parseInt(stringPion[0]) == this.numero)
				{
					switch(Integer.parseInt(stringPion[1]))
					{
						case 1:
							Singe singe = new Singe(i,numeroLigne);
							this.ajouterPion(singe);
							break;
						case 2:
							Lion lion = new Lion(i,numeroLigne);
							this.ajouterPion(lion);
							break;
						case 3:
							Dragon dragon = new Dragon(i,numeroLigne);
							this.ajouterPion(dragon);
							break;
						default:
							System.out.println("Erreur fichier");
							break;
					}
				}
			}
			numeroLigne++;
		}
		br.close();
	}
	
	public String toString() {
		String joueurString = "Joueur: "+this.numero+" "+this.nom+"\n";
		for(int i = 0; i < this.listPion.size(); i++)
		{
			joueurString += this.listPion.get(i)+"\n";
		}
		return joueurString;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Pion> getListPion() {
		return listPion;
	}
	public void setListPion(List<Pion> listPion) {
		this.listPion = listPion;
	}
	public void ajouterPion(Pion pion) {
		this.listPion.add(pion);
	}
	
	
}
