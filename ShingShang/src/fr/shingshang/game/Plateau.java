package fr.shingshang.game;


import java.io.Serializable;
import java.util.List;

import fr.shingshang.game.enumeration.TypeCasePlateau;

public class Plateau implements Serializable {
	private static final long serialVersionUID = -2056000719242757639L;
	public static final int TAILLE_PLATEAU = 10;
	private CasePlateau tabCasePlateau[][];
	
	public Plateau() {
		// Creation et initialisation de tabCasePlateau
		this.tabCasePlateau = new CasePlateau[TAILLE_PLATEAU][TAILLE_PLATEAU];
		for(int y = 0; y < Plateau.TAILLE_PLATEAU; y++)
		{
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++)
			{
				this.tabCasePlateau[x][y] = new CasePlateau(x,y);
			}
		}
		
		// Blocage des cases
		for (int i = 0; i < 4; i++)
		{
		  this.tabCasePlateau[0][i].setType(TypeCasePlateau.BLOQUE);
		  this.tabCasePlateau[Plateau.TAILLE_PLATEAU-1][i].setType(TypeCasePlateau.BLOQUE);
		  this.tabCasePlateau[0][i+6].setType(TypeCasePlateau.BLOQUE);
		  this.tabCasePlateau[Plateau.TAILLE_PLATEAU-1][i+6].setType(TypeCasePlateau.BLOQUE);
		}

		// Transformation des cases en porte
		this.tabCasePlateau[4][1].setType(TypeCasePlateau.PORTAIL_J1);
		this.tabCasePlateau[5][1].setType(TypeCasePlateau.PORTAIL_J1);
		this.tabCasePlateau[4][Plateau.TAILLE_PLATEAU-2].setType(TypeCasePlateau.PORTAIL_J2);
		this.tabCasePlateau[5][Plateau.TAILLE_PLATEAU-2].setType(TypeCasePlateau.PORTAIL_J2);
	}
	
	public static boolean estSurPlateau(int x, int y){
		if(y >= 0 && y < Plateau.TAILLE_PLATEAU
				&& x >= 0 && x < Plateau.TAILLE_PLATEAU)
			return true;
		else return false;
	}
	public void ajouterPion(List<Pion> listPion) {
		for(int i = 0; i< listPion.size(); i++)
		{
			this.tabCasePlateau[listPion.get(i).getX()][listPion.get(i).getY()].setPionCase(listPion.get(i));
		}
		
	}
	
	public CasePlateau getTabCasePlateauIndex(int x, int y) {
		if(x < TAILLE_PLATEAU && x >= 0 && y < TAILLE_PLATEAU && y >= 0)
			return this.tabCasePlateau[x][y];
		else
			return null;
	}
	
	public CasePlateau[][] getTabCasePlateau(){
		return this.tabCasePlateau;
	}
	
	public String toString() {
		String stringPlateau = new String();
		
		for(int y = 0; y < Plateau.TAILLE_PLATEAU; y++) 
		{
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++) 
			{
				if(this.tabCasePlateau[x][y].getPionCase() != null)
					stringPlateau += " "+this.tabCasePlateau[x][y].getPionCase().getPuissance().value; 
				
				else if(this.tabCasePlateau[x][y].getType() == TypeCasePlateau.NORMAL)
					stringPlateau += " 0";
				
				else if(this.tabCasePlateau[x][y].getType() == TypeCasePlateau.BLOQUE)
					stringPlateau += "  ";
				else if(this.tabCasePlateau[x][y].getType() == TypeCasePlateau.PORTAIL_J1
						|| this.tabCasePlateau[x][y].getType() == TypeCasePlateau.PORTAIL_J2 )
					stringPlateau += " P";
			}
			
			stringPlateau += "\n";
		}
		
		return stringPlateau;
	}

}
