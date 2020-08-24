import java.util.*;
import java.io.*;
import java.lang.Math;


public class terrainBasin {
   public static float[][] terrain;
   public static int[][] isBasin;
   public static int rows;
   public static int columns;
   public static long startTime = 0;
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
        
   public static void makeGrid() throws FileNotFoundException{

  
   }

   
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
    
    public static void print(){
      String basins="";
      int count=0;
      for(int i=0; i<rows; i++){
		   for(int j=0; j<columns; j++){
            if(isBasin[i][j]==1){
               basins+=i+" "+j+"\n";
               count++;
            }
         }
     }
     System.out.println(count);
     System.out.println(basins.substring(0,basins.length()-1));
    }

    public static void main(String[] args) throws Exception {
    //Reading in text file
      Scanner line = new Scanner(new BufferedReader(new FileReader(args[0])));
                
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

      tick();
      neighbours(terrain);
      float time = tock();
      
      if(args.length==2){
      	PrintStream outputOne = new PrintStream(new File(args[1]));
      	System.setOut(outputOne);
      	print();
      }else if(args.length==1){
      	System.out.println("Run took "+ time +" seconds");
      }
    }

}

