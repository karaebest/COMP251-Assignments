import java.util.*;

public class Q1 {
	public static int[] last = new int[2];
	public static int initialNumOfBalls = 0;

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

	public static ArrayList<HashSet<int[]>> amend(int[] delete, int[] oldP, int[] newP, HashSet<int[]> inPlay, HashSet<int[]> fixed){

		int[] diff = new int[] {-1,1};
		int[] del, o, n;
		int v, h; 
		int d=0;
		int k=0;

		while(k<4){
			v = (k%2==0)?1:0;
			h = (v==1)?0:1;
			del = new int[] {delete[0]+2*v*diff[d], delete[1]+2*h*diff[d]}; //+- 2 horz and vert from deleted space
			o = new int[] {oldP[0]+2*v*diff[d], oldP[1]+2*h*diff[d]};//+- 2 horz and vert from old space
			n = new int[] {newP[0]+v*diff[d], newP[1]+h*diff[d++]};//+- 1 horz and vert from new space
			if(fixed.contains(del) && !jumps(del, inPlay, fixed).isEmpty()){
				fixed.remove(del); inPlay.add(del);
			}if(fixed.contains(o) && !jumps(o, inPlay, fixed).isEmpty()){
				fixed.remove(o); inPlay.add(o);
			}if(fixed.contains(del) && !jumps(del, inPlay, fixed).isEmpty()){
				fixed.remove(del); inPlay.add(del);
			}

		}
		// for(int k=0; k<4; k++){
		// 	if(k<2){ //only check fixed since moveBall() amends inPlay
		// 		dV = new int[] {delete[0]+diff[k], delete[1]};
		// 		dH = new int[] {delete[0], delete[1]+diff[k]};
		// 		oldV = new int[] {oldP[0]+diff[k], oldP[1]};
		// 		oldH = new int[] {oldP[0], oldP[1]+diff[k]};
		// 		if(fixed.contains(dV)){
		// 			if(!jumps(dV, inPlay, fixed).isEmpty()){
		// 				fixed.remove(dV); inPlay.add(dV);
		// 			}
		// 			if(!jumps(dH, inPlay, fixed).isEmpty()){

		// 			}
		// 		}
		// 	}else{

		// 	}
		// }

	}


	public static void moveBall(HashSet<int[]> balls, int[] sol, int[] toDelete, int[] oldPos){

		int[] newPos = new int[2];
		
		if(oldPos!=null){ 
			newPos[0] = (toDelete[0]-oldPos[0]==0)?toDelete[0]:(2*toDelete[0]-oldPos[0]);
			newPos[1] = (toDelete[1]-oldPos[1]==0)?toDelete[1]:(2*toDelete[1]-oldPos[1]);
			//delete jumped over, change index of moved ball
			if(inPlay.contains(toDelete)){
				inPlay.remove(toDelete);
			}else{
				fixed.remove(toDelete);
			}
			inPlay.remove(oldPos);
			ArrayList<int[]> newJumps = jumps(newPos, inPlay, fixed);
			if(newJumps.isEmpty()){
				fixed.add(newPos);
			}else{
				inPlay.add(newPos);
			}
			//call jumps on new position and add to fixed if none
			//call jumps on old position, check fixed.contains(jumps[i]), if true call jumps on jumps[i] and switch from fixed to positions if there are possible jumps
		}

		sol[0] = inPlay.size()+fixed.size(); 
		if(sol[0]==1){ //no better solution for the board
			A2_Q1.last = sol;
			return;
		}
		
		//if positions.size()==0, compare to previous solution last and replace if better, do nothing o/w
		if(inPlay.size()==0){
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
		
		moveBall(positions(board), new int[2], null, null);
		A2_Q1.last[1] = A2_Q1.initialNumOfBalls-A2_Q1.last[0];

		return A2_Q1.last;
	}

}

