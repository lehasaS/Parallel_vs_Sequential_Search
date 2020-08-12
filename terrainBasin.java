import java.util.*;
import java.io.*;
import java.lang.Math;


public class terrainBasin {
        public static float[][] terrain;
        public static float[] neighbour;
        public static int row;
        public static int column;
        public static void makeGrid() throws Exception{
            try{
                //Reading in text file
                File file = new File("med_in.txt");
                Scanner line = new Scanner(new BufferedReader(new FileReader(file)));
                String[] dimensions = line.nextLine().trim().split(" ");
                //System.out.println(Arrays.toString(dimensions));
                row = Integer.parseInt(dimensions[0]);
                column = Integer.parseInt(dimensions[0]);
		//System.out.println(Arrays.toString(dimensions));
                terrain = new float[row][column];

                //creating the terrain matrix
                while(line.hasNextLine()){
                    for(int i=0; i<row; i++){
                        String[] lines = line.nextLine().trim().split(" ");
                        //System.out.println(Arrays.toString(lines));
                        for(int j=0; j<column; j++){
                            terrain[i][j] = Float.parseFloat(lines[j]);
                        }
                    }
                }
            }
            catch(Exception e){
            }

            //System.out.println(Arrays.toString(terrain));
        }

   
    public static void neighbours(float[][] array){
    int count = 0;
	for(int i=1; i<array.length-1; i++){
		for(int j=1; j<array[i].length-1; j++){
			if(array[i-1][j-1]>=0.01+array[i][j] && array[i-1][j]>=0.01+array[i][j] &&
			array[i][j-1]>=0.01+array[i][j] && array[i+1][j+1]>=0.01+array[i][j] &&
			array[i+1][j-1]>=0.01+array[i][j] && array[i-1][j+1]>=0.01+array[i][j] &&
			array[i][j+1]>=0.01+array[i][j] && array[i+1][j]>=0.01+array[i][j]){
				count++;
				System.out.println(i + " " + j);
			}
			
			
		}
	}
	System.out.println(count);
    }

        public static void main(String[] args) throws Exception {
            makeGrid();
	    neighbours(terrain);
        }


}

