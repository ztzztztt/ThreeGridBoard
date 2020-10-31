package controller;

import factory.ThreeGridFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import service.Solution;

/**
 * @author by zhoutao
 * @description 菜单组件
 * @date 2020/10/30 10:29
 */
public class Menu{

    Board board;

    public Menu(Board board){
        this.board = board;
    }

    private void resetBoard(int values){
        board.setPieceNum(values);
        board.reset();
    }

    public VBox createMenu(){
        VBox vBox = new VBox();
        vBox.setBorder(setMenuBorder());
        vBox.setPrefSize(700.0, 900.0);
        vBox.getChildren().add(createDisplayBox());
        vBox.getChildren().add(createButtonGroup());
        return vBox;
    }

    /**
     * 创建用于设置菜单栏的边框，使得菜单和左边的棋盘分隔开
     * @return Border
     */
    private Border setMenuBorder(){
        BorderStroke borderStroke = new BorderStroke(
                null,
                null,
                null,
                Color.BLACK,
                null,
                null,
                null,
                BorderStrokeStyle.DASHED,
                null,
                BorderWidths.DEFAULT,
                new Insets(5));
        return new Border(borderStroke);
    }


    /**
     * 创建展示的盒子
     * @return VBox
     */
    private VBox createDisplayBox(){
        VBox vBox = new VBox();
        vBox.setPrefSize(700.0, 300.0);
        // 创建显示的4种三格版
        HBox group = new HBox();
        group.setPrefSize(700.0, 250.0);

        group.getChildren().add(createThreeGrid(0));
        group.getChildren().add(createThreeGrid(1));
        group.getChildren().add(createThreeGrid(2));
        group.getChildren().add(createThreeGrid(3));
        // 将展示的4种三格版显示到box中
        vBox.getChildren().add(group);
        return vBox;
    }

    /**
     * 创建三格版
     * @param pos 表示三格版中缺失的位置
     * @return GridPane
     */
    private GridPane createThreeGrid(int pos){
        GridPane gridPane = new GridPane();
        // 从工厂中获取该对象
        ThreeGrid threeGrid = ThreeGridFactory.getThreeGridFactory().getThreeGrid(pos);
        gridPane.setPrefSize(700.0, 150.0);
        gridPane.setAlignment(Pos.CENTER);
        for (int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                if (i * 2 + j == pos){
                    continue;
                }
                gridPane.add(threeGrid.getThreeGridList().get(i).get(j), i, j);
            }
        }
        Label label = new Label();
        label.setText(String.valueOf(threeGrid.getCount()));
        label.setStyle("-fx-font: 24 arial;");
        gridPane.add(label, 1, 2);
        return gridPane;
    }

    /**
     * 创建输入按钮组
     * @return VBox
     */
    private VBox createButtonGroup(){
        VBox vBox = new VBox();
        vBox.setPrefSize(700.0, 550.0);
        vBox.getChildren().add(createPieceInput());
        vBox.getChildren().add(createSpeedSlider());
        Button button = new Button();
        button.setText("开始");
        Solution solution = new Solution(board.getTable(), board.getPosition(), board.getPieceNum());
        button.setOnAction(event -> solution.resolve(board.getTable(), board.getPosition(), board.getPieceNum()));
        vBox.getChildren().add(button);
        return vBox;
    }

    /**
     * 设置棋盘大小输入
     * @return HBox
     */
    private HBox createPieceInput(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefSize(700.0, 50.0);
        // 设置头部标签名称
        Label label = new Label();
        label.setPrefSize(100.0, 50.0);
        label.setText("棋盘大小:");

        TextField textField = new TextField();
        textField.setMinSize(450.0, 20.0);
        textField.setText("10");

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                if (!textField.getText().matches("[0-9]+")) {
                    textField.setText("");
                }
            }
        });

        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                int values = Integer.parseInt(textField.getText());
                if (values % 2 != 0 || values > 18 || values < 2){
                    System.err.println("输入数据不满足条件: 2 < n < 18 and n % 2 == 0");
                } else{
                    if (0 == (values & (values - 1))){
                        resetBoard(values);
                    }
                }
            }
        });


        Button button = new Button();
        button.setPrefSize(60.0, 20.0);
        button.setText("确定");

        button.setOnAction(event -> {
            int values = Integer.parseInt(textField.getText());
            if (values % 2 != 0 || values > 18 || values < 2){
                System.err.println("输入数据不满足条件: 2 < n < 18 and n % 2 == 0");
            } else{
                if (0 == (values & (values - 1))){
                    resetBoard(values);
                }
            }
        });
        hBox.getChildren().add(label);
        hBox.getChildren().add(textField);
        hBox.getChildren().add(button);
        return hBox;
    }

    /**
     * 设置速度调节按钮
     * @return HBox
     */
    private HBox createSpeedSlider(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefSize(700.0, 50.0);
        // 设置头部标签名称
        Label labelHeader = new Label();
        labelHeader.setPrefSize(50.0, 50.0);
        labelHeader.setText("速度:");
        hBox.getChildren().add(labelHeader);
        // 设置尾部显示数据标签
        Label label = new Label();
        label.setPrefSize(50.0, 50.0);
        Slider slider = new Slider(0, 100, 50);

        label.setText(String.valueOf((int)slider.getValue()));

        slider.setPrefSize(500.0, 50.0);

        slider.setBlockIncrement(2);
        // 设置速度滚动条监听事件
        slider.setOnMouseDragged(e -> label.setText(String.valueOf((int)slider.getValue())));
        slider.setOnMouseClicked(e -> label.setText(String.valueOf((int)slider.getValue())));
        hBox.getChildren().add(slider);
        hBox.getChildren().add(label);
        return hBox;
    }

}
