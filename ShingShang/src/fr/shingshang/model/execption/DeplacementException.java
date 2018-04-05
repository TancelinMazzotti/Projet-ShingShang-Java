package fr.shingshang.model.execption;

public class DeplacementException extends Exception{

	private static final long serialVersionUID = -7930831626497609448L;
	
	public DeplacementException(){
		super("Deplacement impossible");
	}
	public DeplacementException(String causeException){
		super(causeException);
	}

}
