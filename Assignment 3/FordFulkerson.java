import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/
		ArrayList<Edge> ed = graph.getEdges();
		int[] color = new int[graph.getNbNodes()];		// 0: white, 1: gray, -1: black
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>(graph.getNbNodes()); 
		Stack<Integer> st = new Stack<Integer>();
		int u;
		int v;

		for(Edge e: ed){			//create adjacency list
			u = e.nodes[0];
			v = e.nodes[1];
			if(adj.get(u)==null) adj.add(u, new ArrayList<Integer>());
			adj.get(u).add(v);
		}

		st.push(source);
		color[source] = 1;
		while(!st.isEmpty()){
			u = st.pop();
			if(adj.get(u)==null){	//if dead end 
				color[u] = -1;
				continue;
			}
			path.add(u);
			for(Integer w: adj.get(u)){
				if(color[w]==0){
					if(w == destination.intValue()){
						path.add(w);
						return path;
					}
					color[w] = 1;
					st.push(w);
				}
			}

		}

		return path;
	}




	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		
		/* YOUR CODE GOES HERE		*/

		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	

	 public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
	    System.out.println(fordfulkerson(g));
	 }
}

