import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SudokuSolver {
    private final int M = 3;//number of squares in a row
    private final int N = M * M; //number of squares in a sudoku 
    private int[][] grid;//2d array of objects 
    private ArrayList<Set<Integer>> rows;//stores rows
    private ArrayList<Set<Integer>> cols;//stores cols
    private ArrayList<Set<Integer>> squares;//stores squares 
    private Set<Integer> nums; //populating potential numbers 

    

    public SudokuSolver(String fileName) {    
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

        //--------------------------------------------------------------------------------------------------------------
        // create the list of sets for each row (this.rows)                                          //
        // row has 9 squares (this is an arraylist of sets )                                         //
        // create the list of sets for each col (this.cols)                                          //
        // each collumn is its own set                                                               //


        //this adds a hash set for each row and column in the sudoku board to their respective lists 
        //the board will have the same number of rows as columns and this is also equal to the number of 
        //numbers in each row or column
        //this also sets the initial capacity of each row/col set to the number of numbers in each row/col respectively 
        int counter = 0;
        
        while (counter < N){
            rows.add(counter, new HashSet<Integer>(N));
            cols.add(counter, new HashSet<Integer>(N));
        }

       
        //-------------------------------------------------------------------------------------------------------------

        // create the list of sets for each square (this.squares)                                    //

        //this creates a has set that has an initial capacity of M (the number of squares in a sudoku, which is also 
        //the number of numbers in a row within each square)
        int count = 0;
        while (count < N){
            squares.add(count, new HashSet<Integer>(M));
        }

        //--------------------------------------------------------------------------------------------------------------- done


        /* the squares are added to the list row-by-row:
        //create set of all individual squares 
            0 1 2
            3 4 5
            6 7 8
         */
        // ...

        //the first square has the first M rows and the first M columns 
        // ---------------- MAKE A NESTED FOR LOOP WITH THE + SOME VARIABLE TO KEEP TRACK OF HOW MANY MULTIPLES OF THE 
        //NUMBER OF ROWS AND COLS NEED TO BE ADDED TO THE I AND J VARIABLES EACH RUN THROUGH OF THE LOOP
        
        for (int i = 0, j = 0; i < M && j < M; ){

        }









        // create a hash set for [1..9] (this.nums)
        // set of the numbers 1-9 (all possible numbers to be used in the sudoku)

        // visually inspect that all the sets are correct
        for (int row = 0; row < N; row++) {
            System.out.println("row " + row + ": " + this.rows.get(row));
        }
        for (int col = 0; col < N; col++) {
            System.out.println("col " + col + ": " + this.cols.get(col));
        }
        for (int square = 0; square < N; square++) {
            System.out.println("square " + square + ": " + this.squares.get(square));
        }
        System.out.println(this.nums);
    }

    public boolean solve() {
        // find an empty location, if any
        boolean finished = true;
        int nextRow = -1;
        int nextCol = -1;
        for (int row = 0; row < N && finished; row++) {
            for (int col = 0; col < N && finished; col++) {
                if (this.grid[row][col] == 0) {
                    finished = false;
                    nextRow = row;
                    nextCol = col;
                }
            }
        }

        // the board is complete; we solved it
        if (finished) {
            return true;
        }

        // get all possible numbers for the row and column we are trying to populate
        /*
            Create a new set based on the this.nums and remove all elements in the sets
            corresponding to nextRow, nextCol, and the corresponding square (use the
            removeAll method). //need to use the size of the array list and can be variable 

            Properly indexing the squares list of sets is tricky. Verify that your
            algorithm is correct.
         */
        Set<Integer> possibleNums = new HashSet<Integer>();
        possibleNums.addAll(this.nums);
        
        // ...

        // if there are no possible numbers, we cannot solve the board in its current state
        if (possibleNums.isEmpty()) {
            return false;
        }

        // try each possible number
        for (Integer possibleNum : possibleNums) {
            // update the grid and all three corresponding sets with possibleNum
            // ...

            // recursively solve the board
            if (this.solve()) {
                // the board is solved!
                return true;
            } else {
                /*
                 Undo the move before trying another possible number by setting the corresponding
                 element in the grid back to 0 and removing possibleNum from all three corresponding
                 sets.
                 */
                // ...
            }
        }

        return false;
    }

    public String toString() {
        String str = "";

        for (int[] row : grid) {
            for (int val : row) {
                str += val + "\t";
            }

            str += "\n";
        }

        return str;
    }


    //runs the code with the sudoku board in puzzle1.txt
    public static void main(String[] args) {
        String fileName = "src/puzzle1.txt";

        SudokuSolver solver = new SudokuSolver(fileName);
        System.out.println(solver);
        if (solver.solve()) {
            System.out.println("Solved!");
            System.out.println(solver);
        } else {
            System.out.println("Unsolveable...");
        }
    }
}