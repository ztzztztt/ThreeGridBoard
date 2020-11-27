package model.threegrid;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import model.cell.CellType;
import model.cell.Cells;

import java.util.Vector;

/**
 * @author by zhoutao
 * @description TODO
 * @date 2020/11/24 21:56
 */
public class DraggerThreeGrid {

    public static Node generateThreeGrid(int key, int boardSize){
        GridPane gridPane = new GridPane();
        Vector<Vector<Cells>> table = generateThreeGridVector(key, boardSize);
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                if (i *2 + j != key){
                    gridPane.add(table.get(i).get(j), j, i);
                }
            }
        }
        return gridPane;
    }


    private static Vector<Vector<Cells>> generateThreeGridVector(int key, int boardSize){
        Vector<Vector<Cells>> table = new Vector<>();
        int cellSize = Math.min(880 / boardSize, 40);
        for (int i=0;i<boardSize;i++){
            Vector<Cells> v = new Vector<>();
            for (int j=0;j<boardSize;j++){
                if (i * 2 + j == key){
                    v.add(new Cells(cellSize, cellSize, CellType.INCOMPLETE));
                    continue;
                }
                v.add(new Cells(cellSize, cellSize, CellType.getCellTypeByValue(key)));
            }
            table.add(v);
        }
        return table;
    }
}
