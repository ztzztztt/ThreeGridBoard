package model.cell;

import javafx.scene.control.Cell;

import java.util.Arrays;

/**
 * @author by zhoutao
 * @description 棋盘方格的类型枚举类
 * @date 2020/10/31 20:50
 */


public enum CellType {
    DEFAULT("默认", 4),
    INCOMPLETE("残缺", 5),
    TOP_LEFT("左上", 0),
    TOP_RIGHT("右上", 1),
    BOTTOM_LEFT("左下", 2),
    BOTTOM_RIGHT("右下", 3);

    private String name;
    private Integer value;

    CellType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static CellType getCellTypeByValue(int value) {
        for (CellType cellType : CellType.values()) {
            if (cellType.value == value) {
                return cellType;
            }
        }
        return null;
    }
}
