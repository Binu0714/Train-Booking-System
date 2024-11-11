package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClassManageController {

    @FXML
    private TextField cinfigIdTxt;

    @FXML
    private Label classId;

    @FXML
    private TableColumn<?, ?> classIdColumn;

    @FXML
    private TextField classIdTxt;

    @FXML
    private TableView<?> classInfoTable;

    @FXML
    private Label classNo;

    @FXML
    private TableColumn<?, ?> classNoColumn;

    @FXML
    private TextField classNoTxt;

    @FXML
    private Label configId;

    @FXML
    private TableColumn<?, ?> configIdColumn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<?, ?> desColumn;

    @FXML
    private Label description;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Label seats;

    @FXML
    private Label seats1;

    @FXML
    private TableColumn<?, ?> seatsColumn;

    @FXML
    private TextField seatsTxt;

    @FXML
    private TextField tktPriceTxt;

    @FXML
    private Label trainId;

    @FXML
    private TableColumn<?, ?> trainIdColumn;

    @FXML
    private ComboBox<?> trainIdMenu;

    @FXML
    private TextField trainNameLoadTxt;

    @FXML
    private Button updateBtn;

    @FXML
    void DeleteOnAction(ActionEvent event) {

    }

    @FXML
    void ResetOnAction(ActionEvent event) {

    }

    @FXML
    void SaveOnAction(ActionEvent event) {

    }

    @FXML
    void UpdateOnAction(ActionEvent event) {

    }

}
