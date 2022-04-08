package Q3;
import java.util.*;
import java.io.File;
import java.lang.reflect.Array;


public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/
		ArrayList<Edge> ed = graph.getEdges();
		int[] color = new int[graph.getNbNodes()];		// 0: white, 1: gray
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

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

		WGraph gf = new WGraph(graph);
		ArrayList<Edge> edges = graph.getEdges();
		int[] cap = new int[edges.size()];
		int k=0;
		int beta;
		ArrayList<Integer> p = null;

		for(Edge e: edges){
			cap[k++] = e.weight;
			e.weight = 0;
		}


		p = pathDFS(graph.getSource(), graph.getDestination(), gf);

		//System.out.print("source:" + graph.getSource() + " dest:" + graph.getDestination());

		while((p==null || !p.isEmpty())){
			// System.out.println("new path:");
			// for(int l=0; l<p.size(); l++){
			// 	System.out.println(p.get(l));
			// }
			// System.out.println("end path:");


			// all = new ArrayList<>();
			// edg = new ArrayList<Edge>();
			//beta = findB(p, gf, graph, first, all, edg);

			beta = findB(p, gf, graph);
			if(beta==-1) break;
			maxFlow += beta;

			gf = new WGraph(augment(beta, p, gf, graph, cap));

			// System.out.println("flow="+maxFlow);
			// System.out.print("current residual:\n"+gf.toString() + "\n end res\n");
			// System.out.print("current graph:\n"+ graph.toString()+ "\n end graph\n");

			p = pathDFS(graph.getSource(), graph.getDestination(), gf);
		}

		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}

	public static int findB(ArrayList<Integer> path, WGraph gf, WGraph g/*, Boolean first, ArrayList<ArrayList<Edge>> all, ArrayList<Edge> ed*/){

		int beta = -1;
		Edge e;
		
		for(int i=0; i<path.size()-1; i++){		//find bottleneck
			e = gf.getEdge(path.get(i), path.get(i+1));
			if(beta == -1 || e.weight<beta) beta = e.weight;
			
		}
		if(beta==-1) return beta;
		for(int i=0; i<path.size()-1; i++){		//update flows
			e = g.getEdge(path.get(i), path.get(i+1));
			if(e != null) e.weight += beta;
			else{
				e = g.getEdge(path.get(i+1), path.get(i));
				e.weight -= beta;
			}
		}

		return beta;
	}
	
	public static WGraph augment(int beta, ArrayList<Integer> path, WGraph gf, WGraph g, int[] cap/*, Boolean first, ArrayList<ArrayList<Edge>> all, ArrayList<Edge> ed*/){


		Edge e;
		ArrayList<Edge> edges = g.getEdges();
		
		WGraph newR = new WGraph();
		newR.setSource(g.getSource());
		newR.setDestination(g.getDestination());


		for(int i=0; i<edges.size(); i++){
			e = edges.get(i);
			if(e.weight==0) newR.addEdge(new Edge(e.nodes[0], e.nodes[1], cap[i]));
			else if(e.weight==cap[i]) newR.addEdge(new Edge(e.nodes[1], e.nodes[0], cap[i]));
			else{
				newR.addEdge(new Edge(e.nodes[0], e.nodes[1], cap[i]-e.weight));
				newR.addEdge(new Edge(e.nodes[1], e.nodes[0], e.weight));
			}
		}

		return newR;
		
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
						new Edge(0, 1, 16),
						new Edge(0, 2, 13),
						new Edge(2, 1, 4),
						new Edge(1, 3, 12),
						new Edge(2, 4, 14),
						new Edge(3, 2, 9),
						new Edge(3, 5, 20),
						new Edge(4, 3, 7),
						new Edge(4, 5, 4),
					};
		Arrays.stream(edges).forEach(e->g.addEdge(e));            
		String result = FordFulkerson.fordfulkerson(g);
		System.out.println(result);
		//weightsFromString(result);
	 }
}

