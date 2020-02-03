package numericalmethods.odesolver;// package statement

public class RK4_adaptive extends ODESolver{
  
public RK4_adaptive(ODEEquation equation, double h) {
  super(equation, h);
 }
 
 public RK4_adaptive(RK4_adaptive source) {
  super(source); 
 }
 
 /** The calling object MUST implement the ODEEquation interface */
 public double solveODE(double xInit, double yInit, double xFinal) {
  if(xInit >= xFinal) 
   throw new RuntimeException("Critical Error: The variable xInit must be greater than the variable xFinal!");
  
  double x = xInit;
  double y = yInit;
  double k1,k2,k3,k4;
  double h = this.getH();
  double old_y;
  int n=0;
  double tau;
  double tol = Math.pow(10,-6);
  
  do {
    double old_y1 = y;
    double old_y2 = y;
    
    if(this.getH() > (xFinal - x)) {
    h = xFinal - x;
    }
    else{
    h = this.getH();
    }

     old_y1 += this.getODEEquation().equation(x, y)*h;
     //System.out.println(old_y1);
     old_y2 += this.getODEEquation().equation(x, y)*h/2;
     old_y2 += this.getODEEquation().equation(x+1/2, old_y2)*h/2;
     //System.out.println(old_y2);
     tau = (old_y2 - old_y1);
     //System.out.println(tau);
     //h= h*Math.pow((tol/-tau),0.2);
     if(Math.abs(tau)<tol){
     //System.out.println(Math.abs(tau));
     h = 0.9*h*Math.min(Math.max(Math.sqrt(tol/(-2*tau)),0.3),2);
     //System.out.println(h);
     }
     //System.out.println(h);
     if(n%1 == 0){
       //System.out.println("h is " + h);
     }
    k1 = this.getODEEquation().equation(x, y)*h;
    k2 = this.getODEEquation().equation(x + h/2., y + k1/2.)*h;
    k3 = this.getODEEquation().equation(x + h/2., y + k2/2.)*h;
    k4 = this.getODEEquation().equation(x + h, y + k3)*h;
    
   y += (k1 + 2.*k2 + 2.*k3 + k4)/6.;

   x += h;
   n++;
   
   
  } while(x < xFinal);
  System.out.println("Number of iterations: " + n);
  System.out.println("Final step size: " + h);
  return y;
 }
 
  public boolean equals(Object object) {
    if (!super.equals(object)) 
      return false;
    else 
      return this.getClass() == object.getClass();
  }
 
 public RK4_adaptive clone() throws CouldNotConstructObjectException {
  return new RK4_adaptive(this);
 }

}
