package fr.shingshang.game;

import java.util.List;

import fr.shingshang.game.enumeration.PuissancePion;

public class Singe extends Pion{
	private static final long serialVersionUID = 8064448209846163754L;

	public Singe(int x, int y) {
		super(x, y);
		this.puissance = PuissancePion.SINGE;
		this.nom = "Singe";
	}

	@Override
	public List<Deplacement> listDeplacementPossible(CasePlateau tabCasePlateau[][]) {
		// TODO Auto-generated method stub
		return null;
	}

}
