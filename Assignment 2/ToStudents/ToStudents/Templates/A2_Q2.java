package Templates;
import java.util.*;

public class A2_Q2 {

	//TO DO if not working -> make sure all int/Integer values correct + valueOf() and intValue()
	// public static int opt(int[] plates, int j, int d, int[][] memOpt ){	//returns sum of weights

	// 	if(j==0) return 0;
	// 	if(j==1){
	// 		memOpt
	// 		return plates[1]; //to not get null value at 0 from memOpt
	// 	}
	// 	int toCompare; 			//TO DO -> change hashMap value to Hash 

	// 	if(d.compareTo(Integer.valueOf(1000))!=0){	//can't assume memOpt has answer
	// 		if(memOpt.get(j))
	// 	}else{
	// 		toCompare = memOpt.get(j-1).get(d);
	// 		if((Math.abs(d - plates[j] - toCompare) < Math.abs(d - toCompare)) && Math.abs(d - plates[j] - toCompare) < Math.abs(d - plates[j])){
	// 			return (plates[j]+toCompare);
	// 		}else{	//check first for memOpt.get(j-1).contains(d-plates[j])
	// 			return Math.max(toCompare, plates[j] + opt(plates, j-1, d-plates[j], memOpt));
	// 		}
	// 	}

	// 	return 0;
	// }

	public static int weight(int[] plates) {

		int[][] max = new int[plates.length+1][1001]; 
		int[] pl = Arrays.copyOf(plates, plates.length+1);

		
		Arrays.sort(pl);
		if(pl[plates.length]==1000) return 1000;

		for(int w=1; w<1001; w++){
			for(int p=1; p<pl.length;p++){

				if(pl[p]==w || max[p-1][w]==w){
					max[p][w] = w;
				}else if(p==1){
					max[p][w] = pl[p];
				}else if(pl[p]>w && max[p-1][w]>w){ 
					max[p][w] = Math.min(pl[p], max[p-1][w]);	//both over w then choose smaller
				}else if(pl[p]>w && max[p-1][w]<w){		//p larger and max smaller
					max[p][w] =(Math.abs(w - max[p-1][w]) >= Math.abs(w - pl[p]))?pl[p]:max[p-1][w];
				}else if(Math.abs(w-pl[p]-max[p-1][w-pl[p]]) < Math.abs(w-pl[p]) && Math.abs(w - max[p-1][w]) > Math.abs(w - pl[p]-max[p-1][w-pl[p]])){
					max[p][w] = pl[p]+max[p-1][w-pl[p]];
					if(max[p][w]==1000) return 1000;
				}
				// else if((Math.abs(w - pl[p] - max[p-1][w]) < Math.abs(w - max[p-1][w])) && Math.abs(w - pl[p] - max[p-1][w]) < Math.abs(w - pl[p])){
				// 	max[p][w] = pl[p] + max[p-1][w];
				// 	if(max[p][w]==1000) return 1000;
				// }else if (Math.abs(w - max[p-1][w])<=Math.abs(w - pl[p] - max[p-1][Math.abs(w-pl[p])])){	//if equal find max
				// 	max[p][w] = max[p-1][w];
				//}
				else{
					max[p][w] = max[p-1][w];
					System.out.println(max[p][w] + " p:"+p+" w: "+ w);
					if(max[p][w]==1000) return 1000;
				}
			}
		}
	
		
		return max[plates.length][1000];
		
	}

}
