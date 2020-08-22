import java.awt.*;
import java.util.ArrayList;

public class Sun extends GenericChessPiece{
    public Sun(int team, boolean isActive) {
        super(team, isActive, new String[]{"blue_sun.png", "red_sun.png"});
    }

    @Override
    public ArrayList<Point> showValidPossibleMoves() {
        return null;
    }
}
