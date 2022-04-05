import java.util.*;
import java.io.File;
import java.lang.reflect.Array;

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
		WGraph gf = new WGraph(graph);
		ArrayList<Edge> ed = graph.getEdges();
		for(Edge e: ed){
			e.weight = 0;
		}
		Boolean first = true;
		ArrayList<Integer> p = null;
		p = pathDFS(gf.getSource(), gf.getDestination(), gf);
		while(p==null || !p.isEmpty()){
			maxFlow += augment(p, gf, graph, first);
			first = false;
			p = pathDFS(gf.getSource(), gf.getDestination(), gf);
			
		}

		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	
	public static int augment(ArrayList<Integer> path, WGraph gf, WGraph g, Boolean first){
		int beta = -1;
		Edge f;
		Edge b;
		Edge e;

		ArrayList<ArrayList<Edge>> all = new ArrayList<>();
		ArrayList<Edge> ed = new ArrayList<Edge>();
		WGraph newR = new WGraph();
		
		for(int i=0; i<path.size()-1; i++){		//find bottleneck
			f = gf.getEdge(path.get(i), path.get(i+1));
			if(first){
				ed.add(f);
				if(beta == -1 || f.weight<beta) beta = f.weight ;
			}else{
				ed = new ArrayList<Edge>();
				ed.add(f);
				b = gf.getEdge(path.get(i+1), path.get(i));
				if(b!=null) ed.add(b);
				all.add(ed);
				if(beta == -1 || f.weight<beta) beta = f.weight ;
			}
			
		}

		if(first){
			for(Edge e1: ed){
				e1.weight -= beta;
				if(e1.weight!=0) newR.addEdge(new Edge(e1.nodes[0], e1.nodes[1], e1.weight));
				newR.addEdge(new Edge(e1.nodes[1], e1.nodes[0], beta));
				g.getEdge(e1.nodes[0], e1.nodes[1]).weight = beta;
			}
		}else{
			for(ArrayList<Edge> a: all){
				f = a.get(0);
				f.weight -= beta;
				e = g.getEdge(f.nodes[0], f.nodes[1]);
				if(e != null) e.weight += beta;
				if(f.weight!=0) newR.addEdge(f);

				if(a.size()==2){
					b = a.get(1);
					b.weight += beta;
					newR.addEdge(b);
				}else{
					newR.addEdge(new Edge(f.nodes[1], f.nodes[0], beta));
				}
			}
			
		}
		return beta;
	}


	 public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
	    System.out.println(fordfulkerson(g));
		// WGraph g = new WGraph();
		// g.setSource(0);
		// g.setDestination(5);
		// Edge[] edges = new Edge[] {
		// 			new Edge(0, 1, 10),
		// 			new Edge(0, 2, 5),
		// 			new Edge(2, 4, 5),
		// 			new Edge(1, 3, 10),
		// 			new Edge(1, 6, 5),
		// 			new Edge(3, 5, 10),
		// 			new Edge(2, 5, 5)
		// 		};
		// Arrays.stream(edges).forEach(e->g.addEdge(e));
		// ArrayList<Integer> path = pathDFS(0, 5, g);
		// for(Integer u: path){
		// 	System.out.println("p:"+u);
		// }
		// System.out.println(path.get(0)==0 && path.get(path.size()-1)==5);
	 }
}

