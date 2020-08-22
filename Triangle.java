import java.awt.*;
import java.util.ArrayList;

public class Triangle extends GenericChessPiece{
    @Override
    public ArrayList<Point> showValidPossibleMoves() {
        return null;
    }

    public Triangle(int team, boolean isActive) {
        super(team, isActive, new String[]{"blue_triangle.png", "red_triangle.png"});
    }
}
