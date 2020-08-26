/**
 * @author Lehasa Seoe (SXXLEH001)
 * 24 August 2020
 * This class is the wrapper class for the parallel implementation of finding a basin
 */
import java.util.concurrent.ForkJoinPool;
import java.util.*;
import java.io.*;

public class findBasin{
   public static int rows;
   public static int columns;
   public static float[][] terrain;
   public static int[][] isBasin;
   public static String basinOutput;
   
   static long startTime = 0;
	
    /**
     * This function instatiates the start time
     */
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	
    /**
     * This function gets the time after execution is done
     */
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}

	static final ForkJoinPool fjPool = new ForkJoinPool();
	
    /**
     * This invokes the fork join pool and creates a basinSearch object
     * @param float[][] arrayName
     * @param int[][] arrayName
     */
   static void basinFind(float[][] terrain, int[][] isBasin){
	   fjPool.invoke(new basinSearch(terrain,1,terrain.length-1,isBasin));
	}
   
   /**
     * This function runs the program and calls appropriate methods to do specific tasks
     * @param args It is an array of strings, it takes the input we type.
     * @throws this method will throw an exception when the file to be read is not found
     */
   public static void main(String[] args) throws FileNotFoundException{
      //Reading in text file
      Scanner line = new Scanner(new BufferedReader(new FileReader("data/"+args[0])));
                
      String[] dimensions = line.nextLine().trim().split(" ");
      rows = Integer.parseInt(dimensions[0]);
      columns = Integer.parseInt(dimensions[1]);
            
      terrain = new float[rows][columns];
      isBasin = new int[rows][columns];
      String[] dataSet = line.nextLine().split(" ");
		//Populating arrays
	   int arrayCounter=0;
		for(int i=0; i<rows; i++){
		   for(int j=0; j<columns; j++){
			   terrain[i][j] = Float.parseFloat(dataSet[arrayCounter]);
            isBasin[i][j] = 0;
			   arrayCounter++;
		   }
	   }
	System.gc();
      tick();
      basinFind(terrain, isBasin);
      float time = tock();
      
      /*int maxThreadAmount = Runtime.getRuntime().availableProcessors();
      System.out.println("Number of threads: "+ maxThreadAmount);*/ /*I used this block
      of code to determine the optimal number of threads*/
      
      if(args.length==2){
      	PrintStream outputOne = new PrintStream(new File(args[1])); 
      	System.setOut(outputOne);
      	printBasins();
      }else if(args.length==1){
      	System.out.println(time);
      }else{
      	System.out.println("Invalid parameters!! Input <data file name> <output file name>");
      }
      
   }
   
   public static void printBasins(){
      String basins="";
      int count=0;
      for(int i=1; i<rows-1; i++){
	for(int j=1; j<columns-1; j++){
            if(isBasin[i][j]==1){
               basins+=i+" "+j+"\n";
               count++;
            }
         }
     }
     System.out.println(count);
     System.out.println(basins.substring(0,basins.length()-1));
   }

}
