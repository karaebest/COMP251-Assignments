package Templates;

import java.util.*;

//to try: use array instead of ArrayList, try .remove() on iterators, has to do with copies being passed as args to moveBall

public class Q1_final {

	public static int[][] positions(int[][] oldBalls, int[] newPos, int[] tD, int[] oP){ //to insert initial ball positions into ArrayList

		int[][] balls = new int[33][2];
		int b=0;
		int[] p;
		int k = 0;

		while(!oldBalls[b].equals(new int[] {0,0})){
			p = oldBalls[b];
			if((!p.equals(tD)) && !p.equals(oP)){
                balls[k++] = oldBalls[b];
            }
			b++;
		}
		balls[k] = newPos;
		return balls;
	}

	public static ArrayList<int[]> jumps(int[] pos, String[][] board){ //returns list of adjacent balls to ball pos that can be newPosT over

		ArrayList<int[]> jump = new ArrayList<>();
		int i = pos[0];
		int j = pos[1];

		if(i+2<5 && board[i+2][j].equals(".") && board[i+1][j].equals("o")){ //space above
			jump.add(new int[] {i+1,j});
		}
		if(i-2>=0 && board[i-2][j].equals(".") && board[i-1][j].equals("o")){ //space below
			jump.add(new int[] {i-1,j});
		}
		if(j+2<9 && board[i][j+2].equals(".") && board[i][j+1].equals("o")){ //space right
			jump.add(new int[] {i,j+1});
		}
		if(j-2>=0 && board[i][j-2].equals(".") && board[i][j-1].equals("o")){ //space left
			jump.add(new int[] {i,j-1});
		}

		return jump;
		
	}

	public static int moveBall(int[][] oldBalls, int[] newPos, int[] toDelete, int[] oldPos, String[][] board, int minB){
		int[][] balls;
		

		if(oldPos[0]!=-1){  
            balls = positions(oldBalls, newPos, toDelete, oldPos);
            board[toDelete[0]][toDelete[1]] = ".";
			board[oldPos[0]][oldPos[1]] = ".";
            board[newPos[0]][newPos[1]] = "o";
			minB--;
		}else{
            balls = oldBalls;
        }

		int opt = minB;

		if(minB==1){ //no better minB for the board
			return minB;
		}
		
		int b = 0;
		
		while(!balls[b].equals(new int[] {0,0})){
			ArrayList<int[]> jumps = jumps(balls[b], board);
			for(int i=0; i<jumps.size(); i++){
				int[] tD = jumps.get(i);
				int[] nP = balls[b];
				if(tD[0]!=balls[b][0]){ //find newPos
				    nP[0] = (tD[0]<balls[b][0])?nP[0]-2:nP[0]+2;
			    }else{
				    nP[1] = (tD[1]<balls[b][1])?nP[1]-2:nP[1]+2;
			    }
				opt = moveBall(balls, nP, tD, balls[b], board, minB);
				opt = opt<=minB?opt:minB;

			}
			b++;
		}
		return opt;
	
	}
	
	public static int[] game(String[][] board){

		int[][] balls = new int[33][2];
		int i=0;
		int b=0;
		int[] opt = new int[2];

		while(i<board.length){
			for(int j=0; j<9; j++){
				if(board[i][j].equals("o")){
					balls[b++] = new int[] {i,j};
				}
			}
			i++;
		}
		int[] temp = new int[] {-1,-1};
		opt[0] = moveBall(balls, temp, temp, temp, board, b); 
		opt[1] = b - opt[0];

		return opt;
	}

}


