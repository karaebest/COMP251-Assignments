import java.util.*;

public class Q1 {
	public static int[] last = new int[2];
	public static int initialNumOfBalls = 0;
	public static HashSet<int[]> nilSpaces;

	public static HashSet<int[]> positions(String[][] board){ //to insert initial ball positions into hashset

		HashSet<int[]> balls = new HashSet<>();


		int i=0;

		while(i<board.length){
			for(int j=(i==0 || i==4)?3:0; j<((i==0 || i==4)?6:8); j++){
				if(board[i][j].equals("o")){
					balls.add(new int[] {i,j});
				}
			}
			i++;
		}
		A2_Q1.last[0] = balls.size();
		A2_Q1.initialNumOfBalls = balls.size();
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
		if(j+2<9 && (!((i==0 || i==4) && (j+2<3 || j+2>5))) && balls.contains(new int[] {i, j+1})){ //space right
			jump.add(new int[] {i, j+1});
		}
		if(i-2>=0 && (!((i-2==0) && (j<3 || j>5))) && balls.contains(new int[] {i-1, j})){ //space left
			jump.add(new int[] {i, j-1});
		}


		return jump;
		
	}

	

	public static void moveBall(HashSet<int[]> balls, int[] sol, int[] toDelete, int[] oldPos){

		int[] newPos = new int[2];
		
		if(oldPos!=null){ 
			newPos[0] = (toDelete[0]-oldPos[0]==0)?toDelete[0]:(2*toDelete[0]-oldPos[0]);
			newPos[1] = (toDelete[1]-oldPos[1]==0)?toDelete[1]:(2*toDelete[1]-oldPos[1]);
			//delete jumped over, change index of moved ball
			balls.remove(toDelete);
			balls.remove(oldPos);
			balls.add(newPos);
			
		}

		sol[0] = balls.size(); 
		if(sol[0]==1){ //no better solution for the board
			A2_Q1.last = sol;
			return;
		}
		
		Iterator b = balls.iterator();
		int j = 1;

		while(b.hasNext()){ //iterate through balls
			int[] temp = b.next();
			ArrayList<int[]> jumps = jumps(temp, balls);
			j = jumps.isEmpty()?j+1:j;

			Iterator jp = jumps.iterator();
			while(jp.hasNext()){ //iterate through ball[i] jumps
				moveBall(balls, sol, jp.next(), temp);
			}
		}

		if(j==balls.size()){
			A2_Q1.last = last[0]<=sol[0]?last:sol;
		}
	
	}
	
	public static int[] game(String[][] board){
		
		moveBall(positions(board), new int[2], null, null);
		A2_Q1.last[1] = A2_Q1.initialNumOfBalls-A2_Q1.last[0];

		return A2_Q1.last;
	}

}

