package fr.shingshang.model.execption;

public class HorsPlateauException extends Exception {

	private static final long serialVersionUID = -7045976826400955778L;
	
	public HorsPlateauException(){
		super("Coordonnee hors plateau");
	}
	public HorsPlateauException(String axe, int valeur){
		super("Coordonnee hors plateau -> "+axe+"="+valeur);
	}
	public HorsPlateauException(int x, int y){
		super("Coordonnee hors plateau -> ["+x+";"+y+"]");
	}
}
