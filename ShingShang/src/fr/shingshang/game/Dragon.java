package fr.shingshang.game;

import fr.shingshang.game.enumeration.PuissancePion;

public class Dragon extends Pion{
	private static final long serialVersionUID = -3113333146484676981L;

	public Dragon(int x, int y) {
		super(x, y);
		this.puissance = PuissancePion.DRAGON;
		this.nom = "Dragon";
	}

}
