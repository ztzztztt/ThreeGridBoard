package controller;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.layout.AnchorPane;
import model.ChessBoard;
import service.Solution;
import service.Task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author by zhoutao
 * @description 页面布局类
 * @date 2020/10/31 22:11
 */
public class LauncherController {
    AnchorPane rootPane;
    ChessBoardController chessBoardController = ChessBoardController.getInstance();
    ThreeGridController threeGridController = ThreeGridController.getInstance();
    ToolMenuController toolMenuController = ToolMenuController.getInstance();

    private static LauncherController launcherController;

    public static LauncherController getInstance(){
        if (launcherController == null){
            launcherController = new LauncherController();
        }
        return launcherController;
    }

    private LauncherController(){
        this.initialAnchorPane();
        // 启动事件监听
        this.initEvent();
    }

    private void initialAnchorPane(){
        rootPane = new AnchorPane();
        rootPane.setPrefSize(1400.0, 900.0);
        rootPane.setStyle("-fx-background-color: #FFFFFF;");
        rootPane.getChildren().add(chessBoardController.getChessBoardPane());
        rootPane.getChildren().add(threeGridController.getThreeVBox());
        rootPane.getChildren().add(toolMenuController.getToolMenuVBox());
    }

    public AnchorPane getAnchorPane(){
        return this.rootPane;
    }

    // 事件监听
    private void initEvent(){
        this.radioEventListener();
        this.autoButtonEventListener();
        this.speedSliderEventListener();
    }

    // 棋盘设置按钮监听事件
    private void radioEventListener(){
        toolMenuController.getToggleGroup().selectedToggleProperty().addListener(
            (ObservableValue<? extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
                if (toolMenuController.getToggleGroup().getSelectedToggle() != null) {
                    if (!new_Toggle.getUserData().equals("")){
                        threeGridController.resetLabel();
                        chessBoardController.resetDefaultSize(Integer.parseInt(new_Toggle.getUserData().toString()));
                        toolMenuController.getAutoStartButton().setDisable(false);
                    }
                }
            });
    }

    private final Solution solution = new Solution(ChessBoard.getInstance());

    // 求解问题事件监听
    private void autoButtonEventListener(){
        toolMenuController.getAutoStartButton().setOnAction(event -> {
            solution.resolve();
            toolMenuController.getAutoStartButton().setDisable(true);
        });
    }

    // 速度滚动条事件监听
    private void speedSliderEventListener(){
        toolMenuController.getSpeedSlider().valueProperty().addListener((ov, oldValue, newValue) -> {
            Solution.setDelay(newValue.doubleValue() / 100.0);
            toolMenuController.setSpeedShowLabel(newValue.intValue());
        });
    }

    private void manualStartButtonEventListener(){
        toolMenuController.getManualStartButton().setOnAction(event -> {

        });
    }
}
