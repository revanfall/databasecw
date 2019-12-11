import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dnl.utils.text.table.TextTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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

public class BossWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane printList;

    @FXML
    private SplitMenuButton workerMethList;

    @FXML
    private MenuItem getOrders;

    @FXML
    private MenuItem getAllPayments;

    @FXML
    private MenuItem getPaid;

    @FXML
    private MenuItem registerWorker;

    @FXML
    private MenuItem deleteWorker;

    @FXML
    private MenuItem setPayment;

    @FXML
    private MenuItem createOrder;

    @FXML
    private MenuItem printWorkers;

    @FXML
    private MenuItem updatePassword;

    @FXML
    private MenuItem salaryWtihBonuses;

    @FXML
    private SplitMenuButton orderButList;

    @FXML
    private MenuItem showBonusesSal;

    @FXML
    private TableView<Worker> tableEmpl;

    @FXML
    private TableColumn<Worker, Integer> idEmpl;

    @FXML
    private TableColumn<Worker, String> tableName;

    @FXML
    private TableColumn<Worker, String> tablSurname;

    @FXML
    private TableColumn<Worker, String> tablPhone;

    @FXML
    private TableColumn<Worker, String> tableTitle;

    @FXML
    void initialize() {

        DatabaseConnection db=new DatabaseConnection();
        try {
            db.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Worker> list=FXCollections.observableArrayList(db.getWorkers());
        idEmpl.setCellValueFactory(new PropertyValueFactory<Worker,Integer>("idWorker"));
        tableName.setCellValueFactory(new PropertyValueFactory<Worker,String>("name"));
        tablSurname.setCellValueFactory(new PropertyValueFactory<Worker,String>("surname"));
        tablPhone.setCellValueFactory(new PropertyValueFactory<Worker,String>("phone"));
        tableTitle.setCellValueFactory(new PropertyValueFactory<Worker,String>("titlename"));
        tableEmpl.setItems(list);

        registerWorker.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationTable.fxml"));
            loader.setController(new Registration());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,600,400));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
               });


        deleteWorker.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteWorker.fxml"));
            loader.setController(new DeleteController());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        getOrders.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrdersTable.fxml"));
            loader.setController(new OrdersController());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,300, 300));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getAllPayments.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentTable.fxml"));
            loader.setController(new PaymentController());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        getPaid.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderStatus.fxml"));
            loader.setController(new OrderStatusController());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setPayment.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentSetter.fxml"));
            loader.setController(new SetPaymentController());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createOrder.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderCreation.fxml"));
            loader.setController(new CreateOrderController());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        printWorkers.setOnAction(event -> {
           ArrayList<Worker> workerArrayList= db.getWorkers();
            try {
                FileWriter writer=new FileWriter("workerlist5.txt");
                String wob=null;
                String end=" _____________Рабочие нашей компании__________\n";
                writer.write(end);
                for(Worker w:workerArrayList){
                    wob=w.getIdWorker()+"---"+w.getName()+"---"+w.getSurname()+"---"+w.getPhone()+"---"+w.getTitlename()+System.lineSeparator();
                writer.write(wob);
                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        salaryWtihBonuses.setOnAction(event -> {
            ArrayList<BonusesTable> salo=db.getSalaryWithBonuses();
            FileWriter writer= null;
            try {
                writer = new FileWriter("salaries.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String first=null;
            String end=" _____________Зарплаты с бонусами___________\n";
            try {
                writer.write(end);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(BonusesTable b:salo){
                try {
                    writer.write(b.getWname()+"---"+b.getWsurname()+"---"+b.getTitlename()+"---"+b.getIdOrder()+"---"+b.getBonuses()+System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                writer.close();
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

        showBonusesSal.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SalariesTable.fxml"));
            loader.setController(new SalariesTableController());
            try {
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }});


    }
}
