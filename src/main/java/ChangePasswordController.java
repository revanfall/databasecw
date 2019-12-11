import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChangePasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private TextField oldPass;

    @FXML
    private TextField newPass;

    @FXML
    private Button changeButton;

    @FXML
    void initialize() {
        changeButton.setOnAction(event -> {
            DatabaseConnection db=new DatabaseConnection();
            try {
                db.getDbConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.changePassword(new LoginWorker(login.getText().trim(),oldPass.getText().trim()),newPass.getText().trim());
        });
    }
}
