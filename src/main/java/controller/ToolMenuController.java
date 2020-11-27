package controller;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


/**
 * @author by zhoutao
 * @description 菜单工具布局UI组件
 * @date 2020/11/1 17:25
 */
public class ToolMenuController {
    // 整个菜单组件
    private VBox toolMenuVBox;
    // 速度调节按钮
    private HBox speedHBox;
    private Slider speedSlider;
    private Label speedShowLabel;
    // 棋盘设置组
    private HBox boardSettingHBox;
    private ToggleGroup toggleGroup;
    // 按钮组: 自动求解、手动求解
    private HBox buttonHBox;
    private Button autoStartButton;
    private Button manualStartButton;
    private Button cancelButton;

    private static ToolMenuController toolMenuController;

    public static ToolMenuController getInstance(){
        if (toolMenuController == null){
            toolMenuController = new ToolMenuController();
        }
        return toolMenuController;
    }

    private ToolMenuController(){
        this.initialMenuVBox();
    }

    private void initialMenuVBox(){
        toolMenuVBox = new VBox();
        toolMenuVBox.setPrefSize(480.0, 470.0);
        toolMenuVBox.setLayoutX(910.0);
        toolMenuVBox.setLayoutY(420.0);
        toolMenuVBox.setStyle("-fx-background-color: #f3f3f3;");
        this.initialSpeedHBox();
        this.initialBoardSettingHBox();
        this.initialButtonHBox();

        toolMenuVBox.getChildren().add(speedHBox);
        toolMenuVBox.getChildren().add(boardSettingHBox);
        toolMenuVBox.getChildren().add(buttonHBox);
    }

    private void initialSpeedHBox(){
        speedHBox = new HBox();
        speedHBox.setPrefSize(200.0, 80.0);
        speedHBox.setAlignment(Pos.CENTER);
        // 速度标签名
        Label speedLabel = new Label();
        speedLabel.setTextAlignment(TextAlignment.CENTER);
        speedLabel.setText("速度:");
        speedLabel.setFont(Font.font("Arial",18));
        HBox.setMargin(speedLabel, new Insets(0, 10, 0, 10));
        speedHBox.getChildren().add(speedLabel);

        // 速度滑动条
        speedSlider = new Slider();
        speedSlider.setPrefWidth(350.0);
        speedSlider.setValue(50.0);
        speedHBox.getChildren().add(speedSlider);

        // 速度值显示标签名
        speedShowLabel = new Label();
        speedShowLabel.setTextAlignment(TextAlignment.CENTER);
        speedShowLabel.setPrefWidth(40);
        speedShowLabel.setText("50");
        speedShowLabel.setFont(Font.font("Arial", 18));
        HBox.setMargin(speedShowLabel, new Insets(0, 10, 0, 10));
        speedHBox.getChildren().add(speedShowLabel);
    }

    private void initialBoardSettingHBox(){
        boardSettingHBox = new HBox();
        boardSettingHBox.setPrefSize(200.0, 80.0);
        boardSettingHBox.setAlignment(Pos.CENTER);

        Label boardSettingLabel = new Label();
        boardSettingLabel.setText("棋盘设置:");
        boardSettingLabel.setTextAlignment(TextAlignment.CENTER);
        boardSettingLabel.setFont(Font.font("Arial", 18));

        boardSettingHBox.getChildren().add(boardSettingLabel);

        toggleGroup = new ToggleGroup();
        for (int i=1;i<6;i++){
            RadioButton radioButton = new RadioButton();
            String name = String.valueOf((int)Math.pow(2, i));
            radioButton.setText(String.format("%s*%s", name, name));
            radioButton.setUserData((int)Math.pow(2, i));
            radioButton.setMnemonicParsing(false);
            HBox.setMargin(radioButton, new Insets(0, 10, 0, 10));
            radioButton.setToggleGroup(toggleGroup);
            boardSettingHBox.getChildren().add(radioButton);

            if (i == 3){
                radioButton.setSelected(true);
            }
        }
    }

    private void initialButtonHBox(){
        buttonHBox = new HBox();
        buttonHBox.setPrefSize(200.0, 80.0);
        buttonHBox.setAlignment(Pos.CENTER);

        autoStartButton = new Button();
        autoStartButton.setText("自动求解");
        autoStartButton.setMnemonicParsing(false);
        autoStartButton.setStyle("-fx-border-radius: 25px;");
        HBox.setMargin(autoStartButton, new Insets(0, 10, 0, 10));
        buttonHBox.getChildren().add(autoStartButton);

        manualStartButton = new Button();
        manualStartButton.setText("手动求解");
        manualStartButton.setMnemonicParsing(false);
        manualStartButton.setStyle("-fx-border-radius: 25px;");
        HBox.setMargin(manualStartButton, new Insets(0, 10, 0, 10));
        buttonHBox.getChildren().add(manualStartButton);

        cancelButton = new Button();
        cancelButton.setText("撤销");
        cancelButton.setMnemonicParsing(false);
        cancelButton.setStyle("-fx-border-radius: 25px;");
        HBox.setMargin(cancelButton, new Insets(0, 10, 0, 10));
        buttonHBox.getChildren().add(cancelButton);
        cancelButton.setDisable(true);
    }

    public VBox getToolMenuVBox(){
        return this.toolMenuVBox;
    }

    public ToggleGroup getToggleGroup(){
        return this.toggleGroup;
    }

    public Button getAutoStartButton(){
        return autoStartButton;
    }

    public Slider getSpeedSlider(){
        return this.speedSlider;
    }

    public Button getManualStartButton(){
        return this.manualStartButton;
    }

    public void setSpeedShowLabel(int value){
        if (value <= 100 && value >= 0){
            speedShowLabel.setText(String.valueOf(value));
        } else {
            System.err.println("输入的速度不能超过0-100");
        }
    }

    public Button getCancelButton(){
        return this.cancelButton;
    }

    public void setCancelButtonIsAble(boolean cancel){
        this.cancelButton.setDisable(cancel);
    }
}
