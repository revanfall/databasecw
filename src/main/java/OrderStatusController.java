import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrderStatusController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OrderStatus> tableStatus;

    @FXML
    private TableColumn<OrderStatus, Integer> idOrder;

    @FXML
    private TableColumn<OrderStatus, String> ordStatus;

    @FXML
    void initialize() {
        DatabaseConnection db=new DatabaseConnection();
        try {
            db.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<OrderStatus> orderStatuses=FXCollections.observableArrayList(db.getOrderStatus());
        idOrder.setCellValueFactory(new PropertyValueFactory<OrderStatus,Integer>("idOrder"));
        ordStatus.setCellValueFactory(new PropertyValueFactory<OrderStatus,String>("status"));
        tableStatus.setItems(orderStatuses);

    }
}


