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
    private static ArrayList<Set<Integer>> squares;//stores number of squares in sudoku board
    private Set<Integer> nums; //populating potential numbers 


    //makes 2d sudoku board w/values
    public Sudoku(String fileName) {    
        // reads the puzzle file
        try (Scanner in = new Scanner(new File(fileName))) { //constructor 

            //creates a 2D copy of the sudoku board and fills in all of the values 
            //with the numerical value or a 0 if the space is empty
            this.grid = new int[boardSq][boardSq];

            for (int row = 0; row < boardSq; row++) {
                String line = in.next();

                for (int col = 0; col < boardSq; col++) {
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
        // create the list of sets for each row (this.rows)                                          // done 
        // row has 9 squares (this is an arraylist of sets )                                         // done
        // create the list of sets for each col (this.cols)                                          // done
        // each collumn is its own set                                                               // done


        //this adds a hash set for each row and column in the sudoku board to their respective lists 
        //the board will have the same number of rows as columns and this is also equal to the number of 
        //numbers in each row or column
        //this also sets the initial capacity of each row/col set to the number of numbers in each row/col respectively 
        
        //initializes the rows arraylist
        this.rows = new ArrayList<Set<Integer>>();
        
        //adds the values to the hashsets and the hashsets to the arraylist
        int rC = 0; //keeps track of the index of the row as shown in the 2d array 
        int numSpace = 0; //keeps track of the column of each number in the row
        while (rC < this.boardSq){
            Set<Integer> rowHashSet = new HashSet<>();
            
            //fills in the values for each row into their respective hashsets before adding it to the rows arraylist
            while (numSpace < boardSq){
                rowHashSet.add(this.grid[rC][numSpace]);
                numSpace++; //moves to the next column over
            }
            this.rows.add(rowHashSet);
            rC++; //moves to the next row below 
        }

       
        //initializes the cols arraylist
        this.cols = new ArrayList<Set<Integer>>();
        
        //adds the values to the hashsets and the hashsets to the arraylist
        int cC = 0; //keeps track of the index of the column as shown in the 2d array 
        numSpace = 0; //resets this cariable to 0 to keep track of the row of each number in the column
        
        while (cC < this.boardSq){
            Set<Integer> colHashSet = new HashSet<>();
            
            //fills in the values for each column into their respective hashsets before adding it to the cols arraylist
            while (numSpace < boardSq){
                colHashSet.add(this.grid[numSpace][cC]);
                numSpace++; //moves to the next row below
            }
            this.cols.add(colHashSet);
            cC++; //moves to the next column over 
        }
        
        //------------------------------------------------------------------------------------------------------------- done 
        
        
        /* the squares are added to the list row-by-row:                                        //
        //create set of all individual squares                                                  //
            0 1 2 
            3 4 5
            6 7 8
         */
        // ...

        //the first square has the first M rows and the first M columns 
        // ---------------- MAKE A NESTED FOR LOOP WITH THE + SOME VARIABLE TO KEEP TRACK OF HOW MANY MULTIPLES OF THE 
        //NUMBER OF ROWS AND COLS NEED TO BE ADDED TO THE I AND J VARIABLES EACH RUN THROUGH OF THE LOOP
        
        // create the list of sets for each square (this.squares)                                    //

        //this creates a has set that has an initial capacity of M (the number of squares in a sudoku, which is also 
        //the number of numbers in a row within each square)
        
        //initializing the squares arraylist 
        squares = new ArrayList<Set<Integer>>(); 
        
        //adds the squares (with the values of each number in the square) to the square arraylist 
        int rowCounter = 0; //moves the while loop to the next row 
        while (rowCounter < rowSq){
            int a = 0 + (rowCounter*rowSq); //keeps track of the larger board row that the square is in 
            int b = 0;  //keeps track of the larger board column that the square is in 
            int sRow = 0;  // keeps track of the row of each number within the board square 
            int sCol = 0;  //keeps track of the column of each number within the board square 
        
            while (a < boardSq){ //row index of squares (top left number) are 0, 3, 6 
                while (b < boardSq){ //col index of squares (top left number) are 0, 3, 6
                    Set<Integer> sqHashSet = new HashSet<>();
                    
                    //adding the values of each number to the square hash set before adding it to the squares array list 
                    for (sRow = a; sRow < (a + rowSq); sRow++){ //goes through every row in square 
                        
                        for (sCol = b; sCol < (b + rowSq); sCol++){ //goes through every column in square 
                            sqHashSet.add(this.grid[sRow][sCol]); //adds the numerical value 
                        }
                    }
                    this.squares.add(sqHashSet);
                    b+=rowSq; //moves to the next larger square board column over
                }
                a+=rowSq;    //moves to the next larger square board row below 
                
            }
            
            rowCounter++; //moves to the next square row below 
        }
    
    
        //--------------------------------------------------------------------------------------------------------------- done
        
        
        // create a hash set for [1..9] (this.nums)                                                      //
        // set of the numbers 1-9 (all possible numbers to be used in the sudoku)                        //
        
        //creates hash set for all of the possible numbers to be added to each space 
        this.nums = new HashSet<Integer>();
        for (int i = 1; i <(boardSq + 1); i++)
        {
            this.nums.add(i);
        }
        
        //-------------------------------------------------------------------------------------------------------------done 
        
        
        // visually inspect that all the sets are correct
        for (int row = 0; row < boardSq; row++) {
            System.out.println("row " + row + ": " + this.rows.get(row));
        }
        for (int col = 0; col < boardSq; col++) {
            System.out.println("col " + col + ": " + this.cols.get(col));
        }
        for (int square = 0; square < boardSq; square++) {
            System.out.println("square " + square + ": " + this.squares.get(square));
        }
        System.out.println(this.nums);
        
        //--------------------------------------------------------------------------------------------------done 
        
        
        
    
    }
    
    public boolean solve() {
        // find an empty location, if any                                                               //
        // then finds which row and which column it's in                                                //
        boolean finished = true;
        int rowIndex = -1; 
        int colIndex = -1;
        for (int row = 0; row < boardSq && finished; row++) {
            for (int col = 0; col < boardSq && finished; col++) {
                if (this.grid[row][col] == 0) {
                    finished = false;
                    rowIndex = row; //row index
                    colIndex = col; //col undex
                }
            }
        }

        // the board is complete; we solved it
        if (finished) {
            return true;
        } 
            
        
        //---------------------------------------------------------------------------------------done 
        
        // get all possible numbers for the row and column we are trying to populate      //
        /*
            Create a new set based on the this.nums and remove all elements in the sets
            corresponding to nextRow, nextCol, and the corresponding square (use the
            removeAll method). //need to use the size of the array list and can be variable 

            Properly indexing the squares list of sets is tricky. Verify that your
            algorithm is correct.
         */
        
        //creates a hash set for all of the potential values for each number spot
        Set<Integer> vals = new HashSet<Integer>(); 
        
        //adds all of the potential values stores in the nums set to the potential value hashSet
        vals.addAll(this.nums);
        
        //removes all of the values from the potential values list that are already in the square
        vals.removeAll(this.rows.get(rowIndex)); //goes to the rows array list and looks through the entire row 
        vals.removeAll(this.cols.get(colIndex)); //goes to the cols array list and looks through the entire column
        
        
        //getting the position of the square that the zero is in 
        int zRIndex = (rowIndex / rowSq) * 3; //gets the index of the row of the zero in the grid
        int zCIndex = (colIndex / rowSq) * 3; //gets the index of the col of the zero in the grid
        
        //removing the already filled-in numbers in the square from the list of potential values 
         for (int r = zRIndex; r < (zRIndex + 3); r++)
        {
            for (int c = zCIndex; c < (zCIndex + 3); c++)
            {
                vals.remove(this.grid[r][c]);
            }
        }
        
        
        // if there are no possible numbers, we cannot solve the board in its current state
        if (vals.isEmpty()) {
            return false;
        }
        
        
        //done------------------------------------------------------------------
        
        
          for (Integer possibleNum : vals) {
            // update the grid
            this.grid[rowIndex][colIndex] = possibleNum;

            // update the sets with that number too
            this.rows.get(rowIndex).add(possibleNum);
            this.cols.get(rowIndex).add(possibleNum);

            // calculating the square index - where it is in this.squares
            // and adding that to the set in this.squares
            int squareIndex = (rowIndex / rowSq) * rowSq + (colIndex / rowSq);
            this.squares.get(squareIndex).add(possibleNum);
            
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
                this.grid[rowIndex][colIndex] = 0;
                this.rows.get(rowIndex).remove(possibleNum);
                this.cols.get(colIndex).remove(possibleNum);
                this.squares.get(squareIndex).remove(possibleNum);
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
    
    
    public static void main (String[] args){
        //reading the sudoku board file
        String fileName = "puzzle1.txt";

        //creating a sudoku board with the information read from the puzzle1 file 
        Sudoku solver = new Sudoku(fileName);
        
        //test printing the 2d array version of the board 
            for (int i = 0; i < solver.grid.length; i ++){
            for (int j = 0; j < solver.grid[0].length; j++){
                System.out.print(solver.grid[i][j]);
            }
            System.out.println("");
        }
        
        //works ------------------------------------------------------------------------
        
        
        //testing how many hashsets are in each row, col, and square sets 
        int rCount = 0;
        while (rCount < solver.rows.size()){
            rCount++;
        }
        
        int cCount = 0;
        while (cCount < solver.cols.size()){
            cCount++;
        }
        
        //works -------------------------------------------------------------------------
        
        int sCount = 0;
        while (sCount < solver.squares.size()){
            sCount++;
        }
        
        System.out.println("Expected rows: 9 code: " + rCount);     
        System.out.println("Expected cols: 9 code: " + cCount);
        System.out.println("Expected squares: 9 code: " + sCount);   
        
        //works -------------------------------------------------------------------------------
        
        //test printing out all of the squares
        for (Set<Integer> thisSquare : squares){
            System.out.println(thisSquare);
        }
        
        //works -----------------------------------------------------------------------------------
        
        
        //testing the solver 
        System.out.println(solver);
        if (solver.solve()) {
            System.out.println("Solved!");
            System.out.println(solver);
        } else {
            System.out.println("Unsolveable...");
        }
        
        
    }
}
