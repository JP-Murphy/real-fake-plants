import numericalmethods.odesolver.*; //import statement
import java.util.*; //import statement

  /**
   * Driver Class for the 2019 CHG4343 Project
   * @author Group 23 (Rim, Sasha, Eduardo, Jaden)
   * @version 1.0
   */
public class Group23_Driver_LosPollos{
  
  public static void main(String[] args){
    
    System.out.println("Testing main method Los Pollos.\n");
    
    //For now this program can only handle reactions in the form of xA+yB->zC
    System.out.println("Please enter the reaction with stoichiometry (e.g A + 2B -> C == 1 2 -1):");
    Scanner scanner = new Scanner(System.in);
    int species1 = scanner.nextInt();
    int species2 = scanner.nextInt();
    int species3 = -scanner.nextInt();
    System.out.println(species1+ "A + " + species2 + "B -> " + species3 + "C");
    
    System.out.println("\nPlease enter the reaction constant (units assumed as dm^6/mol^2-min):");
    double kA = scanner.nextDouble(); // dm^6/mol^2-min
    System.out.println("kA = " + kA + " dm^6/mol^2-min");
    
    System.out.println("\nPlease enter the reactor flow rate (units assumed as dm^3/min):");
    double vO = scanner.nextDouble(); // dm^3/min
    System.out.println("vO = " + vO + " dm^3/min");
    
    System.out.println("\nPlease enter the flow rate for the first reactant (units assumed as mol/min):");
    double FaO = scanner.nextDouble(); // mol/min
    System.out.println("FaO = " + FaO + " mol/min");
    
    System.out.println("\nPlease enter the flow rate for the second reactant (units assumed as mol/min):");
    double FbO = scanner.nextDouble(); // mol/min
    System.out.println("FbO = " + FbO + " mol/min");
    
    System.out.println("\nPlease enter the reactor volume (units assumed as dm^3):");
    double V = scanner.nextDouble(); // dm^3
    System.out.println("V = " + V + " dm^3");
    
    //Initialize conversion, volume, and rate variables
    double x=0.;
    double v =0.,rate =0.;
    
    //Initialize initial concentrations and assign values to current concentrations
    double CaO,CbO,CcO;
    CaO = FaO/V;
    CbO = FbO/V;
    CcO=0.0;
    double Ca = CaO;
    double Cb = CbO;
    double Cc = CcO;
    
    //Create storage arrays for species, initial concentrations, and stoichiometry
    String[] species = new String[]{"A","B","C"};
    double[] iConc = new double[]{CaO,CbO,CcO};
    double[] stoich= new double[]{species1,species2,species3};
    
    double var=0;
    
    //Indicate if another method is to be used to determine reactor conversion
    int doWhileTest = 1;
    
    //Set step size (fixed for now) for the numerical methods
    double h = 0.05;
    
    System.out.println("\n-----------------------------------------------------------------");
    System.out.println("-----------------------------------------------------------------");
    System.out.println("Start of Program.");
    System.out.println("-----------------------------------------------------------------");
    
    //determine the limiting reactant for this reaction set
    System.out.println("\nDetermining the limiting reactant for this reaction.\n");
    
    //Initialize variables for limiting reactant determination
    double min = 10000;
    int index=-1;
    
    for(int i = 0; i < iConc.length; i++){
      
      var = iConc[i]/stoich[i]; //set var equal to the initial concentration over stoichiometric coefficient
      
      if(var<min && var>0){ //test if var is less than the current minimum ratio
       
        min = var; //if var<min, set min = var, and store the index where that ratio occurs
        
        index = i;
        
      }else if(var==min){ //if var==min for all values then all reactants are consumed at the same rate
       
        index = -1; //set index to negative
        
      }
      
    }
    
    if(index!=-1){ //if index is non-negative and there is a limiting reactant
      
      System.out.println("The limiting reactant in this reaction is " + species[index] + ".");
      
    }else{
      
      System.out.println("All reactant species have equivalent concentrations and stoichiometry; using species A as a basis.");
      
      index = 0;
      
    }
    
    System.out.println("\nCreating array for flow parameters of system.");
    
    //Creating an array to hold the flow rate of the system and the limiting reactant
    double[] flowParameters = new double[]{iConc[index]*V,vO};
    
    System.out.println("\nCreating an nth order reaction using the assumption of an elementary rate law.\n");
    NthOrderReaction rxn1 = new NthOrderReaction(stoich[index]*kA/stoich[index-1], new double[]{CaO,CbO,0.},new double[]{species1,species2,-species3});
    System.out.println("This is" + rxn1);
    
    System.out.println("\nCreating a plug flow reactor using the nth order reaction and the initial concentrations.\n");
    PlugFlowReactor reactor1 = new PlugFlowReactor(rxn1, flowParameters);
    System.out.println(reactor1);
    
    System.out.println("\n-----------------------------------------------------------------");
    System.out.println("Calculated using Euler's Method.");
    System.out.println("-----------------------------------------------------------------");
    System.out.println("Solving ODE using Euler's Method with intial step size of " + h+".");
    
    EulersMethod eum = new EulersMethod(reactor1, 0.5);
    long eulerStart = System.nanoTime();
    double convX = eum.solveODE(0.,0.,600.)*100;
    long eulerStop = System.nanoTime();
    
    System.out.println("\nFinal conversion achieved.");
    System.out.println("Time to solve: " + (eulerStop-eulerStart)/(Math.pow(10,6)) + " ms");
    System.out.printf("\nConversion of A is %.2f%s %n",convX/2, "%");
    System.out.printf("Conversion of B is %.2f%s %n",convX, "%");
    System.out.printf("%nExiting Fa is %.2f%s%nExiting Fb is %.2f%s%nExiting Fc is %.2f%s%n", rxn1.getiConc()[0]*vO, " mol/min", rxn1.getiConc()[1]*vO, " mol/min", rxn1.getiConc()[2]*vO, " mol/min");
    System.out.println("-----------------------------------------------------------------");
    
    if(doWhileTest == 1){
      System.out.println("Calculated using a do-while loop with fixed step size of "+h+".");
      System.out.println("-----------------------------------------------------------------");
      long doWhileStart = System.nanoTime();
      System.out.println("Solving ODE iteratively using a do-while loop.\n");
      
      do{
    rate = rxn1.calculateReactionRate(x, flowParameters);
    v+=h;
    x+=rate*h/FbO;
    Ca = ((1.-0.5*x)*FbO)/vO;
    Cb = FbO*(1.-x)/vO;
    Cc = (0.5*FbO*x)/vO;
    rxn1.setiConc(new double[]{Ca,Cb,Cc});
    }while(v<V);
    long doWhileStop = System.nanoTime();
    
    System.out.println("Final conversion achieved.");
    System.out.println("Time to solve: " + (doWhileStop-doWhileStart)/(Math.pow(10,6)) + " ms");
    System.out.printf("\nConversion of A is %.2f%s %n",((FaO-Ca*vO)/FaO)*100, "%");
    System.out.printf("Conversion of B is %.2f%s %n",((FbO-Cb*vO)/FbO)*100, "%");
    System.out.printf("%nExiting Fa is %.2f%s%nExiting Fb is %.2f%s%nExiting Fc is %.2f%s%n", Ca*vO, " mol/min", Cb*vO, " mol/min", Cc*vO, " mol/min");
    System.out.println("-----------------------------------------------------------------");
    System.out.println("End of Program.");
    System.out.println("-----------------------------------------------------------------");
    System.out.println("-----------------------------------------------------------------");
    }
  }
  
}