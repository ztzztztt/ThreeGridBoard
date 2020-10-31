package controller;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import utils.Position;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Vector;

/**
 * @author by zhoutao
 * @description 棋盘组件
 * @date 2020/10/29 21:54
 */
public class Board {

    Vector<Vector<Piece>> table = new Vector<>();
    Position position;
    GridPane gridPane = new GridPane();
    int pieceNum = 8;

    public Vector<Vector<Piece>> getTable() {
        return table;
    }

    public Position getPosition() {
        return position;
    }

    public int getPieceNum() {
        return pieceNum;
    }

    public Board(){
        // 生成棋盘
        generateBoard();
        // 生成随机
        generateRandomImComplete();
    }

    /**
     * 随机生成一个残缺位置
     */
    private void generateRandomImComplete(){
        position = new Position((int)(Math.random() * pieceNum), (int)(Math.random() * pieceNum));
    }

    public void setPieceNum(int pieceNum) {
        for (int i=1;i<5;i++){
            if (pieceNum % (int)Math.pow(2, i) == 0){
                this.pieceNum = pieceNum;
                break;
            }else{
                System.err.println("输入的棋子数量不满足条件");
            }
        }
//        if (pieceNum % 16 == 0 || pieceNum % 8 == 0 || pieceNum % 4 == 0 || pieceNum % 2 == 0){
//            this.pieceNum = pieceNum;
//        }else{
//            System.err.println("输入的棋子数量不满足条件");
//        }
    }

    private void generateBoard(){
        if (pieceNum <= 0){
            System.err.println("棋盘棋子的数量不能为0");
        } else {
            for(int i=0;i<pieceNum;i++){
                Vector<Piece> vector = new Vector<>();
                for (int j=0;j<pieceNum;j++){
                    vector.add(new Piece());
                }
                table.add(vector);
            }
        }
    }

    public GridPane createGrid(){
        generateBoard();
        gridPane.setPrefSize(900.0, 900.0);
        gridPane.setAlignment(Pos.CENTER);
        displayNode();
        return gridPane;
    }

    private void displayNode(){
        for (int i=0;i<pieceNum;i++){
            for(int j=0;j<pieceNum;j++){
                if (i == position.getPositionX() && j == position.getPositionX()){
                    table.get(i).get(j).imComplete();
                }
                gridPane.add(table.get(i).get(j), i, j);
            }
        }
    }

    public void reset(){
        table.clear();
        position.reset();
        generateRandomImComplete();
        generateBoard();
        gridPane.getChildren().clear();
        displayNode();
    }
}
