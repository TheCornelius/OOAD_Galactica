import java.util.ArrayList;

/**
 * The concrete class of the GenericChessPiece
 */
public class Sun extends GenericChessPiece{
    /**
     * Constuctor of the Sun class, which creates a Sun chess piece object
     * @param coordinate Coordinate
     * @param team Team
     */
    public Sun(Point coordinate, int team) {
        super(coordinate, team, new String[]{"./Assets/blue_sun.png", "./Assets/red_sun.png"});
    }
    /**
     * A concrete method, which will show the valid possible move after player has selected the Sun chess piece
     * @param CHESS_BOARD The static ChessBoard instance
     * @return ArrayList of the possible moves
     */
    @Override
    public ArrayList<Point> showValidPossibleMoves(final ChessBoard CHESS_BOARD) {

        ArrayList<Point> validPossibleMoves = new ArrayList<Point>();

        int row = this.getCoordinate().getY();
        int col = this.getCoordinate().getX();

        int[] possibleRows = {row    , row + 1, row - 1, row    , row + 1, row - 1, row + 1, row - 1};
        int[] possibleCols = {col + 1, col    , col    , col - 1, col + 1, col - 1, col - 1, col + 1};

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
     * A concrete toString() method, which will return all the details of the Sun object
     * @return Name, x coordinate, y coordinate, team
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ";" + getCoordinate().getX() + ";" + getCoordinate().getY() + ";" + getTeam();
    }
}
