package fr.shingshang.model.plateau;


import java.io.Serializable;
import java.util.List;

import fr.shingshang.model.enumeration.TypeCasePlateau;
import fr.shingshang.model.execption.CaseBloqueException;
import fr.shingshang.model.execption.HorsPlateauException;
import fr.shingshang.model.pion.Pion;

public class Plateau implements Serializable {
	private static final long serialVersionUID = -2056000719242757639L;
	public static final int TAILLE_PLATEAU = 10;
	private CasePlateau tabCasePlateau[][];
	private CasePlateau portail1J1;
	private CasePlateau portail2J1;
	private CasePlateau portail1J2;
	private CasePlateau portail2J2;
	
	public Plateau() {
		// Creation et initialisation de tabCasePlateau
		this.tabCasePlateau = new CasePlateau[TAILLE_PLATEAU][TAILLE_PLATEAU];
		for(int y = 0; y < Plateau.TAILLE_PLATEAU; y++)
		{
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++)
			{
				try {
					this.tabCasePlateau[x][y] = new CasePlateau(x,y);
				} catch (HorsPlateauException e) {
					e.printStackTrace();
				}
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
		this.setPortail1J1(this.tabCasePlateau[4][1]);
		this.tabCasePlateau[5][1].setType(TypeCasePlateau.PORTAIL_J1);
		this.setPortail2J1(this.tabCasePlateau[5][1]);
		this.tabCasePlateau[4][Plateau.TAILLE_PLATEAU-2].setType(TypeCasePlateau.PORTAIL_J2);
		this.setPortail1J2(this.tabCasePlateau[4][Plateau.TAILLE_PLATEAU-2]);
		this.tabCasePlateau[5][Plateau.TAILLE_PLATEAU-2].setType(TypeCasePlateau.PORTAIL_J2);
		this.setPortail2J2(this.tabCasePlateau[5][Plateau.TAILLE_PLATEAU-2]);
	}
	
	public static boolean estSurPlateau(int x, int y){
		if(y >= 0 && y < Plateau.TAILLE_PLATEAU
				&& x >= 0 && x < Plateau.TAILLE_PLATEAU)
			return true;
		else return false;
	}
	public void ajouterPion(List<Pion> listPion) {
		for(int i = 0; i < listPion.size(); i++)
		{
			this.tabCasePlateau[listPion.get(i).getX()][listPion.get(i).getY()].setPionCase(listPion.get(i));
		}
		
	}
	
	public CasePlateau getTabCasePlateauIndex(int x, int y) throws HorsPlateauException, CaseBloqueException{
		if(x < 0 || x >= TAILLE_PLATEAU || y < 0 || y >= TAILLE_PLATEAU )
			throw new HorsPlateauException(x,y);
		else if(this.tabCasePlateau[x][y].getType() == TypeCasePlateau.BLOQUE)
			throw new CaseBloqueException(x,y);
		
		return this.tabCasePlateau[x][y];
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

	public CasePlateau getPortail1J1() {
		return portail1J1;
	}

	public void setPortail1J1(CasePlateau portail1j1) {
		portail1J1 = portail1j1;
	}

	public CasePlateau getPortail2J1() {
		return portail2J1;
	}

	public void setPortail2J1(CasePlateau portail2j1) {
		portail2J1 = portail2j1;
	}

	public CasePlateau getPortail1J2() {
		return portail1J2;
	}

	public void setPortail1J2(CasePlateau portail1j2) {
		portail1J2 = portail1j2;
	}

	public CasePlateau getPortail2J2() {
		return portail2J2;
	}

	public void setPortail2J2(CasePlateau portail2j2) {
		portail2J2 = portail2j2;
	}

}
