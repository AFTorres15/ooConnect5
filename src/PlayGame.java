import java.util.Random;
import java.util.Scanner;

/**
 * Created by Andrea on 6/17/2018.
 */
public class PlayGame {
    private static ConsoleUI ui= new ConsoleUI();
    private static Board board= new Board();

    /**
     * This helps with the flow of the game, it uses a switch statement to switch between player 1 and 2
     * it calls on
     */
    public PlayGame(){
        char currPlayer='1';
        boolean continueGame=true;
        int gameStatus=1;
        System.out.println("Welcome To Connect 5");
        ui.printBoard(board.board);
        while(continueGame) {
            System.out.println("_________________________________________________________________________");

            switch (gameStatus) {
                case 0:
                    continueGame=false;
                    break;
                case 1://player 1
                    gameStatus=getCoordinatesFromUser(currPlayer);
                    currPlayer='2';
                    ui.printBoard(board.board);
                    break;
                case 2://player 2
                    gameStatus=getCoordinatesFromUser(currPlayer);
                    currPlayer='1';
                    ui.printBoard(board.board);
                    break;
                case 3://invalid original player input
                    gameStatus=getCoordinatesFromUser(currPlayer);
                    break;
                default:
                    if(currPlayer=='1'){
                        currPlayer='2';
                        addRandomCoordinates(currPlayer);
                    }else {
                        currPlayer = '1';
                        addRandomCoordinates(currPlayer);
                    }

            }
        }

    }

    /**
     * This method helps to get coordinates from the player and calls on other helper methods to check if the coordinates
     * are good available coordinates. it also adds the coordinates to the board and checks if the new tile is the winning tile
     * @param currPlayer helps in the placing of the tile
     * @return this statemnt helps detrimine the state of the game used in the switch statement
     */
    private static int getCoordinatesFromUser(char currPlayer){
        String userInput;
        int[]  coordinates;
        System.out.println("Player "+currPlayer+", please enter your next move x y  or -1 to quit:");
        Scanner scan = new Scanner(System.in);
        userInput=scan.nextLine();
        coordinates=getCoodinatesFromString(userInput);
        if(coordinates[0]==(-2))
            return 0;//do something that ends the game 0 means that the game should end
        if(checkIfCoordinatesAreViable(coordinates)){
            System.out.println("Your coordinates are X:"+(coordinates[0]+1)+" Y:"+(coordinates[1]+1));
            board.addCoordinate(coordinates[0],coordinates[1],currPlayer);
            if(checkWin(coordinates[0],coordinates[1],currPlayer))
                return 0;//will force game to end
            if(currPlayer=='1')
                return 2;//helps to switch players
            if(currPlayer=='2')
                return 1;//helps to switch player

        }
        return 3;//because points were not good
    }

    /**
     * This helper method takes the string of given information form the user and turns it into an int array that will
     * hold the x and y value
     * @param coodinates this string is the raw input from the scanner
     * @return coordinates[0] is the x value and coordinates[1] is the y value
     */
    private static int[] getCoodinatesFromString(String coodinates){
        int[] pointXY;

        String[] splitCoodrinates = coodinates.split(" ");
        pointXY = new int[splitCoodrinates.length];

        for (int i = 0; i < pointXY.length; i++) {
            try{
                pointXY[i] = Integer.parseInt(splitCoodrinates[i]) - 1;
            }catch (NumberFormatException e){
                System.out.println("In valid entry");
                pointXY[i]=0;

            }
        }

        return  pointXY;
    }

    /**
     * this helper method calls the checkcoordinatesfit on the table to make sure that the points are withing the talbe range
     * and thne calls on board.isFilled to check if the coordinate is occupied
     * @param coordinates [0] is the x coordinate [1] is the y coordinate
     * @return true if valid cooridnate false otherwise
     */
    private static boolean checkIfCoordinatesAreViable(int[] coordinates){//coordinates[0] is x, coordinates[1] is y.
        if(checkCoodrinatesFitOnTable(coordinates[0],coordinates[1])){
            if(!board.isFilled(coordinates[0],coordinates[1]))
                return true;
        }
        return false;

    }

    /**
     * Checks if coordinates fit within the parameters of the 15X15 table
     * @param x x coordinate "coloms"
     * @param y y corrdinate "rows"
     * @return true if points are withing range false otherwise
     */
    private static boolean checkCoodrinatesFitOnTable(int x, int y){
        return !(x < 0 || y < 0 || x > 15 || y > 15);
    }

    /**
     * This method helps to check if the desired new tile is a winning tile calls on the board.playerWon method found in board
     * @param x x coordinate of new desired tile
     * @param y y coordinate of new desired tile
     * @param player current player
     * @return true if it is a winning tile false otherwise
     */
    private static boolean checkWin(int x, int y,char player){
        if(board.playerWon(x, y, player)) {
            System.out.println("Congratulations Player " + player + " you won");
            return true;
        }
        return false;
    }

    /**
     * If no good value is given a random tile is generated with this method
     * @return good valid points
     */
    private static int[] makeRandomValidCoordinates(){
        int[] newPoint= new int[2];//0 is x coordinate,1 is y coordinate
        boolean acceptable=false;
        Random rand= new Random();
        while (!acceptable){
            newPoint[0]=rand.nextInt(15);
            newPoint[1]=rand.nextInt(15);
            acceptable=!(board.isFilled(newPoint[0],newPoint[1]));
        }
        return newPoint;
    }

    /**
     * this adds coordinates if the random coordinates are needed.
     * @param currPlayer
     */
    private static void addRandomCoordinates(char currPlayer){
        int[] coordinates=makeRandomValidCoordinates();
        System.out.println("Your coordinates are X:"+coordinates[0]+1+" Y:"+coordinates[1]+1);
        board.addCoordinate(coordinates[0],coordinates[1],currPlayer);
        checkWin(coordinates[0],coordinates[1],currPlayer);
    }




}
