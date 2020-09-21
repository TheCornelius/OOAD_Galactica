import java.util.ArrayList;

/**
 * This class represents the Triangle chess pieces used in Webale chess game, and inherits from the GenericChessPiece class.
 */
public class Triangle extends GenericChessPiece{
    /**
     * Class constructor for the Triangle class. This constructor takes in two arguments - the coordinate of the current Triangle instance, and the player owning the current Triangle instance.
     * @param coordinate A Point object which represents the current X and Y coordinate values of a Triangle instance in the Webale chess board. 
     * @param team An integer variable which represents whether this chess piece object belongs to the red or blue player.
     */
    public Triangle(Point coordinate, int team) {
        super(coordinate, team, new String[]{"./Assets/blue_triangle.png", "./Assets/red_triangle.png"});
    }
    
    /**
     * This method contains the algorithm used for generating the possible available moves of a Triangle instance in the Webale chess game.
     * This method first retrieves the X and Y coordinates of the current Triangle instance, and uses 4 for loops for determining the valid possible moves across two diagonals of the Webale chess game.
     * @param CHESS_BOARD A variable which represents the Webale chess board used in game.
     * @return ArrayList An ArrayList of Point instances which represents the coordinates which are valid moves on the Webale chess board for a Triangle chess piece.
     */
    
    @Override
    public ArrayList<Point> showValidPossibleMoves(final ChessBoard CHESS_BOARD) {
        int row = this.getCoordinate().getY();
        int col = this.getCoordinate().getX();

        ArrayList<Point> validPossibleMoves = new ArrayList<Point>();

        for (int i = row+1, j = col+1; (i <= 7 && j <= 6); i++, j++) {
            // i indicates row movement - Moving bottom right 
            if (ChessBoard.getInstance().hasChessPiece(i, j)) {
                // If the board has chess piece
                if (ChessBoard.getChessPieces()[i][j].getTeam() != this.getTeam()) {
                    // If the chess piece belongs to the opponent     
                    validPossibleMoves.add(new Point(j, i));
                }
                break;
            }
            else{
                validPossibleMoves.add(new Point(j, i));
            }
        }

        for (int i = row-1, j = col-1; (i >= 0 && j >= 0); i--, j--) {
            // i indicates row movement - Moving upwards to the left (top left)
            if (ChessBoard.getInstance().hasChessPiece(i, j)) {
                // If the board has chess piece
                if (ChessBoard.getChessPieces()[i][j].getTeam() != this.getTeam()) {
                    // If the chess piece belongs to the opponent     
                    validPossibleMoves.add(new Point(j, i));
                }
                break;
            }
            else{
                validPossibleMoves.add(new Point(j, i));
            }
        }

        for (int i = row-1,  j = col+1; (i >= 0 && j <=6) ; i--, j++) {
            // i indicates row movement - Moving upward to the right (top right)
            if (ChessBoard.getInstance().hasChessPiece(i,j)) {
                // If the board has chess piece
                if (ChessBoard.getChessPieces()[i][j].getTeam() != this.getTeam()) {
                    // If the chess piece belongs to the opponent     
                    validPossibleMoves.add(new Point(j,i));
                }
                break;
            }
            else{
                validPossibleMoves.add(new Point(j,i));
            }
        }

        for (int i = row+1, j = col-1; (i <= 7 && j >=0) ; i++, j--) {
            // i indicates row movement - Moving downwards to left (bottom left)
            if (ChessBoard.getInstance().hasChessPiece(i, j)) {
                // If the board has chess piece
                if (ChessBoard.getChessPieces()[i][j].getTeam() != this.getTeam()) {
                    // If the chess piece belongs to the opponent     
                    validPossibleMoves.add(new Point(j,i));
                }
                break;
            }
            else{
                validPossibleMoves.add(new Point(j,i));
            }
        }
        return validPossibleMoves;
    }

    /**
     * This method returns a human readable String representation of an instance of the Triangle class.
     * This method is also used in the process of creating game save files, when writing contents of Triangle objects to the game save file.
     * @return String A human readable String representation of an instance of the Triangle class.
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ";" + getCoordinate().getX() + ";" + getCoordinate().getY() + ";" + getTeam();
    }
}
