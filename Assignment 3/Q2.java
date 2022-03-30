import java.util.*;


public class Q2 {
	
	public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {

		return jumps(TS(graph), location);
	}

	public static int[][] TS(Hashtable<Integer, ArrayList<Integer>> graph){	//returns array of arrays of [sorted keys, in degrees, out degrees]

		int[] in = new int[graph.size()+1];
		int[] out = new int[graph.size()+1];
		int[] inTemp = new int[graph.size()+1];	
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.addAll(graph.keySet());
		int[] sorted = new int[graph.size()+1];
		int[][] all = new int[3][graph.size()+1];

		Iterator<Integer> it = q.iterator();
		while(it.hasNext()){	//find all nodes w indeg = 0 
			Integer k = it.next();
			for(Integer d: graph.get(k)){
				in[d]++;
				inTemp[d]++;
				out[k]++;
			} 
			if(in[k]>0) q.remove(k);	//remove from queue after being iterated through so at end of while loop, only 0 in deg left
		}

		int i = 0;
		while(!q.isEmpty()){
			Integer k1 = q.remove();
			sorted[i++] = k1;
			for(Integer d1: graph.get(k1)){
				if(--inTemp[d1]==0) q.add(d1);
			}
		}

		all[0] = sorted;
		all[1] = in;
		all[2] = out;

		return all;

	}

	public static int jumps(int[][] info, int[] location){

		return 0;
	}

	public static void main(String[] args) {

	}

}
