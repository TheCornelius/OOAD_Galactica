import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller implements EventHandler<ActionEvent> {        //not implement yet
    protected ChessBoard board;

    public Controller(ChessBoard board) {
        this.board = board;
    }

    public ChessGame getChessGame(){
        return board.getGame();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
    }

}
