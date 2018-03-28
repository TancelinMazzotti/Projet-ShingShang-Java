package fr.shingshang.model.execption;

public class DeplacementException extends Exception{

	private static final long serialVersionUID = -7930831626497609448L;
	private static String message;
	
	public DeplacementException(){
		message = "Deplacement impossible";
	}
	public DeplacementException(String causeException){
		message = causeException;
	}
	
	public String toString(){
		return message;
	}

}
