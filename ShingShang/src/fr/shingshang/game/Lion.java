package fr.shingshang.game;

import java.util.List;

import fr.shingshang.game.enumeration.PuissancePion;

public class Lion extends Pion {
	private static final long serialVersionUID = 7599412123823107774L;

	public Lion(int x, int y) {
		super(x, y);
		this.puissance = PuissancePion.LION;
		this.nom = "Lion";
	}

	@Override
	public List<Deplacement> listDeplacementPossible(CasePlateau tabCasePlateau[][]) {
		// TODO Auto-generated method stub
		return null;
	}

}
