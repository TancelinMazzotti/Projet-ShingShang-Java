package fr.shingshang.game.execption;

public class CaseBloqueException extends Exception{

	private static final long serialVersionUID = 277159463538431455L;
	
	public CaseBloqueException(){
		System.out.println("Case bloque");
	}
	public CaseBloqueException(int x, int y){
		System.out.println("Case ["+x+";"+y+"] bloque");
	}

}
