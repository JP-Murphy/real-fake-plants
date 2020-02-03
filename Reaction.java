package reaction.reactionType; //package statement

public abstract class Reaction{
  
   /**
    * Used to create a copy of the calling Reaction object
    * 
    * @return     |a Reaction with the same properties as the current 
    */
  public abstract Reaction clone();
  
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
  public abstract double calculateReactionRate(double x, double[] parameters);
  
   /**
    * Returns the current concentrations for the chemical species of this reaction
    * 
    * @return          |an array of concentrations for the chemical species in this reaction
    */
  public abstract double[] getiConc();
    
   /**
    * Updates the concentration for the chemical species of this reaction
    * 
    * @param newiConc  |the new concentrations for the chemical species in this reaction
    * @return          |true if the concentration array is updated
    */
  public abstract boolean setiConc(double [] conc);
  
   /**
    * Returns the current stoichiometric coefficients for the chemical species in this reaction
    * 
    * @return          |an array of stoichiometric coefficients for the chemical species in this reaction
    */
  public abstract double[] getStoich();
  
   /**
    * Updates the stoichiometric coefficients for the chemical species of this reaction
    * 
    * @param newStoich  |the new stoichiometric coefficients for the chemical species in this reaction
    * @return           |true if the stoichiometric coefficient array is updated
    */
  public abstract boolean setStoich(double[] stoich);

   /**
    * Returns the reaction rate constant for the limiting reactant of this reaction
    * 
    * @return        |the reaction rate constant for the limiting reactant of this reaction
    */
   public abstract double getK();//Reaction rate constant mutator

   /**
    * Updates the reaction rate constant for the limiting reactant of this reaction
    * 
    * @param k        |the new reaction rate constant for the limiting reactant of this reaction
    * @return         |true if the reaction rate constant is updated
    */
   public abstract boolean setK(double k); //Reaction rate constant accessor
}