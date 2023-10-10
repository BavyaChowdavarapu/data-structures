public class Grid
{
    private static final int SIZE = 10;
    int[][] pixels = new int[SIZE][SIZE];

    //stack for the pixels
    Stack<int[]> pix = new Stack<>();

    /**
     * Flood fill, starting with the given row and column.
    */
    public void floodfill(int row, int column)
    {
        //created a new int array for the coordinates of the pixel
        int[] position = new int[]{row, column};
        pix.push(position);

        
        int count = 1;
        while(!pix.isEmpty()) {
            position = pix.pop();
            row = position[0];
            int col = position[1];

            if (pixels[row][col] == 0) {
                if (row != 0) {
                    pix.push(new int[]{row-1, col});
                }
                if (col != 9) {
                    pix.push(new int[]{row, col+1});
                }
                if (row != 9) {
                    pix.push(new int[]{row+1, col});
                }
                if (col != 0) {
                    pix.push(new int[]{row, col-1});
                }

                pixels[row][col] = count;
                count++;
            }
        }

        System.out.println(toString());
        
    }

    @Override
    public String toString()
    {
        String r = "";
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
                r = r + String.format("%4d", pixels[i][j]);
            r = r + "\n";
        }
        return r;
    }
}
