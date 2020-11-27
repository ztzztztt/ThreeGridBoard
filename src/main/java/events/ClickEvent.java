package events;

import controller.LauncherController;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import model.ChessBoard;
import model.DoublePosition;
import model.threegrid.DraggerThreeGrid;

/**
 * @author by zhoutao
 * @description TODO
 * @date 2020/11/26 22:11
 */
public class ClickEvent {
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
            Node threeNode = DraggerThreeGrid.generateThreeGrid(key, ChessBoard.getInstance().getBoardSize());
            threeNode.setId(String.valueOf(key));
            threeNode.setLayoutX(900.0 + pos.x + itemY * 200);
            threeNode.setLayoutY(pos.y + itemX * 200.0);
            DraggerEvent.draggable(threeNode);
            LauncherController.getInstance().getAnchorPane().getChildren().add(threeNode);
        });
    }
}
