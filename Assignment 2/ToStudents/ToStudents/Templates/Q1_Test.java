package Templates;

import java.util.*;

public class Q1_Test {
    public static void main(String[] args){
        String[][] board = new String[][] {{"#","#","#",".","o",".","#","#","#"},
            {".",".",".","o","o",".","o",".","."},
            {".",".","o",".",".",".","o",".","."},
            {"o",".",".","o",".",".",".",".","."},
            {"#","#","#",".",".",".","#","#","#"}};
        int[] output = Q1_final.game(board);
        System.out.println("Answer:"+output[0]+" "+output[1]);
    }
    
}
