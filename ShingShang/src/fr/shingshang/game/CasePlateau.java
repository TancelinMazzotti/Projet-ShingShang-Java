package fr.shingshang.game;


import java.io.Serializable;

import fr.shingshang.game.enumeration.TypeCasePlateau;

public class CasePlateau implements Serializable{
	private static final long serialVersionUID = 7038042643152918875L;
	private int x;
	private int y;
	private Pion pionCase;
	private TypeCasePlateau type;
	
	public CasePlateau(int x, int y) {
		this.x = x;
		this.y = y;
		this.pionCase = null;
		this.type = TypeCasePlateau.NORMAL;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Pion getPionCase() {
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

	
}
