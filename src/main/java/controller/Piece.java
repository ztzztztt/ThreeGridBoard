package controller;

import javafx.scene.control.Label;

import java.util.HashMap;

/**
 * @author by zhoutao
 * @description 棋盘中每一个棋子的类
 * @date 2020/10/30 9:02
 */
public class Piece extends Label {
    final String WHITE_STYLE = "-fx-background-color:white; -fx-border-color:lightgrey; -fxborder-width: 5px";
    final String GREY_STYLE = "-fx-background-color:grey; -fx-border-color:lightgrey; -fxborder-width: 5px";
    HashMap<Integer, String> colorStyle = new HashMap<>();

    boolean imComplete = false;
    int colorKey;

    public boolean isImComplete() {
        return imComplete;
    }

    public Piece(){
        super();
        setMinSize(50, 50);
        initColorStyle();
        reset();
    }

    /**
     * 初始化四种颜色，表示三格版的类型
     * 按照缺失的位置从左到右，从上到下的排列位置设置编号
     * 0 - (0, 0), 1 - (0, 1), 2 - (1, 0), 3 - (1, 1)
     */
    private void initColorStyle(){
        colorStyle.put(0, "-fx-background-color:red;");
        colorStyle.put(1, "-fx-background-color:green;");
        colorStyle.put(2, "-fx-background-color:blue;");
        colorStyle.put(3, "-fx-background-color:orange;");
    }

    public void setColorKey(int colorKey) {
        this.colorKey = colorKey;
        setStyle(colorStyle.get(this.colorKey));
    }

    /**
     * 重置每个标签的颜色
     */
    public void reset(){
        setStyle(WHITE_STYLE);
    }

    public void imComplete(){
        this.imComplete = true;
        setStyle(GREY_STYLE);
    }
}
