package controller;


import events.DraggerEvent;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.layout.AnchorPane;
import model.ChessBoard;
import model.Position;
import model.cell.CellType;
import service.Solution;

import java.util.ArrayList;

/**
 * @author by zhoutao
 * @description 页面布局类
 * @date 2020/10/31 22:11
 */
public class LauncherController {
    int borderSize = 2;
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
        this.manualStartButtonEventListener();
        this.cancelButtonEventLister();
    }

    // 棋盘设置按钮监听事件
    private void radioEventListener(){
        toolMenuController.getToggleGroup().selectedToggleProperty().addListener(
            (ObservableValue<? extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
                if (toolMenuController.getToggleGroup().getSelectedToggle() != null) {
                    if (!new_Toggle.getUserData().equals("")){
                        threeGridController.resetLabel();
                        borderSize = Integer.parseInt(new_Toggle.getUserData().toString());
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
            toolMenuController.getCancelButton().setDisable(true);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    solution.resolve();
                }
            }.start();
            toolMenuController.getAutoStartButton().setDisable(true);
            DraggerEvent.getHashMap().clear();
            DraggerEvent.setStep(0);
        });
    }

    // 速度滚动条事件监听
    private void speedSliderEventListener(){
        toolMenuController.getSpeedSlider().valueProperty().addListener((ov, oldValue, newValue) -> new Thread() {
            @Override
            public void run() {
                super.run();
                Platform.runLater(()-> {
                    Solution.setDelay(1.0 - newValue.doubleValue() / 100.0);
                    toolMenuController.setSpeedShowLabel(newValue.intValue());
                });
            }
        }.start());
    }

    public void manualStartButtonEventListener(){
        toolMenuController.getManualStartButton().setOnAction(event -> {
            toolMenuController.getCancelButton().setDisable(false);
            threeGridController.dragEvent();
        });
    }

    public void cancelButtonEventLister(){
        toolMenuController.getCancelButton().setOnAction(event -> {
            if (DraggerEvent.getStep() > 0){
                DraggerEvent.getHashMap().get(DraggerEvent.getStep() - 1).forEach(position -> ChessBoard.getInstance().getTable().get(position.getPositionX()).get(position.getPositionY()).setCellType(CellType.DEFAULT));
                DraggerEvent.setStep(DraggerEvent.getStep() - 1);
            }
        });

    }
}
