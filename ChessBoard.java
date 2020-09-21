import java.util.ArrayList;
/**
 * This class is the main model of the program which is one of the MVC pattern (Model) class.
 * This class is also implemented Singleton Design Pattern which will only instantiated once.
 */
public class ChessBoard{  // Singleton Model
    /**
     * Store current chessPieces objects in respective 2d-array
     */
    private static GenericChessPiece[][] chessPieces;
    /**
     * Singleton instance
     */
    private static ChessBoard instance = new ChessBoard();
    /**
     * Round, initialize to 1, increment every player turns
     */
    private static int turnCount = 1;
    /**
     * Constants of the row number
     */
    private static final int ROWS = 8;
    /**
     * Constants of the col number
     */
    private static final int COLUMNS = 7;
    /**
     * Increment the turn count by one
     */
    public void incrementTurnCount() {
        turnCount++;
    }

    /**
     * Swap the triangles and pluses every 4 turnCount
     */
    public void trianglePlusTransform(){
        if (turnCount % 4 == 0){
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLUMNS; col++) {
                    if (chessPieces[row][col] != null) {
                        if(chessPieces[row][col] instanceof Triangle) {
                            chessPieces[row][col] = new Plus(chessPieces[row][col].getCoordinate(), chessPieces[row][col].getTeam());
                        }
                        else if(chessPieces[row][col] instanceof Plus){
                            chessPieces[row][col] = new Triangle(chessPieces[row][col].getCoordinate(), chessPieces[row][col].getTeam());
                        }
                    }
                }
            }
        }
    }

    /**
     * Flip the board 180 degress in every turn
     */
    public static void flipBoard() {
        GenericChessPiece[][] tempChessPiece = new GenericChessPiece[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (chessPieces[row][col] != null) {
                    tempChessPiece[ROWS - row - 1][col] = chessPieces[row][col];
                    tempChessPiece[ROWS - row - 1][col].getCoordinate().setY(ROWS - row - 1);
                    if (tempChessPiece[ROWS-row-1][col] instanceof Arrow) {
                        ((Arrow)tempChessPiece[ROWS-row-1][col]).changeDirection();
                    }
                }
            }
        }
        chessPieces = tempChessPiece;
    }
    /**
     * By setting default construtor to private, this will ensure only one ChessBoard instance in the runtime, which correspond to the Singleton Design Pattern
     */
    private ChessBoard() {
        chessPieces = new GenericChessPiece[8][7];
        resetBoard();
    }
    /**
     * The movement logic for the chessPieces, by copy the chessPiece to the destination and nullify the origin.
     * @param fromRow Moves from row no
     * @param fromCol Moves from col no
     * @param toRow Moves to row no
     * @param toCol Moves to col no
     */
    public void moveChessPieces(int fromRow, int fromCol, int toRow, int toCol){
        chessPieces[toRow][toCol] = chessPieces[fromRow][fromCol];
        chessPieces[fromRow][fromCol] = null;
        chessPieces[toRow][toCol].setCoordinate(toCol, toRow);
    }
    /**
     * This will clear the whole board by nullify the whole chessPieces[][]
     */
    public static void clearBoard(){
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                chessPieces[row][col] = null;
            }
        }
    }
    /**
     * This will reset the board to its initial state.
     */
    public static void resetBoard() {
        clearBoard();
        turnCount = 1;
        ChessPieceFactory factory = new ChessPieceFactory();
        chessPieces[0][0] = factory.makeChessPiece("Plus", 0, 0, 2);
        chessPieces[0][1] = factory.makeChessPiece("Triangle", 1, 0, 2);
        chessPieces[0][2] = factory.makeChessPiece("Chevron", 2, 0, 2);
        chessPieces[0][3] = factory.makeChessPiece("Sun", 3, 0, 2);
        chessPieces[0][4] = factory.makeChessPiece("Chevron", 4, 0, 2);
        chessPieces[0][5] = factory.makeChessPiece("Triangle", 5, 0, 2);
        chessPieces[0][6] = factory.makeChessPiece("Plus", 6, 0, 2);
        chessPieces[1][0] = factory.makeChessPiece("Arrow", 0, 1, 2, false, 1);
        chessPieces[1][2] = factory.makeChessPiece("Arrow", 2, 1, 2, false, 1);
        chessPieces[1][4] = factory.makeChessPiece("Arrow", 4, 1, 2, false, 1);
        chessPieces[1][6] = factory.makeChessPiece("Arrow", 6, 1, 2, false, 1);
        chessPieces[7][0] = factory.makeChessPiece("Plus", 0, 7, 1);
        chessPieces[7][1] = factory.makeChessPiece("Triangle", 1, 7, 1);
        chessPieces[7][2] = factory.makeChessPiece("Chevron", 2, 7, 1);
        chessPieces[7][3] = factory.makeChessPiece("Sun", 3, 7, 1);
        chessPieces[7][4] = factory.makeChessPiece("Chevron", 4, 7, 1);
        chessPieces[7][5] = factory.makeChessPiece("Triangle", 5, 7, 1);
        chessPieces[7][6] = factory.makeChessPiece("Plus", 6, 7, 1);
        chessPieces[6][0] = factory.makeChessPiece("Arrow", 0, 6, 1, false, 0);
        chessPieces[6][2] = factory.makeChessPiece("Arrow", 2, 6, 1, false, 0);
        chessPieces[6][4] = factory.makeChessPiece("Arrow", 4, 6, 1, false, 0);
        chessPieces[6][6] = factory.makeChessPiece("Arrow", 6, 6, 1, false, 0);
    }
    /**
     * This will return the only instance of this class, which corresponds to the Singleton Design Pattern
     * @return instance of the chess board
     */
    public static ChessBoard getInstance(){
        return instance;
    }
    /**
     * This will return the chessPieces[][] array
     * @return chessPieces[][] array
     */
    public static GenericChessPiece[][] getChessPieces() {
        return chessPieces;
    }
    /**
     * This will return the boolean of the existance of the chessPiece in the chessPieces[][] array by the given coordinate
     * @param row Row no
     * @param col Col no
     * @return A boolean indicating whether this position has a chess piece occupied
     */
    public boolean hasChessPiece(int row, int col) {
        return chessPieces[row][col] != null;
    }
    /**
     * This will return the playerTurn in integer
     * @return PlayerTurn in integer
     */
    public static int getPlayerTurn() {
        // If the current turn is 1, it is blue's turn; if it is 2, it is red's current turn
        return turnCount % 2 == 0 ? 2 : 1;
    }
    /**
     * This will return the playerTurn in String
     * @return "Blue" or "Red"
     */
    public static String getPlayerTurnName() {
        return getPlayerTurn() == 1 ? "Blue" : "Red";
    }
    /**
     * This will return the turn count in integer
     * @return Turn count
     */
    public static int getTurnCount() {
        return turnCount;
    }

    /**
     * This will set the turn count of the game by the given integer, which designed for loading game file
     * @param turnCount Integer of the turn count number
     */
    public void setTurnCount(int turnCount) {
        if (turnCount > 0) {
            ChessBoard.turnCount = turnCount;
        } else {
            ChessBoard.turnCount = 1;
        }
    }
    /**
     * This will return the roundCount, which is only increments when both the players have moved, which also displayed on the right bottom of the UI
     * @return Round Count
     */
    public static int getRoundCount() {
        return (getTurnCount() + 1) / 2;
    }
    /**
     * This will return the winner (player) of the game in integer
     * @return Winner in integer
     */
    public int getWinner () {
        ArrayList<GenericChessPiece> sunArray = new ArrayList<>();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (chessPieces[row][col] instanceof Sun){
                    sunArray.add(chessPieces[row][col]);
                }
            }
        }

        if (sunArray.size() == 1) {
            // If there is only one sun left
            return sunArray.get(0).getTeam();
        }
        return 0;
    }
}