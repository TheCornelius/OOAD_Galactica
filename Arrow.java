import java.awt.*;
import java.util.ArrayList;

public class Arrow extends GenericChessPiece{
    public Arrow(int team, boolean isActive) {
        super(team, isActive, new String[]{"blue_arrow.png", "red_arrow.png"});
    }

    @Override
    public ArrayList<Point> showValidPossibleMoves() {
        return null;
    }
}
