package fr.shingshang.game;

import java.util.ArrayList;
import java.util.List;

import fr.shingshang.game.enumeration.PuissancePion;
import fr.shingshang.game.enumeration.TypeCasePlateau;

public class Lion extends Pion {
	private static final long serialVersionUID = 7599412123823107774L;

	public Lion(CasePlateau casePlateau, Joueur joueur) {
		super(casePlateau, joueur);
		this.puissance = PuissancePion.LION;
		this.nom = "Lion";
	}

	@Override
	public List<Deplacement> listDeplacementPossible(CasePlateau tabCasePlateau[][]) {
		List<Deplacement> listDeplacement = new ArrayList<Deplacement>();
		
		//Calcul zone du plateau autour du pion 
		int yMin = this.getY() - 1;
		int yMax = this.getY() + 1;
		int xMin = this.getX() - 1;
		int xMax = this.getX() + 1;
		
		for(int y = yMin; y <= yMax; y++)
		{	
			for(int x = xMin; x <= xMax; x++)
			{
				// Calcul coordonne saut
				int ySaut = this.getY();
				int xSaut = this.getX();
				if(y < this.getY()) ySaut = y - 1;
				else if(y > this.getY()) ySaut = y + 1;
				if(x < this.getX()) xSaut = x - 1;
				else if(x > this.getX()) xSaut = x + 1;
				
				// Controle case autour du pion validité
				if(Plateau.estSurPlateau(x, y) && tabCasePlateau[x][y].getType() != TypeCasePlateau.BLOQUE)
				{
					// Si la case de destination est vide
					if(tabCasePlateau[x][y].getPionCase() == null)
						listDeplacement.add(new Deplacement(tabCasePlateau[this.getX()][this.getY()],tabCasePlateau[x][y],false,null));
					
					// Si le pion et plus puissant que l'obstacle et la destination de saut ne sort pas du tableau
					else if(tabCasePlateau[x][y].getPionCase().puissance.value <= this.puissance.value
							&& xSaut >= 0 && ySaut >= 0
							&& xSaut < Plateau.TAILLE_PLATEAU && ySaut < Plateau.TAILLE_PLATEAU)
					{
						// Si la case destination saut n'est pas bloqué et est vide
						if(tabCasePlateau[xSaut][ySaut].getType() != TypeCasePlateau.BLOQUE 
								&& tabCasePlateau[xSaut][ySaut].getPionCase() == null)
						{
							listDeplacement.add(new Deplacement(tabCasePlateau[this.getX()][this.getY()],tabCasePlateau[xSaut][ySaut],true,tabCasePlateau[x][y]));
						}
					}
				}
			}
		}
		return listDeplacement;
	}

}
