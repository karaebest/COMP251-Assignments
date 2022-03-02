package Templates;

import java.util.*;

public class Q1 {
	public static int[] opt = new int[2];
	public static int initialNumOfBalls = 0;

	public static HashSet<int[]> positions(String[][] board){ //to insert initial ball positions into hashset

		HashSet<int[]> balls = new HashSet<>();


		int i=0;

		while(i<board.length){
			for(int j=(i==0 || i==4)?3:0; j<((i==0 || i==4)?6:9); j++){
				if(board[i][j].equals("o")){
					balls.add(new int[] {i,j});
				}
			}
			i++;
		}
		Q1.opt[0] = balls.size();
		Q1.initialNumOfBalls = balls.size();
		return balls;
	}

	public static ArrayList<int[]> jumps(int[] pos, HashSet<int[]> balls){ //returns list of adjacent balls to ball pos that can be jumped over

		ArrayList<int[]> jump = new ArrayList<>();
		int i = pos[0];
		int j = pos[1];
		


		if(i+2<5 && (!((i+2==4) && (j<3 || j>5))) && balls.contains(new int[] {i+1, j})){ //space above
			jump.add(new int[] {i+1, j});
		}
		if(i-2>=0 && (!((i-2==0) && (j<3 || j>5))) && balls.contains(new int[] {i-1, j})){ //space below
			jump.add(new int[] {i-1, j});
		}
		if(j+2<9 && (!((i==0 || i==4) && j+2>5)) && balls.contains(new int[] {i, j+1})){ //space right
			jump.add(new int[] {i, j+1});
		}
		if(j-2>=0 && (!((i==0 || i==4) && j-2<3)) && balls.contains(new int[] {i, j-1})){ //space left
			jump.add(new int[] {i, j-1});
		}


		return jump;
		
	}

	

	public static void moveBall(HashSet<int[]> balls, int minB, int[] toDelete, int[] oldPos){

		int[] newPos = new int[2];
		
		if(oldPos[0]!=-1){ 
			newPos[0] = (toDelete[0]-oldPos[0]==0)?toDelete[0]:(2*toDelete[0]-oldPos[0]);
			newPos[1] = (toDelete[1]-oldPos[1]==0)?toDelete[1]:(2*toDelete[1]-oldPos[1]);
			//delete jumped over, change index of moved ball
			balls.remove(toDelete);
			balls.remove(oldPos);
			balls.add(newPos);
			
		}

		if(minB==1){ //no better minB for the board
			Q1.opt[0] = minB;
			return;
		}
		
		Iterator<int[]> b = balls.iterator();
		int j = 0;

		while(b.hasNext()){ //iterate through balls
			int[] temp = b.next();
			ArrayList<int[]> jumps = jumps(temp, balls);
			if(jumps.isEmpty()){
				j++;
				continue;
			}

			Iterator<int[]> jp = jumps.iterator();
			while(jp.hasNext()){ //iterate through ball[i] jumps
				int[] jpTemp = jp.next();
				moveBall(balls, minB-1, jpTemp, temp);
			}
		}

		if(j==balls.size()){
			Q1.opt[0] = opt[0]<=minB?opt[0]:minB;
			return;
		}
	
	}
	
	public static int[] game(String[][] board){

		HashSet<int[]> balls = positions(board);
		int[] temp = new int[] {-1,-1};
		moveBall(balls, positions(board).size(), temp, temp); 
		Q1.opt[1] = Q1.initialNumOfBalls-Q1.opt[0];

		return Q1.opt;
	}

}

