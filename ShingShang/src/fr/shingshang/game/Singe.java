package fr.shingshang.game;

import java.util.ArrayList;
import java.util.List;

import fr.shingshang.game.enumeration.PuissancePion;
import fr.shingshang.game.execption.CaseBloqueException;
import fr.shingshang.game.execption.DeplacementException;
import fr.shingshang.game.execption.HorsPlateauException;

public class Singe extends Pion{
	private static final long serialVersionUID = 8064448209846163754L;

	public Singe(CasePlateau casePlateau, Joueur joueur) {
		super(casePlateau, joueur);
		this.puissance = PuissancePion.SINGE;
		this.nom = "Singe";
	}

	@Override
	public List<Deplacement> listDeplacementPossible(Plateau plateau) {
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
				
				try {
					CasePlateau caseProche = plateau.getTabCasePlateauIndex(x,y);
					// On essaie de voir si un deplacement simple est possible
					if(caseProche.getPionCase() == null && !caseProche.estUnPortail())
						listDeplacement.add(new Deplacement(this.casePlateau,caseProche,false,null));
					
					try{
						CasePlateau caseDistant = plateau.getTabCasePlateauIndex(xSaut,ySaut);
						// On essaie de voir si un deplacement double est possible
						if(caseProche.getPionCase() == null && caseDistant.getPionCase() == null && !caseDistant.estUnPortail())
							listDeplacement.add(new Deplacement(this.casePlateau,caseDistant,false,null));
						// On essaie de voir si un saut est possible
						else if(caseProche.getPionCase() != null && caseDistant.getPionCase() == null && !caseDistant.estUnPortail())
							listDeplacement.add(new Deplacement(this.casePlateau,caseDistant,true,caseProche));
						}
					catch (HorsPlateauException | CaseBloqueException | DeplacementException e2) {
						e2.printStackTrace();
					}	
				}
				catch (HorsPlateauException | CaseBloqueException | DeplacementException e) {
					e.printStackTrace();
				}
			}
		}
		return listDeplacement;
	}
}
