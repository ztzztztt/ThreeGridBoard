
import controller.Board;
import controller.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author by zhoutao
 * @description 残缺棋盘程序启动类
 * @date 2020/10/29 20:48
 */

public class ChessBoardApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {
        try{

            Board board = new Board();
            Menu rightMenu = new Menu(board);
            // 创建棋盘
            GridPane chessBoard = board.createGrid();
            // 创建右侧菜单栏
            VBox menu = rightMenu.createMenu();

            BorderPane rootPage = new BorderPane();
            rootPage.setLeft(chessBoard);
            rootPage.setRight(menu);
            Scene scene = new Scene(rootPage, 1600, 900);
            primaryStage.setTitle("残缺棋盘0.1");
            primaryStage.setScene(scene);
            // 设置窗口大小不可更改
            primaryStage.setResizable(false);
            // 显示窗口大小
            primaryStage.show();
        } catch (NullPointerException e){
            System.out.println("加载程序样式文件出错");
        }
    }
}