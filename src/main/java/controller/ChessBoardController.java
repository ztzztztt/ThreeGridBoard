package controller;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import model.ChessBoard;

/**
 * @author by zhoutao
 * @description 棋盘布局页面组件
 * @date 2020/11/1 17:23
 */
public class ChessBoardController {
    private GridPane chessBoardPane;
    private final ChessBoard chessBoard;
    private int defaultSize;


    private static ChessBoardController chessBoardController;

    public static ChessBoardController getInstance(){
        if (chessBoardController == null){
            chessBoardController = new ChessBoardController();
        }
        return chessBoardController;
    }

    private ChessBoardController(){
        chessBoard = ChessBoard.getInstance();
        defaultSize = chessBoard.getBoardSize();
        this.initialChessBoardPane();
        this.constructBoard();
    }

    private void initialChessBoardPane(){
        chessBoardPane = new GridPane();
        chessBoardPane.setPrefSize(880.0, 880.0);
        chessBoardPane.setAlignment(Pos.CENTER);
        chessBoardPane.setLayoutX(10.0);
        chessBoardPane.setLayoutY(10.0);
        chessBoardPane.setStyle("-fx-background-color: #f3f3f3;");
    }

    private void constructBoard(){
        for (int i=0;i<chessBoard.getBoardSize();i++){
            for (int j=0;j<chessBoard.getBoardSize();j++){
                chessBoard.getTable().get(i).get(j).setVisible(true);
                chessBoardPane.add(chessBoard.getTable().get(i).get(j), j, i);
            }
        }
    }

    public GridPane getChessBoardPane(){
        return this.chessBoardPane;
    }

    public void resetDefaultSize(int size){
        this.defaultSize = size;
        chessBoard.resizeChessBoard(this.defaultSize);
        chessBoardPane.getChildren().clear();
        this.constructBoard();
    }
}
