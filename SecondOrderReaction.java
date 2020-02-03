public class SecondOrderReaction extends Reaction
{
   private double k;
       private double[] iConc;
     private double[] stoich;
  
   public SecondOrderReaction(double k)
   { 
      this.k=k;
      this.iConc = null;
      this.stoich =null;
   }
   
   //overload constructor to give copy constructor
   
   public SecondOrderReaction(SecondOrderReaction copy)
   { 
      this.k=copy.k;
      //if there were any arrays or other objects
      //as instance variables, we would need to provide
      //deep copying of these as well...
   }
   
   //override Object clone() method - note that the principle of covariants
   //applies here 
   public SecondOrderReaction clone()
   {
       //simply override the clone method to call the 
       //object's copy constructor
       return new SecondOrderReaction(this);
   }     
   
   //requisite accessor and mutator methods
   public double getK()
   {
      return k;
   }
   
   public void setK(double k)
   {
       this.k=k;
   }
      public double[] getStoich(){
     return this.stoich;
   }
   
   public double[] getiConc(){
     return this.iConc;
   }
   
   public void setStoich(double[] newStoich){
     this.stoich = newStoich;
   }
   
   public void setiConc(double[] newiConc){
     this.iConc = newiConc;
   }
   
   //overridden method - now concrete
   //in this case, array parameters has only one element representing
   //the inlet concentration of the limiting reactant
   public double calculateReactionRate(double x, double[] parameters)
   {
       return k*Math.pow(parameters[0]*(1-x),2);
    }   
   
   }//end of class