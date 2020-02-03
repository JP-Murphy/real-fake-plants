package reaction.reactionType; //package statement

public class NthOrderReaction extends Reaction{
  
   private double k; //Reaction rate constant dm^6/mol^2-min
   private double[] iConc; //Concentration array
   private double[] stoich; //Stoichiometry array
   private static int n; //Order of reaction
  
   /**
    * Constructor for the NthOrderReaction class
    * 
    * @param k      |reaction rate constant for the limiting reactant of this reaction
    * @param iConc  |concentration values for the chemical species within this reaction
    * @param stoich |stoichiometric coefficients for the species within this reaction
    *
    */
   public NthOrderReaction(double k, double[] iConc, double[] stoich){ 
     
      this.k=k; //Assign the reaction rate constant
      
      this.iConc = new double[iConc.length];// Begin deep copying of concentration array
      
      for(int i = 0;i<iConc.length;i++){
        
        this.iConc[i]=iConc[i]; 
        
      }
      this.stoich= new double[stoich.length]; // Begin deep copying of stoichiometry array
      
       for(int i = 0;i<stoich.length;i++){
        
        this.stoich[i]=stoich[i]; 
        
      }
       for(int i = 0;i<stoich.length;i++){// Begin determination of reaction order
         
         if(stoich[i]>0){
           this.n+=stoich[i]; //Since this reaction is elementary, the addition of reactant stoichiometric coefficients is equivalent to the order
         
         }
       }
   }
   
   /**
    * The copy constructor for the NthOrderReaction class
    * 
    * @param copy   |an NthOrderReaction with properties that are desired in this NthOrderReaction
    */
   public NthOrderReaction(NthOrderReaction copy){ 
     
      this.k=copy.k; //Assign the reaction rate constant
      
      this.iConc = new double[copy.iConc.length]; // Begin deep copying of concentration array
      
      for(int i = 0;i<copy.iConc.length;i++){
        
        this.iConc[i]=copy.iConc[i]; 
        
      }
      this.stoich= new double[copy.stoich.length]; // Begin deep copying of stoichiometry array
      
       for(int i = 0;i<copy.stoich.length;i++){
        
        this.stoich[i]=copy.stoich[i]; 
        
      }
   }
   
   /**
    * {@inheritDoc}
    * 
    * @return       |a new NthOrderReaction with the same properties as the one used to call the method
    */
   public NthOrderReaction clone(){
   
       return new NthOrderReaction(this); //Clones the current NthOrderReaction using the copy constructor
       
   }     
   
   /**
    * {@inheritDoc}
    * 
    * @return        |the reaction rate constant for the limiting reactant of this reaction
    */
   public double getK(){ //Reaction rate constant mutator
     
      return k;
      
   }
   
   /**
    * {@inheritDoc}
    * 
    * @param k        |the new reaction rate constant for the limiting reactant of this reaction
    * @return         |true if the reaction rate constant is updated
    */
   public boolean setK(double k){ //Reaction rate constant accessor
     
       this.k=k;
       return true;
       
   }
   
   /**
    * {@inheritDoc}
    * 
    * @return          |an array of stoichiometric coefficients for the chemical species in this reaction
    */
   public double[] getStoich(){ //Stoichiometry array accessor
     
     return this.stoich;
     
   }
   
   /**
    * {@inheritDoc}
    * 
    * @return          |an array of concentrations for the chemical species in this reaction
    */
   public double[] getiConc(){ //Concentration array accessor
     
     return this.iConc;
     
   }
   
   /**
    * {@inheritDoc}
    * 
    * @param newStoich  |the new stoichiometric coefficients for the chemical species in this reaction
    * @return           |true if the stoichiometric coefficient array is updated
    */
   public boolean setStoich(double[] newStoich){ //Stoichiometry array mutator
     
     System.out.println("Invalid operation; stoichiometry of reaction is static.");
     return false;
     
   }
   
   /**
    * {@inheritDoc}
    * 
    * @param newiConc  |the new concentrations for the chemical species in this reaction
    * @return          |true if the concentration array is updated
    */
   public boolean setiConc(double[] newiConc){ //Concentration array mutator
     double[] updateConc = new double[newiConc.length];
  
     for(int i = 0;i<newiConc.length;i++){
       updateConc[i] = newiConc[i];
     }
     
     this.iConc = updateConc;
     return true;
     
   }
   
   /**
    * Returns the String representation of an NthOrderReaction.
    * 
    * @return String  |the order of the reaction
    */
   public String toString(){
     
     String ordinalIndicator; //Indicates the letters that occur after a specified number
     switch(n){
       case 1: ordinalIndicator = "st "; //1st order reaction
       break;
       case 2: ordinalIndicator = "nd "; //2nd order reaction
       break;
       case 3: ordinalIndicator = "rd "; //3rd order reaction
       break;
       case 4: ordinalIndicator = "th "; //4th order reaction
       break;
       default: ordinalIndicator = "th "; //Default case for orders from 4 to 10
       break;
     }
 
     return " a " + n + ordinalIndicator + "order reaction.";
     
   }
   
   /**
    * Calculates the reaction rate for this reaction.
    * 
    * Overridden method from abstract Reaction class that utilizes the conversion and 
    * updates the concentration array values to determine the rate of reaction for the 
    * limiting reactant species.
    * 
    * @param x           |the conversion of the limiting reactant at this point in the reaction
    * @param parameters  |the flow parameters of the limiting reactant and the system
    * 
    * @return            |reaction rate at the specified conversion
    */
   public double calculateReactionRate(double x, double[] parameters){
     
     //Equivalent to Ca = (1-thetaB*X)*CbO, Cb = (1-X)*CbO, Cc = (thetaC*X)*CbO
     this.setiConc(new double[]{((1.-this.getStoich()[0]*x/this.getStoich()[1])*parameters[0])/parameters[1],parameters[0]*(1.-x)/parameters[1],(-this.getStoich()[2]*parameters[0]*x/this.getStoich()[1])/parameters[1]});
     
     //Equivalent to r = kB*Ca*Cb^2
     double val = k*Math.pow(this.getiConc()[0],this.stoich[0])*Math.pow(this.getiConc()[1],this.stoich[1]);
     
     return val;
     
    }   
   
  
  
}