package service;

import controller.Piece;
import controller.ThreeGrid;
import factory.ThreeGridFactory;
import javafx.geometry.Pos;
import utils.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

/**
 * @author by zhoutao
 * @description 解决三格版的类
 * @date 2020/10/30 17:28
 */
public class Solution {
    int pieceNum;
    Position position;
    Vector<Vector<Piece>> table;
    ArrayList<Position> arrayList = new ArrayList<>(4);


    private void initHashMap(){
        arrayList.add(new Position(0, 0));
        arrayList.add(new Position(0, 1));
        arrayList.add(new Position(1, 0));
        arrayList.add(new Position(1, 1));
    }

    public Solution(Vector<Vector<Piece>> table, Position position, int pieceNum){
        this.table = table;
        this.position = position;
        this.pieceNum = pieceNum;
        initHashMap();
    }

    public void resolve(Vector<Vector<Piece>> table, Position position, int pieceNum){
        if (this.table.size() == 2){
            int threeGridId = judgeThreeGrid();
            ThreeGrid threeGrid = ThreeGridFactory.getThreeGridFactory().getThreeGrid(threeGridId);
            threeGrid.setCount(threeGrid.getCount() + 1);
            changePieceColor(threeGridId);
        } else {
            // 计算出缺失的点所在方位
            // (0, 1): 左上, (0, 1): 右上, (1, 0): 左下, (1, 1): 右下
            int missingPositionX = 2 * this.position.getPositionX() / this.pieceNum;
            int missingPositionY = 2 * this.position.getPositionY() / this.pieceNum;
            int missingId = missingPositionX * 2 + missingPositionY;
            // 获取对应的三格版
            ThreeGrid threeGrid = ThreeGridFactory.getThreeGridFactory().getThreeGrid(missingId);
            threeGrid.setCount(threeGrid.getCount() + 1);

            // 获取需要修改颜色的位置
            int base = this.pieceNum / 2;

            for (int i=0;i<4;i++){
                Position newPosition = new Position(base + arrayList.get(i).getPositionX() , base + arrayList.get(i).getPositionY());
                changePieceColor(newPosition, missingId);
                Vector<Vector<Piece>> newTable = new Vector<>();
                int xGreatThanHalf = newPosition.getPositionX() > this.pieceNum / 2 ? 1 : 0;
                int yGreatThanHalf = newPosition.getPositionY() > this.pieceNum / 2 ? 1 : 0;
                int startX = xGreatThanHalf * ((this.pieceNum + 1) / 2);
                int startY = yGreatThanHalf * ((this.pieceNum + 1) / 2);
                for (int j=startX;j<startX + this.pieceNum / 2;j++){
                    Vector<Piece> v = new Vector<>();
                    for(int k=startY; k<startY + this.pieceNum / 2;k++){
                        v.add(table.get(i).get(j));
                    }
                    newTable.add(v);
                }
                if (i == missingId){
                    resolve(newTable, position, pieceNum / 2);
                } else{
                    resolve(newTable, newPosition, pieceNum / 2);
                }
            }
        }
    }

    public int judgeThreeGrid(){
        int threeGridId = -1;
        for (int i=0;i<table.size();i++){
            for (int j=0;j<table.size();j++){
                if (table.get(i).get(j).isImComplete()){
                    threeGridId = i * 2 + j;
                }
            }
        }
        return threeGridId;
    }

    public void changePieceColor(int threeGridId){
        table.forEach(v -> v.forEach(piece -> piece.setColorKey(threeGridId)));
    }

    public void changePieceColor(Position position, int threeGridId){
        for (int i=0;i<this.pieceNum;i++){
            for (int j=0;j<this.pieceNum;j++){
                if (i == position.getPositionX() && j == position.getPositionY()){
                    table.get(i).get(j).setColorKey(threeGridId);
                }
            }
        }
    }
}
