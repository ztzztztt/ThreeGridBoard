package model.threegrid;

import model.cell.CellType;
import model.cell.Cells;
import java.util.Vector;

/**
 * @author by zhoutao
 * @description 三格版抽象类
 * @date 2020/10/31 21:13
 */
public abstract class ThreeGrid {
    protected int key;
    public Vector<Vector<Cells>> threeGrid = new Vector<>();

    public ThreeGrid(){ }

    public Vector<Vector<Cells>> getThreeGrid(){
        return this.threeGrid;
    }

    public int getKey(){
        return this.key;
    }

    public static Vector<Vector<Cells>> generateThreeGrid(int key, int boardSize){
        Vector<Vector<Cells>> table = new Vector<>();
        int cellSize = Math.min(880 / boardSize, 40);
        for (int i=0;i<boardSize;i++){
            Vector<Cells> v = new Vector<>();
            for (int j=0;j<boardSize;j++){
                if (i * 2 + j == key){
                    v.add(new Cells(cellSize, cellSize, CellType.INCOMPLETE));
                    continue;
                }
                v.add(new Cells(cellSize, cellSize));
            }
            table.add(v);
        }
        return table;
    }
}
