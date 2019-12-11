import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WorkerTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private SplitMenuButton workerMethList;

    @FXML
    private AnchorPane printList;

    @FXML
    private SplitMenuButton orderButList;

    @FXML
    private MenuItem updatePassword;

    @FXML
    private MenuItem getOrders;

    @FXML
    private MenuItem getAllPayments;

    @FXML
    private MenuItem getPaid;

    @FXML
    private MenuItem setPayment;

    @FXML
    private MenuItem createOrder;

    @FXML
    private TableView<Order> tableEmpl;

    @FXML
    private TableColumn<Order, Date> apDate;

    @FXML
    private TableColumn<Order, String> orType;

    @FXML
    private TableColumn<Order, Integer> idWorker;

    @FXML
    private TableColumn<Order, Double> orPrice;

    @FXML
    private TableColumn<Order, Integer> orQuan;

    @FXML
    void initialize() {

        DatabaseConnection db = new DatabaseConnection();
        try {
            db.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CurrentUser cr = CurrentUser.getInstance(db.loginUser(new LoginWorker("admin", "1234")));
        ObservableList<Order> list = FXCollections.observableArrayList(db.getCurrentWorkerOrders(cr.worker));
        apDate.setCellValueFactory(new PropertyValueFactory<Order, Date>("appointmentDate"));
        orType.setCellValueFactory(new PropertyValueFactory<Order, String>("orderType"));
        idWorker.setCellValueFactory(new PropertyValueFactory<Order, Integer>("idEmployee"));
        orPrice.setCellValueFactory(new PropertyValueFactory<Order, Double>("orderPrice"));
        orQuan.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderQuant"));
        tableEmpl.setItems(list);


        getAllPayments.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentTable.fxml"));
            loader.setController(new PaymentController());
            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        getPaid.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderStatus.fxml"));
            loader.setController(new OrderStatusController());
            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setPayment.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentSetter.fxml"));
            loader.setController(new SetPaymentController());
            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updatePassword.setOnAction(event -> {FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePass.fxml"));
            loader.setController(new ChangePasswordController());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
