/**
 * This class is implemented using Factory Design Pattern.
 * The main purpose of the following class is to return a corresponding chess piece objects by given the approprate chess piece name.
 */
public class ChessPieceFactory {
    /**
     * This method will take in a string which contains the chess piece name and return a corresponding chess piece objects.
     * @param chessPieceName Takes in chess piece name in String
     * @param x Coordinate x (col) of the chessPiece
     * @param y Coordinate y (row) of the chessPiece
     * @param team The belonging team of the chessPiece
     * @return A chess piece object
     */
    public GenericChessPiece makeChessPiece(String chessPieceName, int x, int y, int team) {
        if(chessPieceName == null){
            return null;
        }
        // Arrow, Triangle, Plus, Sun, Chevron
        if(chessPieceName.equalsIgnoreCase("triangle")){
            return new Triangle(new Point(x, y), team);
        } else if(chessPieceName.equalsIgnoreCase("plus")){
            return new Plus(new Point(x, y), team);
        } else if(chessPieceName.equalsIgnoreCase("sun")){
            return new Sun(new Point(x, y),team);
        }  else if(chessPieceName.equalsIgnoreCase("chevron")){
            return new Chevron(new Point(x, y), team);
        }
        return null;  // returns null to indicate cannot initialize chess piece object
    }

    /**
     * This method is an overloading method which is specifically designed for arrow class which needs additional information or parameter.
     * @param chessPieceName Takes in chess piece name in String
     * @param x Coordinate x (col) of the chessPiece
     * @param y Coordinate y (row) of the chessPiece
     * @param team The belonging team of the chessPiece
     * @param turnOver The boolean which corresponds to the arrow object if it has turn overed.
     * @param direction The direction of the arrow
     * @return A chess peice object (Arrow)
     */
    public GenericChessPiece makeChessPiece(String chessPieceName, int x, int y, int team, boolean turnOver, int direction) {
        if(chessPieceName == null){
            return null;
        }
        // Arrow, Triangle, Plus, Sun, Chevron
       if(chessPieceName.equalsIgnoreCase("arrow")){
            return new Arrow(new Point(x, y), team, direction, turnOver);
        }

        return null;  // returns null to indicate cannot initialize chess piece object
    }
}