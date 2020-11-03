package model.threegrid;

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
}
