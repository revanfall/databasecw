import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SetPaymentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button setPayment;

    @FXML
    private TextField pa;

    @FXML
    private TextField paySum;

    @FXML
    void initialize() {
        DatabaseConnection db=new DatabaseConnection();
        try {
            db.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setPayment.setOnAction(event -> {
            db.setPayment(new OrderPayment(Integer.parseInt(pa.getText()),Double.parseDouble(paySum.getText())));
        });
    }
}
