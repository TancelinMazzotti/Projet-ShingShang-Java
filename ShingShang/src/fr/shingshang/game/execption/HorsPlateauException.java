package fr.shingshang.game.execption;

public class HorsPlateauException extends Exception {

	private static final long serialVersionUID = -7045976826400955778L;
	public HorsPlateauException(){
		System.out.println("Coordonne hors plateau");
	}
	public HorsPlateauException(String axe, int valeur){
		System.out.println("Coordonne hors plateau => "+axe+"="+valeur);
	}
	public HorsPlateauException(int x, int y){
		System.out.println("Coordonne hors plateau => X="+x+" & Y="+y);
	}
}
