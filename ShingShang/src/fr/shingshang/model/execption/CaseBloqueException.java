package fr.shingshang.model.execption;

public class CaseBloqueException extends Exception{

	private static final long serialVersionUID = 277159463538431455L;
	private static String message;
	
	public CaseBloqueException(){
		message = "Case bloque";
	}
	public CaseBloqueException(int x, int y){
		message = "Case ["+x+";"+y+"] bloque";
	}
	
	public String toString(){
		return message;
	}

}
