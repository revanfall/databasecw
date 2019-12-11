import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField loginField;

    @FXML
    private Button SignInBut;

    @FXML
    private PasswordField passwordField;

@FXML
    public void initialize(){
    SignInBut.setOnAction(event->{
        DatabaseConnection db=new DatabaseConnection();
        try {
            db.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(db.loginUser(new LoginWorker(loginField.getText().trim(), passwordField.getText().trim())));
        CurrentUser cr=CurrentUser.getInstance(db.loginUser(new LoginWorker(loginField.getText().trim(),
                passwordField.getText().trim())));

        if(cr.worker.getTitle()==1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WorkerWindow.fxml"));
            loader.setController(new WorkerTableController());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,700,800));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        else if(cr.worker.getTitle()==2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BossWindow.fxml"));
            loader.setController(new BossWindow());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,700,800));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(cr.worker.getTitle()==3) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BuhController.fxml"));
            loader.setController(new BuhControllerController());
            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 700, 800));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    });
}

}
