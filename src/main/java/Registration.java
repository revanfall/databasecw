 import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
 import javafx.scene.control.Alert;
 import javafx.scene.control.Button;
import javafx.scene.control.TextField;

 public class Registration {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField emplName;

        @FXML
        private TextField emplSurname;

        @FXML
        private TextField emplPhone;

        @FXML
        private TextField emplTitle;

        @FXML
        private Button registerButton;

        @FXML
        private TextField emplLogin;

        @FXML
        private TextField emplPass;

        @FXML
        void initialize() {
            registerButton.setOnAction(event -> {


                if(emplName.getText().trim().isEmpty() || emplTitle.getText().trim().isEmpty() || emplSurname.getText().trim().isEmpty() || emplPhone.getText().trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Внимание");
                    alert.setHeaderText("Внимание");
                    alert.setContentText("Заполниете все поля");

                    alert.showAndWait();

                }

                else {
                    DatabaseConnection db=new DatabaseConnection();
                    Worker worker=new Worker(emplName.getText().trim(),emplSurname.getText().trim(),
                            emplPhone.getText().trim(), Integer.parseInt(emplTitle.getText().trim()));
                    db.registerWorker(worker);
                    db.setAuthorisation(new LoginWorker(emplLogin.getText().trim(), emplPass.getText().trim(), db.getWorkerId(emplSurname.getText().trim())));
                }
                });

        }
    }


