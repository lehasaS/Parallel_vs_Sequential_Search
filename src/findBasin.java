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
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}

	static final ForkJoinPool fjPool = new ForkJoinPool();
	
   static void basinFind(float[][] terrain, int[][] isBasin){
	   fjPool.invoke(new basinSearch(terrain,1,terrain.length-1,isBasin));
	}
   
   public static void main(String[] args) throws FileNotFoundException{
      //Reading in text file
      Scanner line = new Scanner(new BufferedReader(new FileReader(args[0])));
                
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
      tick();
      basinFind(terrain, isBasin);
      float time = tock();
      
      if(args.length==2){
      	PrintStream outputOne = new PrintStream(new File(args[1])); 
      	System.setOut(outputOne);
      	printBasins();
      }else if(args.length==1){
      	System.out.println("Run took "+ time +" seconds");
      }
      
   }
   
   public static void printBasins(){
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

}
