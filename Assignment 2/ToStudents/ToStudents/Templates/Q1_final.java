package Templates;

import java.util.*;

public class Q1_final {
	public static int[] opt = new int[2];
	public static int initialNumOfBalls = 0;

	public static ArrayList<int[]> positions(ArrayList<int[]> oldBalls, int[] newPos, int[] tD, int[] oP){ //to insert initial ball positions into ArrayList

		ArrayList<int[]> balls = new ArrayList<>();

        Iterator<int[]> b = oldBalls.iterator();

        while(b.hasNext()){
            int[] p = b.next();
            if((p[0]!=tD[0] || p[1]!=tD[1]) && (p[0]!=oP[0] || p[1]!=oP[1])){
                balls.add(p);
            }
        }
		balls.add(newPos);
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

	

	public static void moveBall(ArrayList<int[]> oldBalls, int[] newPos, int[] toDelete, int[] oldPos, String[][] board){
		ArrayList<int[]> balls = new ArrayList<>();
		if(oldPos[0]!=-1){  
            System.out.println("Before:"+oldBalls.size());
            balls = positions(oldBalls, newPos, toDelete, oldPos);
            System.out.println("After:"+balls.size());
            board[toDelete[0]][toDelete[1]] = ".";
			board[oldPos[0]][oldPos[1]] = ".";
            board[newPos[0]][newPos[1]] = "o";
		}else{
            balls.addAll(oldBalls);
        }

        int minB = balls.size();
		if(minB==1){ //no better minB for the board
			Q1_final.opt[0] = minB;
			return;
		}
		
		Iterator<int[]> b = balls.iterator();
		int j = 0;
		while(b.hasNext()){ //iterate through balls
			int[] temp = b.next();
			ArrayList<int[]> jumps = jumps(temp, board);
			if(jumps.isEmpty()){
				j++;
				continue;
			}

			Iterator<int[]> jp = jumps.iterator();
			while(jp.hasNext()){ //iterate through balls to jumps
				int[] jpTemp = jp.next(); //ball to delete
                int[] nP = temp;
			    if(jpTemp[0]!=temp[0]){ //newPos
				    nP[0] = (jpTemp[0]<temp[0])?nP[0]-2:nP[0]+2;
			    }else{
				    nP[1] = (jpTemp[1]<temp[1])?nP[1]-2:nP[1]+2;
			    }
                moveBall(balls, nP, jpTemp, temp, board);
			}
		}

        if(j==minB){
            Q1_final.opt[0] = opt[0]<=minB?opt[0]:minB;
            return;
        }

		
	
	}
	
	public static int[] game(String[][] board){

		ArrayList<int[]> balls = new ArrayList<>();
		int i=0;
		while(i<board.length){
			for(int j=0; j<9; j++){
				if(board[i][j].equals("o")){
					balls.add(new int[] {i,j});
				}
			}
			i++;
		}
        Q1_final.opt[0] = balls.size();
        Q1_final.initialNumOfBalls = balls.size();
		int[] temp = new int[] {-1,-1};
		moveBall(balls, temp, temp, temp, board); 
		Q1_final.opt[1] = Q1_final.initialNumOfBalls-Q1_final.opt[0];

		return Q1_final.opt;
	}

}


