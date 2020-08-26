/**
 * @author Lehasa Seoe (SXXLEH001)
 * 24 August 2020
 * This class is the sequentail implimentation of finding basins
 */
import java.util.*;
import java.io.*;
import java.lang.Math;


public class terrainBasin {
   public static float[][] terrain;
   public static int[][] isBasin;
   public static int rows;
   public static int columns;
   public static long startTime = 0;
	
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
	
    /**
     * This function searches for basins
     * @param float[][] arrayName
     */
    public static void neighbours(float[][] array){
        float currentValue;
        for(int i=1; i<(terrain.length-1); i++){
		      for(int j=1; j<(terrain[i].length-1) ; j++){
			      currentValue = (float) (0.01+terrain[i][j]);
			      if(array[i-1][j-1]>=currentValue && array[i-1][j]>=currentValue &&
			         array[i+1][j-1]>=currentValue && array[i-1][j+1]>=currentValue &&
			         array[i][j-1]>=currentValue && array[i+1][j+1]>=currentValue &&
			         array[i][j+1]>=currentValue && array[i+1][j]>=currentValue){
				      isBasin[i][j]=1;
			      }
            }
         }
    }
    
    /**
     * This function prints out the basins that were found
     */
    public static void print(){
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
    
     /**
     * This function runs the program and calls appropriate methods to do specific tasks
     * @param args It is an array of strings, it takes the input we type.
     * @throws this method will throw an exception when the file to be read is not found
     */

    public static void main(String[] args) throws Exception {
    //Reading in text file
      Scanner line = new Scanner(new BufferedReader(new FileReader("data/"+args[0])));
                
      String[] dimensions = line.nextLine().trim().split(" ");
      rows = Integer.parseInt(dimensions[0]);
      columns = Integer.parseInt(dimensions[1]);
                
      terrain = new float[rows][columns];
      isBasin = new int[rows][columns];
      String[] dataSet = line.nextLine().split(" ");
		
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
      neighbours(terrain);
      float time = tock();
      
      if(args.length==2){
      	PrintStream outputOne = new PrintStream(new File(args[1]));
      	System.setOut(outputOne);
      	print();
      }else if(args.length==1){
      	System.out.println(time);
      }
    }

}

