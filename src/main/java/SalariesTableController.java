import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;

public class SalariesTableController {

    @FXML
    private TableView<BonusesTable> paymentTable;

    @FXML
    private TableColumn<BonusesTable, String> name;

    @FXML
    private TableColumn<BonusesTable, String> surname;

    @FXML
    private TableColumn<BonusesTable, String> titlename;

    @FXML
    private TableColumn<BonusesTable, Integer> idorder;

    @FXML
    private TableColumn<BonusesTable, Double> sumsalary;

    @FXML
    void initialize() {
        DatabaseConnection db=new DatabaseConnection();
        try {
            db.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<BonusesTable> list=FXCollections.observableArrayList(db.getSalaryWithBonuses());
        name.setCellValueFactory(new PropertyValueFactory<BonusesTable, String>("wname"));
        surname.setCellValueFactory(new PropertyValueFactory<BonusesTable, String>("wsurname"));
        titlename.setCellValueFactory(new PropertyValueFactory<BonusesTable, String>("titlename"));
        idorder.setCellValueFactory(new PropertyValueFactory<BonusesTable, Integer>("idOrder"));
        sumsalary.setCellValueFactory(new PropertyValueFactory<BonusesTable, Double>("bonuses"));
        paymentTable.setItems(list);

//        String wname;
//        String wsurname;
//        String titlename;
//        int idOrder;
//        double bonuses;
    }

}
