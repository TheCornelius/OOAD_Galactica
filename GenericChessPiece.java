import java.awt.*;
import java.util.ArrayList;

public abstract class GenericChessPiece implements Cloneable{
    private Point coordinate;               //obsolete
    private int team;                       //1:blue 2:red
    private boolean isActive;               //obsolete
    private String[] iconImage;

    public GenericChessPiece(int team, boolean isActive, String[] iconImage) {
        this.team = team;
        this.isActive = isActive;
        this.iconImage = iconImage;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public int getTeam() {
        return team;
    }

    public boolean isActive() {
        return isActive;
    }

    public String[] getIconImage() {
        return iconImage;
    }

    public abstract ArrayList<Point> showValidPossibleMoves();      //TO-DO


}
