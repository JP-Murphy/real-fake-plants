package numericalmethods.odesolver;

public abstract class ODESolver {
	
	private ODEEquation equation;
	private double h;
	
	public ODESolver(ODEEquation equation, double h) {
		if(equation != null) 
			this.equation = equation;
		else 
			throw new CouldNotConstructObjectException("ODESolver");
		
		if(h <= 0) 
			throw new RuntimeException("Critical Error: The variable h must be above zero!");
		else 
			this.h = h;
	}
	
	public ODESolver(ODESolver source) {
		this.equation = source.equation.clone();
		this.h = source.h;
	}
	
	public void setODEEquation(ODEEquation equation) {
		if(equation != null) 
			this.equation = equation;
		else 
			throw new CouldNotConstructObjectException("ODESolver");
	}
	
	public ODEEquation getODEEquation() {
		return this.equation;
	}
	
	public void setH(double h) {
		if(h <= 0) 
			throw new RuntimeException("The variable h must be above zero!");
		else 
			this.h = h;
	}
	
	public double getH() {
		return this.h;
	}
	
	public String toString(){
		return "The equation :: " + this.equation.toString() + " | H :: " + this.h;
	}
	
	public boolean equals(Object source) {
	    if (source == null)
	      return false;
	    else if (source.getClass() != this.getClass())
	      return false;
	    else {
	      ODESolver object = (ODESolver)source;
	      return ((this.equation.equals(object.equation)) && (this.h == object.h));
	    }
	}
	
	public abstract ODESolver clone() throws CouldNotConstructObjectException;
	  
	public abstract double solveODE(double xInit, double yInit, double xFinal);
}
