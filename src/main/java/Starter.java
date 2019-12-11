import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
        loader.setController(new LoginController());
        Parent root=loader.load();
        primaryStage.setTitle("Приймите пожалуйста");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }
/* FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
loader.setController(new MainController(path));
Pane mainPane = loader.load();*/
    public static void main(String[] args) {
        launch(args);
    }
}
