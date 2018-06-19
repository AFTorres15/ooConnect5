/**
 * Created by Andrea on 6/12/2018.
 */
public class ConsoleUI {
    /** Print Board
     * This method is used to create the visual board it takes in a 2d char board to take the "pieces" of the board
     * @param board this is the board where the information on what spaces are taken are retrieved from.
     */
    public void printBoard(char[][] board) {
        System.out.println("y"+'\\'+"x   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15");
        int rowNum;
        for(int i=0;i<15;i++){
            printBetween();
            rowNum=i;
            if(rowNum<9)
            System.out.print((rowNum+1)+"  ");
            else
                System.out.print((rowNum+1)+" ");
           try {
               printRow(board[i]);
           }catch (ArrayIndexOutOfBoundsException e){
               char[] fakeArray={' '};
               printRow(fakeArray);
           }

        }
        printBetween();

    }

    /**
     * This is a helper method that is used to help print out the inbetween lines where no pieces are located
     */
    private void printBetween(){
        System.out.print("   ");
       for(int i=0;i<15;i++)
        System.out.print("+---");
        System.out.println("+");
        //System.out.println();
}

    /**
     * This is a helper method to the printBoard method it prints the parts with the board pieces.
     * @param row
     */
    private void printRow(char[] row){
        String currChar;
        if(row==null) {
            currChar = " ";
        }
        for(int i=0;i<15;i++){
            try {
                currChar= (Character.toString(row[i]));

            }catch (ArrayIndexOutOfBoundsException e){
                currChar= " ";
            }
           // System.out.println(" contents of row i is"+row[i]+"*");
            if (currChar.isEmpty() || currChar == null || currChar.equals(null) || row[i]=='\u0000') {

                currChar = " ";
            }
            System.out.print("| " + currChar + " ");
        }

        System.out.println('|');
}

}
