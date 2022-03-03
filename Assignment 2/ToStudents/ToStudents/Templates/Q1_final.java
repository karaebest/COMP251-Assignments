package Templates;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Q1_final {
	final static int HEIGHT = 5;
	final static int WIDTH = 9; 

	final static String TEST_FOLDER = "Q1";

	public static int[][] positions(int[][] oldBalls, int[] newPos, int[] tD, int[] oP){ //merge with main func, they run simultaneously

		int[][] balls = new int[33][2];
		int b=0;
		int[] p;
		int k = 0;

		while(!(oldBalls[b][0]==0 && oldBalls[b][1]==0)){
			p = oldBalls[b];
			System.out.println(p[0]+" "+p[1]);
			if((!p.equals(tD)) && !p.equals(oP)){
                balls[k++] = oldBalls[b].clone();
            }
			b++;
		}
		balls[k] = newPos.clone();
		return balls;
	}

	public static int[][] jumps(int[] pos, String[][] board){ //returns list of adjacent balls to ball pos that can be newPosT over

		int[][] jump = new int[4][2];
		int i = pos[0];
		int j = pos[1];
		int k = 0;

		if(i+2<5 && board[i+2][j].equals(".") && board[i+1][j].equals("o")){ //space above
			jump[k][0] = i+1;
			jump[k++][1] = j;
		}
		if(i-2>=0 && board[i-2][j].equals(".") && board[i-1][j].equals("o")){ //space below
			jump[k][0] = i-1;
			jump[k++][1] = j;
		}
		if(j+2<9 && board[i][j+2].equals(".") && board[i][j+1].equals("o")){ //space right
			jump[k][0] = i;
			jump[k++][1] = j+1;
		}
		if(j-2>=0 && board[i][j-2].equals(".") && board[i][j-1].equals("o")){ //space left
			jump[k][0] = i;
			jump[k][1] = j-1;
		}

		return jump;
		
	}

	public static int moveBall(int[][] oldBalls, String[][] oldBoard, int minB){
		int[] empty = new int[2];
		int opt = minB;

		if(minB==1){ //no better minB for the board
			return opt;
		}
		
		int b = 0;
		int j;
		
		while(!(oldBalls[b][0]==0 && oldBalls[b][1]==0)){

			int[][] jump = jumps(oldBalls[b], oldBoard);
			
			j = 0;
			while(!(jump[j][0]==0 && jump[j][1]==0)){

				String[][] board = oldBoard.clone();
				
				int[] nP = oldBalls[b].clone();
				if(jump[j][0]!=oldBalls[b][0]){ //find newPos
					nP[0] = (jump[j][0]<oldBalls[b][0])?nP[0]-2:nP[0]+2;
				}else{
					nP[1] = (jump[j][1]<oldBalls[b][1])?nP[1]-2:nP[1]+2;
				}

				int[][] balls = positions(oldBalls, nP, jump[j], oldBalls[b]);
				board[jump[j][0]][jump[j][1]] = ".";
				board[oldBalls[b][0]][oldBalls[b][1]] = ".";
				board[nP[0]][nP[1]] = "o";
				minB--;

				opt = moveBall(balls, board, minB);
				opt = opt<=minB?opt:minB;
				j++;
			}
			b++;
		}
		return opt;
	
	}
	
	public static int[] game(String[][] board){

		int[][] balls = new int[33][2];
		int i=0;
		int b=0;
		int initialB;
		int[] opt = new int[2];

		while(i<board.length){
			for(int j=0; j<9; j++){
				if(board[i][j].equals("o")){
					balls[b][0] = i;
					balls[b++][1] = j;
				}
			}
			i++;
		}
		initialB = b;
		opt[0] = moveBall(balls, board, b);
		opt[1] = initialB - opt[0];
		return opt; 
	}

	public static void main(String[] args) {
		File f = new File(TEST_FOLDER);
		for (String name : f.list()) {
			if (name.endsWith(".in")) {
				try {
					File in = new File(TEST_FOLDER + "/" + name);
					File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length()-3)+".ans");
					Scanner in_scan = new Scanner(in);
					Scanner out_scan = new Scanner(out);
					System.out.printf("Attempting file %s\n", name);
					int n = in_scan.nextInt();
					in_scan.nextLine();
					for (int cs = 0; cs < n; cs++) {
						String[][] board = new String[HEIGHT][WIDTH];
						for (int i = 0; i < HEIGHT; i++) {
							String line = in_scan.nextLine();
							for (int j = 0; j < WIDTH; j++) {
								board[i][j] = new String(new char[]{line.charAt(j)});
							}
						}
						int[] got = game(board);
						int expected_0 = out_scan.nextInt();
						int expected_1 = out_scan.nextInt();
						if (got[0] != expected_0 || got[1] != expected_1) {
							System.out.printf("Expected %d %d but got %d %d\n", expected_0, expected_1, got[0], got[1]);
						} else {
							System.out.printf("Passed test %d\n", cs);
						}
						try {
							in_scan.nextLine(); // Skip empty line
						} catch (NoSuchElementException e) {
							
						}
					}
					
					in_scan.close();
					out_scan.close();
				} catch (FileNotFoundException e) {
					System.out.println(e);
				}
			}
		}
	}

}


