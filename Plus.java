import java.awt.*;
import java.util.ArrayList;

public class Plus extends GenericChessPiece{
    public Plus(int team, boolean isActive) {
        super(team, isActive, new String[]{"blue_plus.png", "red_plus.png"});
    }

    @Override
    public ArrayList<Point> showValidPossibleMoves() {
        return null;
    }
}
