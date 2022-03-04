package Templates;
import java.util.*;

public class A2_Q1 {
	public static ArrayList<int[]> positions(ArrayList<int[]> oldBalls, int[] newPos, int[] tD, int[] oP){ //merge with main func, they run simultaneously

		ArrayList<int[]> balls = new ArrayList<>();

		for(int[] b: oldBalls){
			if((!(b[0]==tD[0] && b[1]==tD[1])) && !(b[0]==oP[0] && b[1]==oP[1])){
				balls.add(b.clone());
            }
		}
		balls.add(newPos.clone());
		return balls;
	}

	public static ArrayList<int[]> jumps(int[] pos, String[][] board){ //returns list of adjacent balls to ball pos that can be newPosT over

		ArrayList<int[]> jump = new ArrayList<>();
		int i = pos[0];
		int j = pos[1];
		int[] toAdd = new int[2];

		if(i+2<5 && board[i+2][j].equals(".") && board[i+1][j].equals("o")){ //space above
			toAdd[0] = i+1;
			toAdd[1] = j;
			jump.add(toAdd.clone());
		}
		if(i-2>=0 && board[i-2][j].equals(".") && board[i-1][j].equals("o")){ //space below
			toAdd[0] = i-1;
			toAdd[1] = j;
			jump.add(toAdd.clone());
		}
		if(j+2<9 && board[i][j+2].equals(".") && board[i][j+1].equals("o")){ //space right
			toAdd[0] = i;
			toAdd[1] = j+1;
			jump.add(toAdd.clone());
		}
		if(j-2>=0 && board[i][j-2].equals(".") && board[i][j-1].equals("o")){ //space left
			toAdd[0] = i;
			toAdd[1] = j-1;
			jump.add(toAdd.clone());
		}

		return jump;
		
	}

	public static int moveBall(ArrayList<int[]> oldBalls, String[][] board, int minB){
		
		SortedSet<Integer> sol = new TreeSet<>(); //add solutions into here, return min one
		ArrayList<int[]> jump = new ArrayList<>();
		ArrayList<int[]> balls = new ArrayList<>();
		int add;
		String[][] bo;
		int[] nP = new int[2];
		
		for(int[] b: oldBalls){

			jump.clear();
			//jump = (ArrayList<int[]>) jumps(b, board).clone();
			jump.addAll(jumps(b, board));
			System.out.println("b:"+b[0]+" "+b[1]);
			
			for(int[] j: jump){
				System.out.println("j:"+j[0]+" "+j[1]+" for b:"+b[0]+" "+b[1]);
				bo = board.clone();
				nP = Arrays.copyOf(b, b.length);
				if(j[0]!=b[0]){ 
					nP[0] = (j[0]<b[0])?nP[0]-2:nP[0]+2;
				}else{
					nP[1] = (j[1]<b[1])?nP[1]-2:nP[1]+2;
				}

				balls.clear();	//take step to new state
				balls.addAll(positions(oldBalls, nP, j, b));
				//balls = (ArrayList<int[]>) positions(oldBalls, nP, j, b).clone();

				bo[j[0]][j[1]] = ".";	
				bo[b[0]][b[1]] = ".";
				bo[nP[0]][nP[1]] = "o";

				add = moveBall(balls, bo, minB-1);	//recur call
				System.out.println("j:"+j[0]+" "+j[1]+" for b:"+b[0]+" "+b[1]+" sol:"+add);
				sol.add(add);

				bo[j[0]][j[1]] = "o";	//revert to prev state
				bo[b[0]][b[1]] = "o";
				bo[nP[0]][nP[1]] = ".";

			}
		}

		if(sol.isEmpty()){
			return minB;
		}else{
			return sol.first();
		}
		 
	
	}
	
	public static int[] game(String[][] board){

		ArrayList<int[]> balls = new ArrayList<>();
		int initialB;
		int[] opt = new int[2];

		for(int i=0; i<board.length; i++){		//find all ball positions
			for(int j=0; j<board[0].length; j++){
				if(board[i][j].equals("o")){
					balls.add(new int[]{i,j});
				}
			}
		}
		initialB = balls.size();
		System.out.println(initialB);
		opt[0] = moveBall(balls, board, initialB);
		opt[1] = initialB - opt[0];
		return opt; 
	}
}