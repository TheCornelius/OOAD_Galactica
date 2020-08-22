import java.awt.*;
import java.util.ArrayList;

public class Chevron extends GenericChessPiece{
    public Chevron(int team, boolean isActive) {
        super(team, isActive, new String[]{"blue_chevron.png", "red_chevron.png"});
    }

    @Override
    public ArrayList<Point> showValidPossibleMoves() {
        return null;
    }
}
