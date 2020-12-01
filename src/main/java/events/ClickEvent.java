package events;

import controller.LauncherController;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import model.ChessBoard;
import model.DoublePosition;
import model.threegrid.*;

/**
 * @author by zhoutao
 * @description 点击事件
 * @date 2020/11/26 22:11
 */
public class ClickEvent {

    static Node topLeftGird = null;
    static Node topRightGrid = null;
    static Node bottomLeftGrid = null;
    static Node bottomRightGrid = null;

    public static void click(Node node) {
        final DoublePosition pos = new DoublePosition();
        // 提示用户该结点可点击
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> node.setCursor(Cursor.HAND));
        node.addEventHandler(MouseEvent.MOUSE_EXITED, event -> node.setCursor(Cursor.DEFAULT));

        // 提示用户该结点可拖拽
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            node.setCursor(Cursor.MOVE);
            // 当按压事件发生时，缓存事件发生的位置坐标
            pos.x = event.getX();
            pos.y = event.getY();
            int key = Integer.parseInt(node.getId());
            int itemX = key / 2;
            int itemY = key % 2;
            switch (key){
                case 0:
                    if (topLeftGird != null){
                        break;
                    }
                    topLeftGird = DraggerThreeGrid.generateThreeGrid(key, ChessBoard.getInstance().getBoardSize());
                    topLeftGird.setId(String.valueOf(key));
                    topLeftGird.setLayoutX(900.0 + pos.x + itemY * 200);
                    topLeftGird.setLayoutY(pos.y + itemX * 200.0);
                    DraggerEvent.draggable(topLeftGird);
                    LauncherController.getInstance().getAnchorPane().getChildren().add(topLeftGird);
                    break;
                case 1:
                    if (topRightGrid != null){
                        break;
                    }
                    topRightGrid = DraggerThreeGrid.generateThreeGrid(key, ChessBoard.getInstance().getBoardSize());
                    topRightGrid.setId(String.valueOf(key));
                    topRightGrid.setLayoutX(900.0 + pos.x + itemY * 200);
                    topRightGrid.setLayoutY(pos.y + itemX * 200.0);
                    DraggerEvent.draggable(topRightGrid);
                    LauncherController.getInstance().getAnchorPane().getChildren().add(topRightGrid);
                    break;
                case 2:
                    if (bottomLeftGrid != null){
                        break;
                    }
                    bottomLeftGrid = DraggerThreeGrid.generateThreeGrid(key, ChessBoard.getInstance().getBoardSize());
                    bottomLeftGrid.setId(String.valueOf(key));
                    bottomLeftGrid.setLayoutX(900.0 + pos.x + itemY * 200);
                    bottomLeftGrid.setLayoutY(pos.y + itemX * 200.0);
                    DraggerEvent.draggable(bottomLeftGrid);
                    LauncherController.getInstance().getAnchorPane().getChildren().add(bottomLeftGrid);
                    break;
                case 3:
                    if (bottomRightGrid != null){
                        break;
                    }
                    bottomRightGrid = DraggerThreeGrid.generateThreeGrid(key, ChessBoard.getInstance().getBoardSize());
                    bottomRightGrid.setId(String.valueOf(key));
                    bottomRightGrid.setLayoutX(900.0 + pos.x + itemY * 200);
                    bottomRightGrid.setLayoutY(pos.y + itemX * 200.0);
                    DraggerEvent.draggable(bottomRightGrid);
                    LauncherController.getInstance().getAnchorPane().getChildren().add(bottomRightGrid);
                    break;
                default:
                    break;
            }
        });
    }

    public static void clearNode(int key){
        switch (key){
            case 0:
                topLeftGird = null;
                break;
            case 1:
                topRightGrid = null;
                break;
            case 2:
                bottomLeftGrid = null;
                break;
            case 3:
                bottomRightGrid = null;
                break;
            default:
                break;
        }
    }
}
