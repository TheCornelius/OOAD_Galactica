import java.util.ArrayList;

/**
 * This Plus class define the path of Plus chess image and algorithm of the valid possible moves of Plus chess
 */
public class Plus extends GenericChessPiece{

    /**
     * Class constructor for the Plus class. This constructor takes in two arguments - the coordinate of the current Plus instance, and the player owning the current Plus instance.
     * @param coordinate A Point object which represents the current X and Y coordinate values of a Plus instance in the Webale chess board. 
     * @param team An integer variable which represents whether this chess piece object belongs to the red or blue player.
     */
    public Plus(Point coordinate, int team) {
        super(coordinate, team, new String[]{"./Assets/blue_plus.png", "./Assets/red_plus.png"});
    }

    /**
     * This method will calculate the valid possible moves of the Plus chess 
     */
    @Override
    public ArrayList<Point> showValidPossibleMoves(final ChessBoard CHESS_BOARD) {
        int row = this.getCoordinate().getY();
        int col = this.getCoordinate().getX();

        ArrayList<Point> validPossibleMoves = new ArrayList<>();

        for (int i = row + 1; i <= 7; i++) {
            // i indicates row movement - Moving downwards
            if (ChessBoard.getInstance().hasChessPiece(i, col)) {
                // If the board has chess piece
                if (ChessBoard.getChessPieces()[i][col].getTeam() != this.getTeam()) {
                    // If the chess piece is for enemy
                    validPossibleMoves.add(new Point(col, i));
                }
                break;
            }
            else{
                validPossibleMoves.add(new Point(col, i));
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            // i indicates row movement - Moving upwards
            if (ChessBoard.getInstance().hasChessPiece(i, col)) {
                // If the board has chess piece
                if (ChessBoard.getChessPieces()[i][col].getTeam() != this.getTeam()) {
                    // If the chess piece is for enemy
                    validPossibleMoves.add(new Point(col, i));
                }
                break;
            }
            else{
                validPossibleMoves.add(new Point(col, i));
            }
        }

        for (int i = col - 1; i >= 0; i--) {
            // i indicates row movement - Moving leftwards
            if (ChessBoard.getInstance().hasChessPiece(row, i)) {
                // If the board has chess piece
                if (ChessBoard.getChessPieces()[row][i].getTeam() != this.getTeam()) {
                    // If the chess piece is for enemy
                    validPossibleMoves.add(new Point(i, row));
                }
                break;
            }
            else{
                validPossibleMoves.add(new Point(i, row));
            }
        }

        for (int i = col + 1; i <= 6; i++) {
            // i indicates row movement - Moving rightwards
            if (ChessBoard.getInstance().hasChessPiece(row, i)) {
                // If the board has chess piece
                if (ChessBoard.getChessPieces()[row][i].getTeam() != this.getTeam()) {
                    // If the chess piece is for enemy
                    validPossibleMoves.add(new Point(i, row));
                }
                break;
            }
            else{
                validPossibleMoves.add(new Point(i, row));
            }
        }
        return validPossibleMoves;
    }

    /**
     * This will display the details of the Plus chess which include chess piece name, coordinate(x,y) and team
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ";" + getCoordinate().getX() + ";" + getCoordinate().getY() + ";" + getTeam();
    }
}
