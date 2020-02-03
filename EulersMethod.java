package numericalmethods.odesolver;

public class EulersMethod extends ODESolver {
 
 public EulersMethod(ODEEquation equation, double h) {
  super(equation, h);
 }
 
 public EulersMethod(EulersMethod source) {
  super(source); 
 }
 
 /** The calling object MUST implement the ODEEquation interface */
 public double solveODE(double xInit, double yInit, double xFinal) {
  if(xInit >= xFinal) 
   throw new RuntimeException("Critical Error: The variable xInit must be greater than the variable xFinal!");
  
  double x = xInit;
  double y = yInit;
  double h = this.getH();
  int n=0;
  
  do {
    if(this.getH() > (xFinal - x)) {
    h = xFinal - x;
    }
    else{
    h = this.getH();
    }
   y += this.getODEEquation().equation(x, y)*h;
   x += h;
  } while(x < xFinal);
  return y;
 }
 
  public boolean equals(Object object) {
    if (!super.equals(object)) 
      return false;
    else 
      return this.getClass() == object.getClass();
  }
 
 public EulersMethod clone() throws CouldNotConstructObjectException {
  return new EulersMethod(this);
 }

}
