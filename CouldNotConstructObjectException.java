package numericalmethods.odesolver;

public class CouldNotConstructObjectException extends RuntimeException {
	
	/* The following line is necessary to avoid eclipse errors */
	private static final long serialVersionUID = 1L; 

	public CouldNotConstructObjectException() {
		super("Critical Error: Could not construct object!"); 
	}
  
	public CouldNotConstructObjectException(String message) { 
		super("Critical Error: " + message + " object could not be constructed!");
	}

}
