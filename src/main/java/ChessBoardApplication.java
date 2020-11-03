

import controller.LauncherController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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

//    public void start(Stage primaryStage) {
//        try{
//            Parent root = FXMLLoader.load(getClass().getResource("application.fxml"));
//            Scene scene = new Scene(root);
//            primaryStage.getIcons().add(new Image("images/favicon.png"));
//            primaryStage.setTitle("残缺棋盘0.1");
//            primaryStage.setScene(scene);
//            // 设置窗口大小不可更改
//            primaryStage.setResizable(false);
//            // 显示窗口大小
//            primaryStage.show();
//        } catch (NullPointerException | IOException e){
//            System.out.println("加载程序样式文件出错");
//            System.err.println(e);
//        }
//    }

    public void start(Stage primaryStage) {
        try{
            AnchorPane rootPage = LauncherController.getInstance().getAnchorPane();
            Scene scene = new Scene(rootPage,1400.0, 900.0);
            primaryStage.setTitle("残缺棋盘0.1");
            primaryStage.getIcons().add(new Image("images/favicon.png"));
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