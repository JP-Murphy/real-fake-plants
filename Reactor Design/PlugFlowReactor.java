package reactor.reactorType; //package statement

import reaction.reactionType.*; //import statement
import numericalmethods.odesolver.*; //import statement

public class PlugFlowReactor extends Reactor implements ODEEquation{
  
  private Reaction r; //the reaction associated with this reactor; in version 2.0 this will most likely be an array of Reactions
  private double[] parameters; //the flow parameters associated with this reactor
  
   /**
    * Constructor for the Reactor class
    * 
    * @param r      |reaction associated with this reactor
    */
  public PlugFlowReactor(Reaction r, double[] parameters){

    super(r,parameters);
    
    this.r=r;
    
    this.parameters = new double[parameters.length];
    
    for(int i = 0;i<parameters.length;i++){
      
      this.parameters[i] = parameters[i];
      
    }
    
  }
   /**
    * The copy constructor for the Reactor class
    * 
    * @param copy   |a Reactor object with properties that are desired in this Reactor
    */
    public PlugFlowReactor(PlugFlowReactor copy){
    
    super(copy.getReaction(),copy.getParameters());
    
    this.r=copy.r.clone();
    
    this.parameters = new double[copy.parameters.length];
    
    for(int i = 0;i<copy.parameters.length;i++){
      
      this.parameters[i] = copy.parameters[i];
      
    }
    
  }
       
   /**
    * {@inheritDoc}
    * 
    * @return        |a copy of the current PlugFlowReactor object
    */
    public PlugFlowReactor clone(){
      
      return new PlugFlowReactor(this);
      
    }

   /**
    * {@inheritDoc}
    * 
    * @return        |the reaction for this reactor
    */
  public Reaction getReaction(){
    
    return this.r; //by returning a clone the concentration values become disconnected with the reaction
    
  }
  
   /**
    * {@inheritDoc}
    * 
    * @return        |true if the reaction of this reactor has been updated
    */
  public boolean setReaction(Reaction newR){
   
    if(newR == null){
      
      System.out.println("Invalid input");
      
      System.exit(0);
      
    }
      
    this.r = newR.clone();
    
    if(this.getReaction() == newR){
      
      return true;
    
    }else{
      
      return false;
      
    }
    
  } 

   /**
    * {@inheritDoc}
    * 
    * @return        |the flow parameters for this reactor
    */
  public double[] getParameters(){
    
    return this.parameters;
  
  }
  
   /**
    * {@inheritDoc}
    * 
    * @return        |true if the flow parameters of this reactor have been updated
    */
  public boolean setParameters(double[] params){
      
      if(params ==null){
        
        System.out.println("Invalid input.");
        
        System.exit(0);
        
      }
       
      this.parameters= new double[params.length];
      
      for(int i = 0;i<params.length;i++){
        
        this.parameters[i] = params[i];
        
      }
      
      return true;
 
    }
  
   /**
    * Generates the ODE for the change in conversion with respect to volume
    * 
    * @param x   |the current volume of the reactor
    * @param y   |the current conversion of the reactor
    * 
    * @return    |the current change in conversion with respect to the current volume
    */
  public double equation(double x, double y){

    //Equivalent to dX/dV = r/FbO
    double val = this.getReaction().calculateReactionRate(y, this.getParameters())/this.getParameters()[0];
    return val;
    
  }
   
   /**
    * Produces a string representation of a PlugFlowReactor
    * 
    * @return    |the string representation of this PlugFlowReactor
    */
  public String toString(){
    
    return "This is a plug flow reactor with" + this.getReaction();
    
  }
  
   /**
    * Tests the equivalency of two PlugFlowReactors
    * 
    * @param other   |a PlugFlowReactor
    * 
    * @return        |true if the two PlugFlowReactor objects are the same
    */
  public boolean equals(PlugFlowReactor other){
    
    if(other == null){
      
      System.out.println("Not a valid input.");
      System.exit(0);
      
    }
    if(other.getClass()!=this.getClass()){
      
      return false;
      
    }else if(other.getReaction()!=this.getReaction() || other.getParameters()!=this.getParameters()){
      
      return false;
      
    }else{
      
      return true;
      
    }
  }
 
   /**
    * 
    * 
    * 
    *//*
  public ODEEquation clone() throws CouldNotConstructObjectException{
    
    if(this==null){
      
      throw new CouldNotConstructObjectException("Sorry.");
      
    }else{
    
    return this;
    
  }
  }*/
  
}