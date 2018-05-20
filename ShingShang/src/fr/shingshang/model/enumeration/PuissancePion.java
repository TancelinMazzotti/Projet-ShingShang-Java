package fr.shingshang.model.enumeration;

/**
 * Repr�sente la puissance que poss�de un pion
 * @author tancelin
 *
 */
public enum PuissancePion {
	DRAGON(3), LION(2), SINGE(1), AUTRE(0);
	public final int value;
	private PuissancePion(int puissance) {
		this.value = puissance;
	}
}
