import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/
		ArrayList<Edge> ed = graph.getEdges();
		int[] color = new int[graph.getNbNodes()];		// 0: white, 1: gray
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>(graph.getNbNodes());
		 
		for(int i=0; i<graph.getNbNodes(); i++){
			adj.add(new ArrayList<Integer>());
		}
		int u;
		int v;

		for(Edge e: ed){			//create adjacency list
			u = e.nodes[0];
			v = e.nodes[1];
			adj.get(u).add(v);
		}
		color[source] = 1;
		return recursiveDFS(source, source, destination, path, adj, color);	//if empty, then no path
	}

	public static ArrayList<Integer> recursiveDFS(Integer u, Integer s, Integer dest, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> adj, int[] color){
		if(adj.get(u).isEmpty()) return path;		//if dead end
		path.add(u);
		for(Integer v: adj.get(u)){
			if(color[v]==0){
				if(v==dest.intValue()){
					path.add(v);
					return path;
				}
				ArrayList<Integer> p = (ArrayList<Integer>) path.clone();
				color[v] = 1;
				ArrayList<Integer> result = recursiveDFS(v, s, dest, p, adj, color);
				if(result.get(result.size()-1)==dest.intValue()) return result;
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
		// String file = args[0];
		// File f = new File(file);
		// WGraph g = new WGraph(file);
	    // System.out.println(fordfulkerson(g));
		WGraph g = new WGraph();
		g.setSource(0);
		g.setDestination(5);
		Edge[] edges = new Edge[] {
					new Edge(0, 1, 10),
					new Edge(0, 2, 5),
					new Edge(2, 4, 5),
					new Edge(1, 3, 10),
					new Edge(1, 6, 5),
					new Edge(3, 5, 10),
					new Edge(2, 5, 5)
				};
		Arrays.stream(edges).forEach(e->g.addEdge(e));
		ArrayList<Integer> path = pathDFS(0, 5, g);
		for(Integer u: path){
			System.out.println("p:"+u);
		}
		System.out.println(path.get(0)==0 && path.get(path.size()-1)==5);
	 }
}

