package fr.shingshang.model.execption;

public class HorsPlateauException extends Exception {

	private static final long serialVersionUID = -7045976826400955778L;
	private static String message;
	
	public HorsPlateauException(){
		message = "Coordonnee hors plateau";
	}
	public HorsPlateauException(String axe, int valeur){
		message = "Coordonnee hors plateau -> "+axe+"="+valeur;
	}
	public HorsPlateauException(int x, int y){
		message = "Coordonnee hors plateau -> ["+x+";"+y+"]";
	}
	
	public String toString(){
		return message;
	}
}
