package model.threegrid;


import model.cell.CellType;
import model.cell.Cells;

import java.util.Vector;

/**
 * @author by zhoutao
 * @description 左上缺失的三格版
 * @date 2020/10/31 21:35
 */
public class TopLeftGrid extends ThreeGrid {
    public static int count=0;
    public static TopLeftGrid topLeftGrid;

    public static TopLeftGrid getInstance(){
        if (topLeftGrid == null){
            topLeftGrid = new TopLeftGrid();
        }
        return topLeftGrid;
    }


    public TopLeftGrid(){
        super();
        this.key = 0;
        this.initThreeGrid();
    }

    private void initThreeGrid(){
        for (int i=0;i<2;i++){
            Vector<Cells> v = new Vector<>(2);
            for (int j=0;j<2;j++){
                if (i == 0 && j == 0){
                    v.add(null);
                    continue;
                }
                v.add(new Cells(50, 50, CellType.getCellTypeByValue(key)));
            }
            this.threeGrid.add(v);
        }
    }

    public int getKey(){
        return this.key;
    }
}
