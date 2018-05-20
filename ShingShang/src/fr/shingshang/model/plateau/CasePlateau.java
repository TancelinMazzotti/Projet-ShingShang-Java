package fr.shingshang.model.plateau;


import java.io.Serializable;

import fr.shingshang.model.enumeration.TypeCasePlateau;
import fr.shingshang.model.execption.HorsPlateauException;
import fr.shingshang.model.pion.Pion;

public class CasePlateau implements Serializable{
	private static final long serialVersionUID = 7038042643152918875L;
	private int x;
	private int y;
	private Pion pionCase;
	private TypeCasePlateau type;
	
	/**
	 * Création d'une case de plateau avec des coordonnées
	 * @param x
	 * 	Coordonnée en abscisse
	 * @param y
	 * 	Coordonnée en ordonnée
	 * @throws HorsPlateauException
	 * 	La case doit être à l'intérieur du plateau
	 */
	public CasePlateau(int x, int y) throws HorsPlateauException {
		if(!Plateau.estSurPlateau(x, y))
			throw new HorsPlateauException(x,y);
		
		this.x = x;
		this.y = y;
		this.pionCase = null;
		this.type = TypeCasePlateau.NORMAL;
	}
	
	/**
	 * Test si la case est un portail
	 * @return
	 * 	Renvoie true si c'est un portail
	 */
	public boolean estUnPortail(){
		if (this.type == TypeCasePlateau.PORTAIL_J1 || this.type == TypeCasePlateau.PORTAIL_J2)
			return true;
		else return false;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Pion getPionCase(){
		return pionCase;
	}
	public void setPionCase(Pion pionCase) {
		this.pionCase = pionCase;
	}
	public TypeCasePlateau getType() {
		return type;
	}
	public void setType(TypeCasePlateau type) {
		this.type = type;
	}

	public String toString(){
		String message = new String();
		message += "Case: ["+x+";"+y+"]";
		return message;
	}
}
