import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class PaymentController {

    @FXML
    private TableView<OrderPayment> paymentTable;

    @FXML
    private TableColumn<OrderPayment, Integer> idPayCol;

    @FXML
    private TableColumn<OrderPayment, Integer> idOrdPay;

    @FXML
    private TableColumn<OrderPayment, Double> paymentColumn;

    @FXML
    void initialize(){
        DatabaseConnection db=new DatabaseConnection();
        try {
            db.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<OrderPayment> orderPayments=FXCollections.observableArrayList(db.getAllPayments());
        idPayCol.setCellValueFactory(new PropertyValueFactory<OrderPayment,Integer>("paymentId"));
        idOrdPay.setCellValueFactory(new PropertyValueFactory<OrderPayment,Integer>("orderId"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<OrderPayment,Double>("orderPay"));
        paymentTable.setItems(orderPayments);
    }

}
