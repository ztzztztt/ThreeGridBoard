package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.threegrid.*;
import java.util.ArrayList;

/**
 * @author by zhoutao
 * @description 三格版布局UI组件
 * @date 2020/11/1 17:26
 */
public class ThreeGridController {

    private static ThreeGridController threeGridController;

    public static ThreeGridController getInstance(){
        if (threeGridController == null){
            threeGridController = new ThreeGridController();
        }
        return threeGridController;
    }

    VBox threeVBox;
    HBox topLeftRight;
    HBox bottomLeftRight;
    ArrayList<Label> labelArrayList = new ArrayList<>(4);

    private final TopLeftGrid topLeftGrid = TopLeftGrid.getInstance();
    private final TopRightGrid topRightGrid = TopRightGrid.getInstance();
    private final BottomLeftGrid bottomLeftGrid = BottomLeftGrid.getInstance();
    private final BottomRightGrid bottomRightGrid = BottomRightGrid.getInstance();

    private ThreeGridController(){
        // 初始化三格版根容器
        this.initialThreeVBox();
        // 初始化三格版容器
        this.initialHBox();
        // 初始化Label标签
        this.initLabel();
        threeVBox.getChildren().add(topLeftRight);
        threeVBox.getChildren().add(bottomLeftRight);
        this.displayThreeGrid();
    }

    private void initLabel(){
        Label topLeftLabel = new Label();
        Label topRightLabel = new Label();
        Label bottomLeftLabel = new Label();
        Label bottomRightLabel = new Label();
        labelArrayList.add(0, topLeftLabel);
        labelArrayList.add(1, topRightLabel);
        labelArrayList.add(2, bottomLeftLabel);
        labelArrayList.add(3, bottomRightLabel);
    }

    private void initialThreeVBox(){
        threeVBox = new VBox();
        threeVBox.setPrefSize(480.0, 400.0);
        threeVBox.setLayoutX(910.0);
        threeVBox.setLayoutY(10.0);
        threeVBox.setStyle("-fx-background-color: #f3f3f3;");
    }

    private void initialHBox(){
        topLeftRight = new HBox();
        topLeftRight.setPrefSize(200.0,200.0);
        topLeftRight.setAlignment(Pos.CENTER);
        bottomLeftRight = new HBox();
        bottomLeftRight.setPrefSize(200.0,200.0);
        bottomLeftRight.setAlignment(Pos.CENTER);
    }

    private void displayThreeGrid(){
        this.display(topLeftGrid, TopLeftGrid.count, labelArrayList.get(0), topLeftRight);
        this.display(topRightGrid, TopRightGrid.count, labelArrayList.get(1), topLeftRight);
        this.display(bottomLeftGrid, BottomLeftGrid.count, labelArrayList.get(2), bottomLeftRight);
        this.display(bottomRightGrid, BottomRightGrid.count, labelArrayList.get(3), bottomLeftRight);
    }

    /**
     * 展示每一个三格版及其对应的数量
     * @param threeGrid 三格版
     * @param count 数量
     * @param label label标签
     * @param hBox 展示位置
     */
    private void display(ThreeGrid threeGrid, int count, Label label, HBox hBox){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setPrefSize(100.0, 200.0);
        HBox.setMargin(gridPane, new Insets(0, 40, 0, 40));
        for (int i=0;i<2;i++) {
            for (int j = 0; j < 2; j++) {
                if (threeGrid.getThreeGrid().get(i).get(j) != null) {
                    gridPane.add(threeGrid.getThreeGrid().get(i).get(j), j, i);
                }
            }
        }
        label.setText(String.valueOf(count));
        gridPane.add(label, 1, 2);
        hBox.getChildren().add(gridPane);
    }

    public void changeThreeGridCount(Label label, int count){
        label.setText(String.valueOf(count));
    }

    public VBox getThreeVBox(){
        return this.threeVBox;
    }


    /**
     * 重置三格版标签
     */
    public void resetLabel(){
        TopLeftGrid.count = 0;
        TopRightGrid.count = 0;
        BottomLeftGrid.count = 0;
        BottomRightGrid.count = 0;
        for (Label label : labelArrayList) {
            label.setText(String.valueOf(0));
        }
    }

    /**
     * 刷新三格版标签
     * @param key 刷新哪个三格版的表示
     */
    public void refreshLabel(int key){
        if (labelArrayList.get(key) != null){
            switch (key){
                case 0:
                    TopLeftGrid.count++;
                    labelArrayList.get(key).setText(String.valueOf(TopLeftGrid.count));
                    break;
                case 1:
                    TopRightGrid.count++;
                    labelArrayList.get(key).setText(String.valueOf(TopRightGrid.count));
                    break;
                case 2:
                    BottomLeftGrid.count++;
                    labelArrayList.get(key).setText(String.valueOf(BottomLeftGrid.count));
                    break;
                case 3:
                    BottomRightGrid.count++;
                    labelArrayList.get(key).setText(String.valueOf(BottomRightGrid.count));
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("输入的key值无效, 默认范围为0-3");
        }
    }
}
