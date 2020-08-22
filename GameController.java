import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

public class GameController extends Controller{
    private static Button btnSelectedFrom;
    private static Button btnSelectedTo;
    private static GenericChessPiece chessSelectedFrom;
    private static GenericChessPiece chessSelectedTo;
    private static int fromCol, fromRow, toCol, toRow;
    public GameController(ChessBoard board) {
        super(board);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Debug: " + actionEvent.getSource() + " clicked!");
        if (btnSelectedFrom == null) {
            Lighting lighting = new Lighting();
            Light.Distant light = new Light.Distant();
            light.setAzimuth(-135.0);
            lighting.setLight(light);
            lighting.setSurfaceScale(5.0);
            ((Button) actionEvent.getSource()).setEffect(lighting);
            btnSelectedFrom = (Button) actionEvent.getSource();
            fromRow = Integer.parseInt(((Button) actionEvent.getSource()).getId())/10;
            fromCol = Integer.parseInt(((Button) actionEvent.getSource()).getId())%10;
        }
        else{
            btnSelectedTo = (Button) actionEvent.getSource();
            toRow = Integer.parseInt(((Button) actionEvent.getSource()).getId())/10;
            toCol = Integer.parseInt(((Button) actionEvent.getSource()).getId())%10;
            getChessGame().moveChessPieces(fromRow, fromCol, toRow,toCol);
            btnSelectedTo = null;
            btnSelectedFrom = null;
            board.setChessIconImage();
            board.getChessButton()[fromRow][fromCol].setEffect(null);
        }
    }
}
