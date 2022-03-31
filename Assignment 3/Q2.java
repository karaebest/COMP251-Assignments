import java.util.*;

import javax.swing.text.html.HTMLDocument.RunElement;


public class Q2 {
	
	public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {

		return jumps(TS(graph), location);
	}

	public static int[][] TS(Hashtable<Integer, ArrayList<Integer>> graph){	//returns array of arrays of [sorted keys, in degrees, out degrees]

		int[] inT = new int[graph.size()+1];
		int[] outT = new int[graph.size()+1];

		int[] inTemp = new int[graph.size()+1];	
		Queue<Integer> q = new ArrayDeque<Integer>();
		ArrayList<Integer> f = new ArrayList<>();
		f.addAll(graph.keySet());
		int[] sorted = new int[graph.size()];
		int[][] all = new int[3][graph.size()];

		for(Integer k: f){
			for(Integer d: graph.get(k)){
				inT[d]++;
				inTemp[d]++;
				outT[k]++;
			}
		}
		for(Integer k: f){
			if(inT[k]==0) q.add(k);
		}
		
		int i = 0;
		while(!q.isEmpty()){
			Integer k1 = q.remove();
			sorted[i++] = k1;
			for(Integer d1: graph.get(k1)){
				if(--inTemp[d1]==0) q.add(d1);
			}
		}
		int[] in = new int[graph.size()];
		int[] out = new int[graph.size()];
		for(i=0; i<sorted.length; i++){		
			in[i] = inT[sorted[i]];
			out[i] = outT[sorted[i]];
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
		//int[] location = new int[]{2,1,2,2,1};
		Hashtable<Integer, ArrayList<Integer>> g = new Hashtable<Integer, ArrayList<Integer>>();
		Integer one = 1;
		Integer two = 2;
		Integer th = 3;
		Integer fo = 4;
		Integer five = 5;
	
		g.put(one, new ArrayList<>());
		ArrayList<Integer> a = new ArrayList<>();
		a.add(one);
		g.put(2, a);
		ArrayList<Integer> b = (ArrayList<Integer>) a.clone();
		b.add(two);
		g.put(th, b);
		ArrayList<Integer> c = (ArrayList<Integer>) b.clone();
		c.add(th);
		g.put(fo, c);
		ArrayList<Integer> d = (ArrayList<Integer>) c.clone();
		d.add(fo);
		g.put(five, d);
		
		int[][] result = TS(g);

		for(int i=0; i<5; i++){
			System.out.println("key:"+result[0][i]+ " in:"+result[1][i]+ " out:"+ result[2][i]);
		}
		
	}

}
