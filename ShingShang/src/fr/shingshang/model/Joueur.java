package fr.shingshang.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.shingshang.model.execption.ValeurAttributException;
import fr.shingshang.model.pion.Pion;

public class Joueur implements Serializable{
	private static final long serialVersionUID = -3098217927160158983L;
	private int numero;
	private String nom;
	private List<Pion> listPion;
	
	public Joueur(int numero, String nom) throws ValeurAttributException {
		if(numero < 1 || numero > 2)
			throw new ValeurAttributException();
		if(nom == null)
			throw new ValeurAttributException();
		if(nom == "" || nom.length() == 0)
			throw new ValeurAttributException();
		
		this.numero = numero;
		this.nom = nom;
		this.listPion = new ArrayList<Pion>();
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
