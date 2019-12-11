import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteController {

    @FXML
    private Button deleteButton;

    @FXML
    private TextField nameBlock;

    @FXML
    private TextField surnameBlock;



    @FXML
    void initialize() {
       deleteButton.setOnAction(event -> {
           DatabaseConnection db=new DatabaseConnection();
           db.deleteWotker(new Worker(nameBlock.getText().trim(),surnameBlock.getText().trim()));
       });

    }
}
