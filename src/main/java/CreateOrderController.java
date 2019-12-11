
    import java.net.URL;
    import java.sql.SQLException;
    import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

    public class CreateOrderController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField advertType;

        @FXML
        private TextField adPrice;

        @FXML
        private TextField adQuant;

        @FXML
        private Button createOrder;

        @FXML
        void initialize() {
            createOrder.setOnAction(event -> {
                DatabaseConnection db=new DatabaseConnection();
                try {
                    db.getDbConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                    }
                    CurrentUser cr=CurrentUser.getInstance(db.loginUser(new LoginWorker("neadmin","12345")));
                    db.addOrder(new Order(advertType.getText().trim(),cr.worker.getIdWorker(),Double.parseDouble(adPrice.getText()),
                    Integer.parseInt(adQuant.getText())));
            });
        }
    }

