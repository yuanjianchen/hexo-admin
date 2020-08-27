package tech.stack.hexo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tech.stack.hexo.util.HttpUtils;

public class App extends Application {

    public static volatile boolean flag = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        while (flag) {
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/fxml/MyScene.fxml"));

            primaryStage.setTitle("Hexo-Admin");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> {
                System.out.print("监听到窗口关闭");
                String s = HttpUtils.sendPost("http://localhost:8080/actuator/shutdown", null);
                System.out.println(s);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
