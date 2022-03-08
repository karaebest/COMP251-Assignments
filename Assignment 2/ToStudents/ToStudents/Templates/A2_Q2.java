package Templates;
import java.util.*;

public class A2_Q2 {

	public static int weight(int[] plates) {

		int[][] max = new int[plates.length+1][1001]; 
		int[] pl = Arrays.copyOf(plates, plates.length+1);
		int opt;	//hold absolute difference between w and opt of p-1 to 1
		int diffP; 	//holds absolute diff between w and plate p
		int optP;	//holds absolute diff between w and sum of plate p and opt sol of w - plate p for plates p-1 to 1
		
		Arrays.sort(pl);
		if(pl[plates.length]==1000) return 1000;

		for(int w=1; w<1001; w++){
			for(int p=1; p<pl.length;p++){

				opt = Math.abs(w - max[p-1][w]); 
				diffP = Math.abs(w - pl[p]);

				if(w==1){
					max[p][w]=pl[1]; continue;		// if max weight is 1, will always be smallest plate
				}

				if(pl[p]==w || max[p-1][w]==w){
					max[p][w] = w; continue;		// if plate = max weight, or optimal solution of plates p-1 to 1 = max weight, will be weight
				}
				
				if(p==1){
					max[p][w] = pl[p]; continue;	//if considering only first plate, opt can only be = to that plate
				}
				
				if(pl[p]>w && max[p-1][w]>w){ 		//if current plate p and opt sol. of p-1 to 1 is bigger than w, than opt is the smaller of the two
					max[p][w] = Math.min(pl[p], max[p-1][w]); continue;	//because can't subtract from plate p so will never be smaller
				}
				
				if(pl[p]>w && max[p-1][w]<w){		//if plate p is bigger than w and opt sol. p-1 to is is smaller than w, choose the one with the smallest diff to w
					max[p][w] = opt >= diffP ? pl[p] : max[p-1][w];	continue;
				}

				optP = Math.abs(w-pl[p]-max[p-1][w-pl[p]]);	//once we know w - plate p is not negative

				if(optP <= diffP && opt > optP){ 
					max[p][w] = pl[p]+max[p-1][w-pl[p]];
					if(max[p][w]==1000) return 1000;
				}

				else{
					max[p][w] = max[p-1][w];
					if(max[p][w]==1000) return 1000;
				}
			}
		}
		return max[plates.length][1000];
		
	}

}
