package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class BookingManageController {

    @FXML
    private TableColumn<?, ?> bookinIdColumn;

    @FXML
    private TextField bookingIdTxt;

    @FXML
    private TableView<?> bookingInfoTable;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> classColumn;

    @FXML
    private ComboBox<?> classComboTxt;

    @FXML
    private TextField classSeatstxt;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private ComboBox<?> destinationComboTxt;

    @FXML
    private TextField durationTxt;

    @FXML
    private TableColumn<?, ?> endStationColumn;

    @FXML
    private TableColumn<?, ?> noOfSeatsColumn;

    @FXML
    private TextField noOfSeatsTxt;

    @FXML
    private TableColumn<?, ?> passengerIdColumn;

    @FXML
    private ComboBox<?> passengerIdComboTxt;

    @FXML
    private TextField passengerNameTxt;

    @FXML
    private Button paymentBtn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private Button resetBtn;

    @FXML
    private ComboBox<?> scheduleComboTxt;

    @FXML
    private TableColumn<?, ?> scheduleIdColumn;

    @FXML
    private TextField stationIdTxt;

    @FXML
    private TextField stationTxt;

    @FXML
    private TextField ticketPriceTxt;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void resetOnAction(ActionEvent event) {

    }

}
