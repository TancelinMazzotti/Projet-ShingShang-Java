package fr.shingshang.game;

import java.io.Serializable;
import java.util.List;

import fr.shingshang.game.enumeration.PuissancePion;

public abstract class Pion implements Serializable{
	private static final long serialVersionUID = -7632733406514627195L;
	protected int x;
	protected int y;
	protected PuissancePion puissance;
	protected String nom;
	
	public Pion(int x, int y) {
		this.x = x;
		this.y = y;
		this.puissance = PuissancePion.AUTRE;
		this.nom = "Autre";
	}
	
	abstract public List<Deplacement> listDeplacementPossible(CasePlateau tabCasePlateau[][]);
	
	public String toString() {
		return this.nom+"\t["+this.x+":"+this.y+"]";
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public PuissancePion getPuissance() {
		return puissance;
	}
	public void setPuissance(PuissancePion puissance) {
		this.puissance = puissance;
	}

		
}
