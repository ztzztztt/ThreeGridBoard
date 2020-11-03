package model;

import controller.ThreeGridController;
import model.cell.Cells;
import model.cell.CellType;
import model.threegrid.BottomLeftGrid;
import model.threegrid.BottomRightGrid;
import model.threegrid.TopLeftGrid;
import model.threegrid.TopRightGrid;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author by zhoutao
 * @description 棋盘类
 * @date 2020/10/31 21:15
 */
public class ChessBoard {
    private int boardSize;
    private Position imComPosition;
    private Vector<Vector<Cells>> table = new Vector<>();

    private static ChessBoard chessBoard;

    public static ChessBoard getInstance(){
        if (chessBoard == null){
            chessBoard = new ChessBoard(8);
        }
        return chessBoard;
    }

    public ChessBoard(int boardSize){
        this.boardSize = boardSize;
        this.genImCompletePosition();
        this.initTable();
    }

    public ChessBoard(Position position, int boardSize){
        this.boardSize = boardSize;
        this.imComPosition = position.deepCopy();
        this.initTable();
    }

    public ChessBoard(Vector<Vector<Cells>> vector, Position position, int boardSize){
        this.table = vector;
        this.boardSize = boardSize;
        this.imComPosition = position.deepCopy();
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * 随机生成一个残缺位置
     */
    private void genImCompletePosition(){
        imComPosition = new Position((int)(Math.random() * boardSize), (int)(Math.random() * boardSize));
    }

    /**
     * 初始化棋盘的表格
     */
    private void initTable(){
        int cellSize = Math.min(880 / this.boardSize, 40);
        for (int i=0;i<this.boardSize;i++){
            Vector<Cells> v = new Vector<>();
            for (int j=0;j<this.boardSize;j++){
                if (i == imComPosition.getPositionX() && j == imComPosition.getPositionY()){
                    v.add(new Cells(cellSize, cellSize, CellType.INCOMPLETE));
                    continue;
                }
                v.add(new Cells(cellSize, cellSize));
            }
            table.add(v);
        }
    }

    /**
     * 修改棋盘大小
     * @param boardSize 棋盘大小
     */
    public void resizeChessBoard(int boardSize){
        this.setBoardSize(boardSize);
        this.genImCompletePosition();
        this.table.clear();
        this.initTable();
    }

    public Vector<Vector<Cells>> getTable(){
        return this.table;
    }

    public void setTable(Vector<Vector<Cells>> table){
        this.table = table;
    }

    public int getBoardSize(){
        return this.boardSize;
    }

    public Position getImComPosition() {
        return imComPosition;
    }

    /**
     * 从指定的位置切分原棋盘
     * @param position 缺失点
     * @param boardSize 棋盘大小
     * @param area 区域
     * @return ChessBoard
     */
    public ChessBoard copyChessBoardPartial(Position position, int boardSize, int area){
        Vector<Vector<Cells>> newTable = new Vector<>();
        for (int i=0;i<boardSize;i++){
            Vector<Cells> tmp = new Vector<>();
            for (int j=0;j<boardSize;j++){
                // 如果拆分不同区域的棋盘，需要对其相应的位置进行获取
                if (area == 0){
                    tmp.add(table.get(i).get(j));
                } else if(area == 1){
                    tmp.add(table.get(i).get(j + boardSize));
                } else if (area == 2){
                    tmp.add(table.get(i + boardSize).get(j));
                } else{
                    tmp.add(table.get(i + boardSize).get(j + boardSize));
                }
            }
            newTable.add(tmp);
        }
        // 不同的区域其缺失点坐标也要相应的发生变化
        if(area == 1){
            position.setPositionY(position.getPositionY() - boardSize);
        } else if (area == 2){
            position.setPositionX(position.getPositionX() - boardSize);
        } else if (area == 3){
            position.setPositionX(position.getPositionX() - boardSize);
            position.setPositionY(position.getPositionY() - boardSize);
        }
        return new ChessBoard(newTable, position, boardSize);
    }

    /**
     * 修改棋盘位置颜色
     * @param positionArrayList 需要着色的位置
     * @param key 区域地点
     */
    public void changeColor(ArrayList<Position> positionArrayList, int key){
        ThreeGridController.getInstance().refreshLabel(key);
        for (int i=0;i<positionArrayList.size();i++){
            if (i != key){
                this.table.get(positionArrayList.get(i).getPositionX()).get(positionArrayList.get(i).getPositionY()).setCellType(CellType.getCellTypeByValue(key));
            }
        }
    }

    /**
     * 修改棋盘位置颜色
     * @param key 区域地点
     */
    public void changeColor(int key){
        ThreeGridController.getInstance().refreshLabel(key);
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                if (i * 2 + j != key){
                    this.table.get(i).get(j).setCellType(CellType.getCellTypeByValue(key));
                }
            }
        }
    }
}
