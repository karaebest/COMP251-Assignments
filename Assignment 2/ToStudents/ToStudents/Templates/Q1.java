package Templates;

import java.util.*;

public class Q1 {
	public static int[] opt = new int[2];
	public static int initialNumOfBalls = 0;

	public static HashSet<int[]> positions(String[][] board){ //to insert initial ball positions into ArrayList

		HashSet<int[]> balls = new HashSet<>();


		int i=0;
		while(i<board.length){
			for(int j=0; j<9; j++){
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

	public static HashSet<int[]> jumps(int[] pos, HashSet<int[]> balls){ //returns list of adjacent balls to ball pos that can be newPosT over

		HashSet<int[]> jump = new HashSet<>();
		int i = pos[0];
		int j = pos[1];
		HashSet<int[]> temp = new HashSet<>();
		temp.addAll(balls);

		System.out.println("TEST:"+!temp.add(new int[] {i+1,j}));
		if(i+2<5 && (!((i+2==4) && (j<3 || j>5))) && !temp.add(new int[] {i+1,j})){ //space above
			jump.add(new int[] {i+1,j});
		}
		if(i-2>=0 && (!((i-2==0) && (j<3 || j>5))) && !temp.add(new int[] {i-1,j})){ //space below
			jump.add(new int[] {i-1,j});
		}
		if(j+2<9 && (!((i==0 || i==4) && j+2>5)) && !temp.add(new int[] {i,j+1})){ //space right
			jump.add(new int[] {i,j+1});
		}
		if(j-2>=0 && (!((i==0 || i==4) && j-2<3)) && !temp.add(new int[] {i,j-1})){ //space left
			jump.add(new int[] {i,j-1});
		}

		return jump;
		
	}

	

	public static void moveBall(HashSet<int[]> balls, int minB, int[] newPos, int[] toDelete, int[] oldPos){
		
		if(oldPos[0]!=-1){ 
			//delete newPosT over, change index of moved ball
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
			HashSet<int[]> jumps = jumps(temp, balls);
			if(jumps.isEmpty()){
				j++;
				continue;
			}

			Iterator<int[]> jp = jumps.iterator();
			while(jp.hasNext()){ //iterate through ball[i] jumps
				int[] jpTemp = jp.next(); //ball to delete
				
				int[] newPosT = temp; 
				if(jpTemp[0]!=temp[0]){
					newPosT[0] = (jpTemp[0]<temp[0])?newPosT[0]-2:newPosT[0]+2;
				}else{
					newPosT[1] = (jpTemp[1]<temp[1])?newPosT[1]-2:newPosT[1]+2;
				}
				HashSet<int[]> newBalls = new HashSet<>();
				newBalls.addAll(balls);
				moveBall(balls, minB-1, jpTemp, newPosT, temp);
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
		moveBall(balls, balls.size(), temp, temp, temp); 
		Q1.opt[1] = Q1.initialNumOfBalls-Q1.opt[0];

		return Q1.opt;
	}

}

