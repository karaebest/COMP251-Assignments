import java.util.*;


public class Q2 {
	
	public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {

		int[] in = new int[graph.size()];
		ArrayDeque<Integer> q1 = new ArrayDeque<Integer>();
		ArrayDeque<Integer> q2 = new ArrayDeque<Integer>();
		ArrayList<Integer> keys = new ArrayList<>();
		int sum1 = 0;
		int sum2 = 0;
		
		keys.addAll(graph.keySet());

		for(Integer k: keys){				//compute all in degrees
			for(Integer d: graph.get(k)){
				in[d]++;
			}
		}
		for(Integer k: keys){				//find all sources and add to queues and increase sums
			if(in[k]==0){
				if(location[k]==1){
					q1.add(k);
					in[k] = -1;
					sum1 = sum1 + 1 + graph.get(k).size();
				}else{
					q2.add(k);
					in[k] = -1;
					sum2 = sum2 + 1 + graph.get(k).size();
				}
			}
		}
		
		if(sum1<sum2){
			return TS(graph, q1, q2, in, location, 2, 0);
		}
		if(sum1>sum2){
			return TS(graph, q1, q2, in, location, 1, 0);
		}else{
			int[] inT = in.clone();
			
			ArrayDeque<Integer> q1T = new ArrayDeque<Integer>();
			for(Integer k: q1){
				q1T.add(k);
			}
			ArrayDeque<Integer> q2T = new ArrayDeque<Integer>();
			for(Integer d: q2){
				q2T.add(d);
			}
			int j1 = TS(graph, q1T, q2T, inT, location, 1, 0);
			int j2 = TS(graph, q1, q2, in, location, 2, 0);

			return Math.min(j1, j2);
		}
		
	}

	public static int TS(Hashtable<Integer, ArrayList<Integer>> g, Queue<Integer> q1, Queue<Integer> q2, int[] in, int[] loc, int p, int j){
		
		Integer k;
		if(p==1){
			while(!q1.isEmpty()){
				k = q1.remove();
				for(Integer d: g.get(k)){
					if(in[d] != -1) in[d]--;
					if(in[d]!=-1 && in[d]==0){
						if(loc[d]==1){
							q1.add(d);
						}else{
							q2.add(d);
						}
						in[d] = -1;
					}
				}
			}
			p = 2;
		}else{
			while(!q2.isEmpty()){
				k = q2.remove();
				for(Integer d: g.get(k)){
					if(in[d] != -1) in[d]--;
					if(in[d]!= -1 && in[d]==0){
						if(loc[d]==1){
							q1.add(d);
						}else{
							q2.add(d);
						}
						in[d] = -1;

					}
				}
			}
			p = 1;
		}

		if(q1.isEmpty() && q2.isEmpty()) return j;
		j++;
		return TS(g, q1, q2, in, loc, p, j);	//make jump
	}

	public static void main(String[] args) {

		Hashtable<Integer, ArrayList<Integer>> g = new Hashtable<Integer, ArrayList<Integer>>();
		Integer one = 0;
		Integer two = 1;
		Integer th = 2;
		Integer fo = 3;
		Integer five = 4;
	
		g.put(one, new ArrayList<>());
		ArrayList<Integer> a = new ArrayList<>();
		a.add(five);
		g.put(two, a);
		ArrayList<Integer> b = new ArrayList<>();
		b.add(fo);
		g.put(th, b);
		ArrayList<Integer> c = new ArrayList<>();
		//c.add(th);
		g.put(fo, c);
		ArrayList<Integer> d = new ArrayList<>();
		d.add(one);
		g.put(five, d);

		int[] location = new int[]{1,1,2,2,2};
		
		System.out.println(rings(g, location));
		
	}

}
