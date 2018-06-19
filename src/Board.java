import java.util.Scanner;

/**
 * Created by Andrea on 6/12/2018.
 */
public class Board {
    char[][] board= new char[15][15];
    boolean[][] isFilled= new boolean[15][15];
    int spaces=0;

    /**
     * This method adds a tile to the board, this method takes the x and y coordinates and adds them to the board, it does
     * not check if the coordinate is already taken that is chelcked else where
     * @param x the x coordinate of the new tile
     * @param y the y coordinate of the new tile
     * @param player the current player placing the tile
     */
    public boolean addCoordinate(int x,int y,char player){
        if(isFilled[y][x]) {
            System.out.println("Choose New Coordinates");
            return false;
        }
        board[y][x]=player;
        isFilled[y][x]=true;
        spaces++;
        return true;
    }

    /**
     * Checks if the board is full
     * @return true if board is full false otherwise
     */
    public boolean isFull(){
        if (spaces>=225)
        return true;
        return false;

    }

    /**
     * This method helps to determine if a new coordinate is the winning coordinate by calling on various helper methods
     * that call themselves recursively and then checking if the two complementing helper methods return a sum of 5
     * @param x x coordinate to start from this is the x coordinate of the new tile
     * @param y y coordinate to start from this is the y coordinate of the new tile
     * @param player this is who is currently playing it is useful to check the other tiles surrounding the new tile
     * @return true if the tile is a winning tile false otherwise.
     */
    public boolean playerWon(int x, int y,char player){
                int currpoints=0;
            if((currpoints=(upCheck(x,y,player)+downCheck(x,y,player)))>=6) {
               // System.out.println("Current Points:" + currpoints);
                return true;
            }
            if(leftCheck(x,y,player)+rightCheck(x,y,player)>=6)
                return true;
            if(leftUpCheck(x,y,player)+rightDownCheck(x,y,player)>=6)
                return true;
            if(leftDownCHeck(x,y,player)+rightUpCheck(x,y,player)>=6)
                return true;
            return false;

    }

    private int upCheck(int x, int y,char player){
        try {
            if (board[y][x] == player) {
                return 1 + upCheck(x , y-1, player);
            }
        }catch (NullPointerException e){
            return 0;
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
        return 0;
    }
    private int downCheck(int x, int y, char player){
        try {
            if(board[y][x]== player)
                return 1+downCheck(x,y+1,player);

        }catch (NullPointerException e){
            return 0;
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
        return 0;
    }
    private int leftCheck(int x, int y, char player){
        try {
            if (board[y][x] == player)
                return 1 + leftCheck(x-1, y, player);
        }catch (NullPointerException e){
            return 0;
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
        return 0;
    }
    private int rightCheck(int x, int y, char player){
        try {
            if (board[y][x] == player) {
                return 1 + rightCheck(x+1, y, player);
            }
        }catch (NullPointerException e){
            return 0;
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
        return 0;
    }
    private int leftUpCheck(int x, int y, char player){
        try{
            if(board[y][x]==player){
                return 1+leftUpCheck(x-1,y-1,player);
            }
        }catch (NullPointerException e){
            return 0;
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
        return 0;
    }
    private int rightDownCheck(int x, int y, char player){
        try{
            if(board[y][x]==player)
                return 1+rightDownCheck(x+1,y+1,player);
        }catch (NullPointerException e){
            return 0;
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
        return 0;
    }
    private int leftDownCHeck(int x,int y, char player){
        try {
            if(board[y][x]==player){
                return 1+leftDownCHeck(x-1,y+1,player);
            }
        }catch (NullPointerException e){
            return 0;
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
        return 0;
    }
    private int rightUpCheck(int x, int y, char player){
        try {
            if(board[y][x]== player)
                return 1+rightUpCheck(x+1,y-1,player);
        }catch (NullPointerException e){
            return 0;
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
        return 0;
    }


    public boolean isFilled(int x, int y) {
        if(isFilled[y][x])
            return true;
        return false;
    }
}
