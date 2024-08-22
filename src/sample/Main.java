package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("远行星星系编辑器");
        Scene scene = new Scene(root, 800, 800);
        //scene.getStylesheets().add(Main.class.getResource("resources/bootstrap3.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        /*
        String regex = "krz_[1-5]";
        boolean isMatch = "krz_51".matches(regex);
        System.out.println(isMatch);
         */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
