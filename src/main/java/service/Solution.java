package service;

import javafx.application.Platform;
import model.ChessBoard;
import model.Position;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author by zhoutao
 * @description 解决三格版问题的类
 * @date 2020/10/30 17:28
 */
public class Solution{

    public static double delay = 0.0;

    private int chessBoardSize;
    private Position imComplete;
    private ChessBoard chessBoard;

    public Solution(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        imComplete = chessBoard.getImComPosition();
        chessBoardSize = chessBoard.getBoardSize();
    }

    /**
     * 求解问题入口类，用于参数准备
     * 递归求解该问题的类
     */
    private void solve() {
        chessBoardSize = chessBoard.getBoardSize();
        imComplete = chessBoard.getImComPosition();
        if (this.chessBoardSize <= 2) {
            // 获取缺失点所在区域
            int area = this.judgeImCompleteArea(chessBoardSize, imComplete);
            this.chessBoard.changeColor(area);
        } else {
            // 获取到中心四个点
            ArrayList<Position> midFourPositionList = this.genMiddleFourPosition(chessBoardSize);
            // 获取缺失的点所在区域
            int area = this.judgeImCompleteArea(chessBoardSize, imComplete);
            // 填充
            this.chessBoard.changeColor(midFourPositionList, area);
            // 将其中一个中心点替换为先前残缺的点
            midFourPositionList.set(area, this.imComplete);
            for (int i = 0; i < 4; i++) {
                ChessBoard topLeftBoard = chessBoard.copyChessBoardPartial(midFourPositionList.get(i), chessBoardSize / 2, i);
                Solution solution = new Solution(topLeftBoard);
                solution.resolve();
            }
        }
    }

    /**
     * 获取棋盘中心四个点的坐标
     * @param chessBoarsSize 棋盘大小
     * @return ArrayList 四个点的列表
     */
    private ArrayList<Position> genMiddleFourPosition(int chessBoarsSize) {
        // 根据棋盘长度获取到棋盘中心四个点
        ArrayList<Position> arrayList = new ArrayList<>(4);
        // 棋盘长度除以2获取右下角的点
        Position bottomRight = new Position(chessBoarsSize / 2, chessBoarsSize / 2);
        // 右下角坐标Y减去1获得左下角点
        Position bottomLeft = new Position(bottomRight.getPositionX(), bottomRight.getPositionY() - 1);
        // 右下角坐标XY减去1获得左上角点
        Position topLeft = new Position(bottomRight.getPositionX() - 1, bottomRight.getPositionY() - 1);
        // 右下角坐标X减去1获得右上角点
        Position topRight = new Position(bottomRight.getPositionX() - 1, bottomRight.getPositionY());
        arrayList.add(topLeft);
        arrayList.add(topRight);
        arrayList.add(bottomLeft);
        arrayList.add(bottomRight);
        return arrayList;
    }

    /**
     * @param chessBoardSize 棋盘大小
     * @param imComplete     缺失点的坐标
     * @return int 缺失点所在区域，左上0，右上1，左下2，右下3
     */
    private int judgeImCompleteArea(int chessBoardSize, Position imComplete) {
        double midX = chessBoardSize / 2.0;
        double midY = chessBoardSize / 2.0;
        return (imComplete.getPositionX() < midX ? 0 : 1) * 2 + (imComplete.getPositionY() < midY ? 0 : 1);
    }


    public void resolve(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dynamicDelay(Solution.delay);
                Platform.runLater(() -> solve());
            }
            // 利用反射设置其延时的值
            private void setDeclaredField(Class<?> clazz, Object obj, String name, Object value) {
                try {
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(obj, value);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
            // 修改值
            public void dynamicDelay(double delay) {
                setDeclaredField(TimerTask.class, this, "period", (long)(delay * 1000.0));
            }
        }, (long) (Solution.delay * 1000.0));
    }
}
