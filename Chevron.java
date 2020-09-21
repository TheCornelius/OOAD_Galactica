import java.util.ArrayList;

/**
 * This Plus class define the path of Chevron chess image and algorithm of the valid possible moves of Chevron chess.
 */
public class Chevron extends GenericChessPiece{
    /**
     * Class constructor for the Chevron class. This constructor takes in two arguments - the coordinate of the current Chevron instance, and the player owning the current Chevron instance.
     * @param coordinate A Point object which represents the current X and Y coordinate values of a Chevron instance in the Webale chess board. 
     * @param team An integer variable which represents whether this chess piece object belongs to the red or blue player.
     */
    public Chevron(Point coordinate, int team) {
        super(coordinate, team, new String[]{"./Assets/blue_chevron.png", "./Assets/red_chevron.png"});
    }

    /**
     * This method will calculate the valid possible moves of the Chevron chess
     */
    @Override
    public ArrayList<Point> showValidPossibleMoves(final ChessBoard CHESS_BOARD) {

        ArrayList<Point> validPossibleMoves = new ArrayList<>();

        int row = this.getCoordinate().getY();
        int col = this.getCoordinate().getX();

        int[] possibleRows = {row + 1, row + 2, row - 2, row - 1 , row + 1, row + 2, row - 2, row - 1};
        int[] possibleCols = {col + 2, col + 1, col + 1, col + 2 , col - 2, col - 1, col - 1, col - 2};
        
        for (int i = 0; i < possibleRows.length; i++) {
            if ((possibleRows[i] >= 0 && possibleRows[i] <= 7) && (possibleCols[i] >= 0 && possibleCols[i] <= 6)) {
                // To enforce boundaries for the box
                if (ChessBoard.getInstance().hasChessPiece(possibleRows[i], possibleCols[i])) {
                    if (ChessBoard.getChessPieces()[possibleRows[i]][possibleCols[i]].getTeam() == this.getTeam()) {
                        continue;
                    }
                }
                validPossibleMoves.add(new Point(possibleCols[i], possibleRows[i]));
            }
        }
        
        return validPossibleMoves;
    }

    /**
     * This will display the details of the Chevron chess which include chess piece name, coordinate(x,y) and team
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ";" + getCoordinate().getX() + ";" + getCoordinate().getY() + ";" + getTeam();
    }
}
