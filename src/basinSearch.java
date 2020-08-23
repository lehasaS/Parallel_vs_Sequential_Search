import java.util.concurrent.*;
import java.util.ArrayList;

public class basinSearch extends RecursiveAction{
     int lo; // arguments
	  int hi;
	  float[][] terrain;
     int[][] isBasin;
	  static final int SEQUENTIAL_CUTOFF=556;
     static String basins;
	    
	  basinSearch(float[][] t,int l, int h, int[][] b){ 
	    this.lo=l; 
       this.hi=h; 
       this.terrain=t;
       this.isBasin=b;
	  }
     
     protected void compute(){
      if((hi-lo) < SEQUENTIAL_CUTOFF){
         float currentValue;
	      for(int i=lo; i<hi; i++){
		      for(int j=1; j<(terrain[i].length)-1 ; j++){
			      currentValue = (float) (0.01+terrain[i][j]);
			      if(terrain[i-1][j-1]>=currentValue && terrain[i-1][j]>=currentValue &&
			         terrain[i+1][j-1]>=currentValue && terrain[i-1][j+1]>=currentValue &&
			         terrain[i][j-1]>=currentValue && terrain[i+1][j+1]>=currentValue &&
			         terrain[i][j+1]>=currentValue && terrain[i+1][j]>=currentValue){
				      isBasin[i][j]=1;
			      }
            }
         }
      }else{
         int mid =(hi+lo)/2;
         basinSearch left = new basinSearch(terrain, lo, mid, isBasin);
			basinSearch right= new basinSearch(terrain, mid , hi, isBasin);
			// order of next 4 lines
			// essential – why?
         left.fork();
			right.compute();
			left.join();
     }
}
}