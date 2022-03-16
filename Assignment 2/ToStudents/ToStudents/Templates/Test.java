package ToStudents.ToStudents.Templates;

import java.util.*;

public class Test {
    public static void main(String[] args){
        // String[][] board = new String[][] {{"#","#","#",".",".",".","#","#","#"}, //13
        //     {".","o",".",".",".","o","o",".","."},
        //     {".",".","o","o",".","o",".",".","o"},
        //     {".","o",".",".",".",".",".",".","."},
        //     {"#","#","#",".",".",".","#","#","#"}};
        // int[] output = A2_Q1.game(board);
        // System.out.println("Answer:"+output[0]+" "+output[1]);

        int[] plates = new int[]{4,
            900,
            500,
            498,
            4};
        
        System.out.println(A2_Q2.weight(plates));
    }
    
}
