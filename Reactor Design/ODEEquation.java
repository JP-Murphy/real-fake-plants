package numericalmethods.odesolver;

public interface ODEEquation {
 
 public double equation(double x, double y);
 
 public ODEEquation clone() throws CouldNotConstructObjectException;


}
