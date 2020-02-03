import numericalmethods.odesolver.*;

public class MembraneReactor extends PlugFlowReactor implements ODEEquation{
  
  private Reaction r;
  private double[] parameters;
  private double rkB;
  
  public MembraneReactor(Reaction r, double[] parameters, double rkB){
    
    super(r, parameters);
    
    this.rkB=rkB;
    
  }
  
  public Reaction getR(){
    
    return this.r;
    
  }
  
  public void setR(Reaction newR){
   
    this.r = newR;
    
  }
  
  public double[] getParameters(){
    
    return this.parameters;
  
  }
  
  public double equation(double x, double y){
    
    double val = this.r.calculateReactionRate(y,new double[]{((1.-0.5*x)*15.)/120.,15.*(1.-x)/120.,(0.5*15.*x)/120.})*x/15.;
    
    return val;
    
  }
 
  public ODEEquation clone() throws CouldNotConstructObjectException{
    
    if(this==null){
      
      throw new CouldNotConstructObjectException("Sorry.");
      
    }else{
    
    return this;
    
  }
  }
}