package reactor.reactorType; //package statement

import reaction.reactionType.*; //import statement

public abstract class Reactor{
  
    private Reaction r; //the reaction associated with this reactor; in version 2.0 this will most likely be an array of Reactions
    private double[] parameters; //the flow parameters associated with this reactor
   
   /**
    * Constructor for the Reactor class
    * 
    * @param r      |reaction associated with this reactor
    */
    public Reactor(Reaction r, double[] parameters){
      
      if(r == null || parameters == null){
        
        System.out.println("Invalid input.");
        
        System.exit(0);
        
      }
      
      this.r = r.clone();
      
      this.parameters = new double[parameters.length];
        
      for(int i = 0; i<parameters.length;i++){
        
        this.parameters[i] = parameters[i];
        
      }
       
    } 
    
   /**
    * The copy constructor for the Reactor class
    * 
    * @param copy   |a Reactor object with properties that are desired in this Reactor
    */
    public Reactor(Reactor original){
      
      if(original == null || original.getReaction() == null || original.getParameters() == null){
        
        System.out.println("Invalid input.");
        
        System.exit(0);
        
      }
      
      this.r=original.r.clone();
      
      this.parameters = new double[original.getParameters().length];
        
      for(int i = 0; i<original.getParameters().length;i++){
        
        this.parameters[i] = original.getParameters()[i];
        
      }
      
    }
    
   /**
    * Returns the reaction of this reactor
    * 
    * @return        |the reaction for this reactor
    */
    public Reaction getReaction(){
      
        return this.r; //by returning a clone the concentration values become disconnected with the reaction
        
    }
    
   /**
    * Returns the flow parameters of this reactor
    * 
    * @return        |the flow parameters for this reactor
    */
    public double[] getParameters(){
      
      return this.parameters;
       
    }
  
   /**
    * Updates the flow parameters of this reactor
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
    * Abstract method for cloning a PlugFlowReactor
    */
    public abstract PlugFlowReactor clone(); 
  
}