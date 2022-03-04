package Templates;

import java.util.*;

public class Q1_Test {
    public static void main(String[] args){
        String[][] board = new String[][] {{"#","#","#",".",".",".","#","#","#"}, //13
            {".","o",".",".",".","o","o",".","."},
            {".",".","o","o",".","o",".",".","o"},
            {".","o",".",".",".",".",".",".","."},
            {"#","#","#",".",".",".","#","#","#"}};
        int[] output = A2_Q1.game(board);
        System.out.println("Answer:"+output[0]+" "+output[1]);
    }
    
}
