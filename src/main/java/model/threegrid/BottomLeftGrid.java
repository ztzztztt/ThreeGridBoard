package model.threegrid;

import model.cell.CellType;
import model.cell.Cells;

import java.util.Vector;

/**
 * @author by zhoutao
 * @description 左下缺少的三格版
 * @date 2020/10/31 21:46
 */
public class BottomLeftGrid extends ThreeGrid {
    public static int count = 0;

    public static BottomLeftGrid bottomLeftGrid;

    public static BottomLeftGrid getInstance(){
        if (bottomLeftGrid == null){
            bottomLeftGrid = new BottomLeftGrid();
        }
        return bottomLeftGrid;
    }

    public BottomLeftGrid(){
        super();
        this.key = 2;
        this.initThreeGrid();
    }

    private void initThreeGrid(){
        for (int i=0;i<2;i++){
            Vector<Cells> v = new Vector<>(2);
            for (int j=0;j<2;j++){
                if (i == 1 && j == 0){
                    v.add(null);
                    continue;
                }
                v.add(new Cells(50, 50, CellType.getCellTypeByValue(key)));
            }
            this.threeGrid.add(v);
        }
    }
}
