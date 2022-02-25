import java.util.*;

public class A2_Q1 {

	public static HashSet<Integer> positions(String[][] board){ //to insert initial ball positions into hashset
		HashSet<Integer> initialPositions = new HashSet<>();
		int j=3;
		int i=0;
		while(true){ // search top and bottom rows
			if(i==board.length && j==4){
				break;
			}
			if(board[i][j].equals("o")){
				initialPositions.add(10*i+j);
			}
			
		}
		//search board and store all positions in hashset
		//symmetries? 
		//for loop searching a quarter of board including middle, when at space [i][j] also check spaces [board.length-1-i/i][board[0].length-1-j/j]
					//except when in middle row (i==floor(board.length/2) or j==floor(board[0].length/2))
		
		return initialPositions;
	}

	public static int[] moveBall(int m, HashSet<Integer> inPlay, HashSet<Integer> fixed, int[] last){
		//if only 1 ball left, return right away
		//if only balls in fixed, add as solution, 

		return new int[2];
	}
	
	public static int[] game(String[][] board){



		return new int[2];
	}

}
