package fr.shingshang.model.execption;

public class CaseBloqueException extends Exception{

	private static final long serialVersionUID = 277159463538431455L;
	
	public CaseBloqueException(){
		super("Case bloque");
	}
	public CaseBloqueException(int x, int y){
		super("Case ["+x+";"+y+"] bloque");
	}

}
