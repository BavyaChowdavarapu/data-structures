//THIS IS JUST A PIECE-BY-PIECE VERSION OF THE SUDOKUSOLVER FILE SO I CAN RUN EACH PART INDEPENDENTLY

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Sudoku {
    
    private final int rowSq = 3;//number of squares in a row of squares
    private final int boardSq = rowSq * rowSq; //number of squares in a full sudoku board
    private int[][] grid;//2d array of objects --> 2d version of the sudoku board 
    private ArrayList<Set<Integer>> rows;//stores number of rows in sudoku board
    private ArrayList<Set<Integer>> cols;//stores number of cols in sudoku board
    private ArrayList<Set<Integer>> squares;//stores number of squares in sudoku board
    private Set<Integer> nums; //populating potential numbers 


    //makes 2d sudoku board w/values
    public Sudoku(String fileName) {    
        // reads the puzzle file
        try (Scanner in = new Scanner(new File(fileName))) { //constructor   

            //creates a 2D copy of the sudoku board and fills in all of the values 
            //with the numerical value or a 0 if the space is empty
            this.grid = new int[N][N];

            for (int row = 0; row < N; row++) {
                String line = in.next();

                for (int col = 0; col < N; col++) {
                    String strVal = line.substring(col, col + 1);
                    int number;
                    if (strVal.equals("x")) { //x = 0 = blank 
                        number = 0;
                    } else {
                        number = Integer.parseInt(strVal);
                    }
                    this.grid[row][col] = number; //stores numbers into grid
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open: " + fileName);
        }
        //test printing the board
        for (int i = 0; i < grid.rows; i ++){
            for (int j = 0; j < grid.cols; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
    }

    
}
