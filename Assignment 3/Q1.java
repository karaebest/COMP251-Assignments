import java.util.*;


public class Q1 {

	public int x, y, z, d;
	//public Q1 pi;

	public Q1(int[] pos, int d){
		this.x = pos[0];
		this.y = pos[1];
		this.z = pos[2];
		this.d = d;
		//this.pi = pi;
	}

	public static int find_exit(String[][][] jail) {

		//find first open space then DFS from there until done
			//if source found and exit not found or exit found and source not found, then not possible, return -1
			//if neither found, then move on to next open space and DFS

		for(int i=0; i<jail.length; i++){	
			for(int j=0; j<jail[0].length; j++){
				for(int k=0; k<jail[0][0].length;k++){
					 if(jail[i][j][k].contains("S") || jail[i][j][k].contains("E")){
						 return DFS(jail, new int[]{i,j,k}, jail[i][j][k].contains("S")?"E":"S");
					 }
				}
			}
		}
		return 0;
	}

	public static int DFS(String[][][] jail, int[] pos, String w){	

		Queue<Q1> q = new ArrayDeque<Q1>();
		
		Q1 u;
		int l = jail.length;
		int r = jail[0].length;
		int c = jail[0][0].length;
		int k = 0;
		
		jail[pos[0]][pos[1]][pos[2]] = "#";
		q.add(new Q1(pos, 0));
		while(!q.isEmpty()){
			u = q.remove();
			if(u.x+1<l && !jail[u.x+1][u.y][u.z].contains("#")){		//down
				if(jail[u.x+1][u.y][u.z].contains(w)) return u.d+1;
				jail[u.x+1][u.y][u.z] = "#";
				q.add(new Q1(new int[]{u.x+1, u.y, u.z}, u.d+1));
				//System.out.println("down x:"+(u.x+1) + " y:"+u.y+ " z:"+ u.z);		
			}
			if(u.x-1>=0 && !jail[u.x-1][u.y][u.z].contains("#")){	//up
				if(jail[u.x-1][u.y][u.z].contains(w)) return u.d+1;
				jail[u.x-1][u.y][u.z] = "#";
				q.add(new Q1(new int[]{u.x-1, u.y, u.z}, u.d+1));
				//System.out.println("up x:"+(u.x-1) + " y:"+u.y+ " z:"+ u.z);		
			}
			if(u.y+1<r && !jail[u.x][u.y+1][u.z].contains("#")){	//front
				if(jail[u.x][u.y+1][u.z].contains(w)) return u.d+1;
				jail[u.x][u.y+1][u.z] = "#";
				q.add(new Q1(new int[]{u.x, u.y+1, u.z}, u.d+1));
				//System.out.println("front x:"+u.x + " y:"+(u.y+1)+ " z:"+ u.z);		
			}
			if(u.y-1>=0 && !jail[u.x][u.y-1][u.z].contains("#")){	//back
				if(jail[u.x][u.y-1][u.z].contains(w)) return u.d+1;
				jail[u.x][u.y-1][u.z] = "#";
				q.add(new Q1(new int[]{u.x, u.y-1, u.z}, u.d+1));
				//System.out.println(" back x:"+u.x + " y:"+(u.y-1)+ " z:"+ u.z);		
			}
			if(u.z+1<c && !jail[u.x][u.y][u.z+1].contains("#")){	//right
				if(jail[u.x][u.y][u.z+1].contains(w)) return u.d+1;
				jail[u.x][u.y][u.z+1] = "#";
				q.add(new Q1(new int[]{u.x, u.y, u.z+1}, u.d+1));
				//System.out.println("right x:"+u.x + " y:"+(u.y)+ " z:"+ (u.z+1));		
			}
			if(u.z-1>=0 && !jail[u.x][u.y][u.z-1].contains("#")){	//left
				if(jail[u.x][u.y][u.z-1].contains(w)) return u.d+1;
				jail[u.x][u.y][u.z-1] = "#";
				q.add(new Q1(new int[]{u.x, u.y, u.z-1}, u.d+1));
				//System.out.println("left x:"+u.x + " y:"+(u.y)+ " z:"+ (u.z-1));		
			}
			k++;
			//System.out.println(k);
		}
		return -1;
	}
	


	public static void main(String[] args) {
		String[][][] jail = {
			{
					{".","S","#"},
					{".",".","#"},
					{"#",".","."}
			},{
					{".",".","#"},
					{".",".","#"},
					{"#",".","E"}
			}

		};
		System.out.println(find_exit(jail));
	}

}
