package model.cell;


import javafx.scene.control.Label;
import java.util.HashMap;

/**
 * @author by zhoutao
 * @description 棋盘每个方格类
 * @date 2020/10/31 9:53
 */
public class Cells extends Label {

    private static final HashMap<Integer, String> hashMap = new HashMap<>(6);
    static {
        hashMap.put(0, "-fx-background-color:red; -fx-border-color:lightgrey;");
        hashMap.put(1, "-fx-background-color:green; -fx-border-color:lightgrey;");
        hashMap.put(2, "-fx-background-color:blue; -fx-border-color:lightgrey;");
        hashMap.put(3, "-fx-background-color:orange; -fx-border-color:lightgrey;");
        hashMap.put(4, "-fx-background-color:white; -fx-border-color:lightgrey;");
//        hashMap.put(5, "-fx-background-color:grey; -fx-border-color:lightgrey;");
        hashMap.put(5, "-fx-background-color:black; -fx-border-color:lightgrey;");
    }

    private double width;
    private double height;
    private CellType cellType;

    public Cells(){
        super();
        this.width = 30;
        this.height = 30;
        this.cellType = CellType.DEFAULT;
        this.initCellStyle();
    }

    public Cells(CellType cellType){
        super();
        this.width = 30;
        this.height = 30;
        this.cellType = cellType;
        this.initCellStyle();
    }

    public Cells(double width, double height){
        super();
        this.width = width;
        this.height = height;
        this.cellType = CellType.DEFAULT;
        this.initCellStyle();
    }

    public Cells(double width, double height, CellType cellType){
        super();
        this.width = width;
        this.height = height;
        this.cellType = cellType;
        this.initCellStyle();
    }

    public void setCellType(CellType cellType){
        this.cellType = cellType;
        this.initCellStyle();
    }

    public CellType getCellType(){
        return this.cellType;
    }

    public void setCellSize(double width, double height){
        this.width = width;
        this.height = height;
    }

    public void setCellWidth(double width) {
        this.width = width;
    }

    public void setCellHeight(double height) {
        this.height = height;
    }


    private void initCellStyle(){
        this.setMinSize(this.width, this.height);
        this.setCellSize(this.width, this.height);
        this.setStyle(hashMap.get(this.cellType.getValue()));
    }
}
