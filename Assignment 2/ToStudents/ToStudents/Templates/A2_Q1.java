import java.util.*;

public class A2_Q1 {
	public static int[] last = new int[2];
	public static int initialNumOfBalls = 0;

	public static HashSet<Integer[]> positions(String[][] board){ //to insert initial ball positions into hashset

		HashSet<Integer[]> initialPositions = new HashSet<>();

		int i=0;

		while(i<board.length){
			for(int j=(i==0 || i==4)?3:0; j<((i==0 || i==4)?6:8); j++){
				if(board[i][j].equals("o")){
					initialPositions.add(new Integer[] {i,j});
				}
			}
			i++;
		}
		A2_Q1.last[0] = initialPositions.size();
		A2_Q1.initialNumOfBalls = initialPositions.size();
		return initialPositions;
	}

	public static ArrayList<int[]> jumps(int row, int col, String[][] board){ //(Integer[] pos, HashSet<Integer[]> positions, HashSet<Integer[]> fixed)

		ArrayList<int[]> jump = new ArrayList<>(); 								// change to ^^ and vv

		if(row+2<5 && board[row+1][col].equals("o") && board[row+2][col].equals(".")){ // pos[0]+2<5 && (!((pos[0]==1 or ==4) and (pos[1]<3 or >5))) && (positions.contains({pos[0]+1, pos[1]}) || fixed.contains())
			jump.add(new int[] {row+1, col});
		}
		if(row-2>=0 && board[row-1][col].equals("o")&& board[row-2][col].equals(".")){
			jump.add(new int[] {row-1, col});
		}
		if(col+2<5 && board[row][col+1].equals("o") && board[row][col+2].equals(".")){
			jump.add(new int[] {row, col+1});
		}
		if(col-2>=0 && board[row][col=1].equals("o")&& board[row][col-2].equals(".")){
			jump.add(new int[] {row, col-1});
		}


		return jump;
		
	}


	public static void moveBall(HashSet<Integer[]> positions, HashSet<Integer[]> fixed, int[] sol, int[] toDelete, int[] oldPos){

		sol[0] = positions.size()+fixed.size(); 
		if(sol[0]==1){ 
			A2_Q1.last = sol;
			return;
		}
		if(oldPos!=null){
			//use toDelete and oldPos to find newPos
			//delete jumped over, change index of moved ball
			//call jumps on new position and add to fixed if none
			//call jumps on old position, check fixed.contains(jumps[i]), if true call jumps on jumps[i] and switch from fixed to positions if there are possible jumps
		}
		
		//if positions.size()==0, compare to previous solution last and replace if better, do nothing o/w
		if(positions.size()==0){
			A2_Q1.last = last[0]<=sol[0]?last:sol;
		}

		//iterate through positions
			//if jumps is empty
				//move into fixed
				//delete from positions 
				//if positions.size()==0, compare to previous solution last and replace if better, do nothing o/w
			//iterate through possible jumps
				//call moveBall(positions, fixed, sol, jumps[i], positions[i])
	
	}
	
	public static int[] game(String[][] board){

		HashSet<Integer[]> fixed = new HashSet<>();
		
		moveBall(positions(board), fixed, new int[2], null, null);
		A2_Q1.last[1] = A2_Q1.initialNumOfBalls-A2_Q1.last[0];

		return A2_Q1.last;
	}

}
