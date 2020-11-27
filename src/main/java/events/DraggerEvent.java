package events;

import controller.ChessBoardController;
import controller.LauncherController;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import model.ChessBoard;
import model.DoublePosition;
import model.Position;
import model.cell.CellType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author by zhoutao
 * @description 事件
 * @date 2020/11/24 21:38
 */
public class DraggerEvent {
    private static int borderSize = 8;
    private static double cellSize = 40.0;
    private static double borderOffset = 0;
    private static int step = 0;
    private static final HashMap<Integer, ArrayList<Position>> hashMap = new HashMap<>();

    public static void draggable(Node node){
        final DoublePosition pos = new DoublePosition();
        final DoublePosition finPos = new DoublePosition();
        // 提示用户该结点可点击
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> node.setCursor(Cursor.HAND));
        node.addEventHandler(MouseEvent.MOUSE_EXITED, event -> node.setCursor(Cursor.DEFAULT));
        // 提示用户该结点可拖拽
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            node.setCursor(Cursor.MOVE);
            // 当按压事件发生时，缓存事件发生的位置坐标
            pos.x = event.getX();
            pos.y = event.getY();
        });

        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            node.setCursor(Cursor.DEFAULT);
            updateParams();
            System.out.println(borderOffset);
            int x = (int)((finPos.x - borderOffset) / cellSize);
            int y = (int)((finPos.y - borderOffset) / cellSize);
            if (x >= borderSize || y >= borderSize){
                System.out.println("Out of Chess Border");
            } else{
                int key = Integer.parseInt(node.getId());
                ArrayList<Position> arrayList = new ArrayList<>(3);
                switch(key){
                    case 0:
                        arrayList.add(new Position(y+1, x));
                        arrayList.add(new Position(y, x+1));
                        arrayList.add(new Position(y+1, x+1));
                        hashMap.put(step, arrayList);
                        ChessBoard.getInstance().getTable().get(y + 1).get(x).setCellType(CellType.getCellTypeByValue(key));
                        ChessBoard.getInstance().getTable().get(y).get(x + 1).setCellType(CellType.getCellTypeByValue(key));
                        ChessBoard.getInstance().getTable().get(y + 1).get(x + 1).setCellType(CellType.getCellTypeByValue(key));
                        break;
                    case 1:
                        arrayList.add(new Position(y, x));
                        arrayList.add(new Position(y+1, x));
                        arrayList.add(new Position(y+1, x+1));
                        hashMap.put(step, arrayList);
                        ChessBoard.getInstance().getTable().get(y).get(x).setCellType(CellType.getCellTypeByValue(key));
                        ChessBoard.getInstance().getTable().get(y + 1).get(x).setCellType(CellType.getCellTypeByValue(key));
                        ChessBoard.getInstance().getTable().get(y + 1).get(x + 1).setCellType(CellType.getCellTypeByValue(key));
                        break;
                    case 2:
                        arrayList.add(new Position(y, x));
                        arrayList.add(new Position(y, x+1));
                        arrayList.add(new Position(y+1, x+1));
                        hashMap.put(step, arrayList);
                        ChessBoard.getInstance().getTable().get(y).get(x).setCellType(CellType.getCellTypeByValue(key));
                        ChessBoard.getInstance().getTable().get(y).get(x + 1).setCellType(CellType.getCellTypeByValue(key));
                        ChessBoard.getInstance().getTable().get(y + 1).get(x + 1).setCellType(CellType.getCellTypeByValue(key));
                        break;
                    case 3:
                        arrayList.add(new Position(y, x));
                        arrayList.add(new Position(y, x+1));
                        arrayList.add(new Position(y+1, x));
                        hashMap.put(step, arrayList);
                        ChessBoard.getInstance().getTable().get(y).get(x).setCellType(CellType.getCellTypeByValue(key));
                        ChessBoard.getInstance().getTable().get(y).get(x + 1).setCellType(CellType.getCellTypeByValue(key));
                        ChessBoard.getInstance().getTable().get(y + 1).get(x).setCellType(CellType.getCellTypeByValue(key));
                        break;
                    default:
                        break;
                }
                step ++;
                // 清楚原来的节点
                LauncherController.getInstance().getAnchorPane().getChildren().remove(node);
            }
        });
        // 实现拖拽功能
        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            updateParams();
            double distanceX = event.getX() - pos.x;
            double distanceY = event.getY() - pos.y;

            double x = node.getLayoutX() + distanceX;
            double y = node.getLayoutY() + distanceY;

            // 由于棋盘之前偏移了10个像素，则需要将其修改回来
            x = (int)(x / cellSize) * cellSize + 10;
            y = (int)(y / cellSize) * cellSize + 10;
            finPos.x = x;
            finPos.y = y;
            node.relocate(x, y);
        });
    }

    private static void updateParams(){
        borderSize = ChessBoard.getInstance().getBoardSize();
        cellSize = Math.min((880.0 / borderSize), cellSize);
        borderOffset = ChessBoardController.getInstance().getChessBoardPane().getChildren().get(0).getLayoutX();
    }

    public static HashMap<Integer, ArrayList<Position>> getHashMap(){
        return hashMap;
    }

    public static int getStep(){
        return step;
    }

    public static void setStep(int s){
        step = s;
    }
}
