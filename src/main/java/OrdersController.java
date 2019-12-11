import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Order> ordTable;

    @FXML
    private TableColumn<Order, Date> dateTabl;

    @FXML
    private TableColumn<Order, String> typeTabl;

    @FXML
    private TableColumn<Order, Double> priceTabl;

    @FXML
    private TableColumn<Order, Integer> numCol;

    @FXML
    private TableColumn<Order, Integer> emplCol;

    @FXML
    void initialize() {
        DatabaseConnection db=new DatabaseConnection();
        try {
            db.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Order> list=FXCollections.observableArrayList(db.getAllOrders());
        dateTabl.setCellValueFactory(new PropertyValueFactory<Order,Date>("appointmentDate"));
        typeTabl.setCellValueFactory(new PropertyValueFactory<Order,String>("orderType"));
        emplCol.setCellValueFactory(new PropertyValueFactory<Order,Integer>("idEmployee"));
        priceTabl.setCellValueFactory(new PropertyValueFactory<Order,Double>("orderPrice"));
        numCol.setCellValueFactory(new PropertyValueFactory<Order,Integer>("orderQuant"));
        ordTable.setItems(list);

    }
}