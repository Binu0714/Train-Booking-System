package lk.ijse.gdse.train_booking_project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.train_booking_project.Dto.BookinCancellationDto;
import lk.ijse.gdse.train_booking_project.Dto.PassengerDto;
import lk.ijse.gdse.train_booking_project.Dto.ScheduleDto;
import lk.ijse.gdse.train_booking_project.Dto.TM.BookingCancellationTM;
import lk.ijse.gdse.train_booking_project.Dto.TM.ScheduleTM;
import lk.ijse.gdse.train_booking_project.Model.BookingCancellationModel;
import lk.ijse.gdse.train_booking_project.Model.PassengerModel;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingCancellationController implements Initializable {

    @FXML
    private TableColumn<BookingCancellationTM, String> cancelIdColumn;

    @FXML
    private TextField cancelIdTxt;

    @FXML
    private ComboBox<String> contactMenuTxt;

    @FXML
    private TableColumn<BookingCancellationTM, Date> dateColumn;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private TableColumn<BookingCancellationTM, String> nameColumn;

    @FXML
    private TableColumn<BookingCancellationTM, String> passengerIdColumn;

    @FXML
    private TextField psIdTxt;

    @FXML
    private TextField psNameTxt;

    @FXML
    private TableColumn<BookingCancellationTM, String> reasonColumn;

    @FXML
    private TextArea reasonTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<BookingCancellationTM> sheduleInfoTable;

    @FXML
    void ResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("reset button clicked..");

        refreshPage();
    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        System.out.println("save button clicked..");

    }

    @FXML
    void contactMenuTxtOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedContact = contactMenuTxt.getSelectionModel().getSelectedItem();
        PassengerDto passengerDto = passengerModel.findByContact(selectedContact);

        if (passengerDto != null) {
            psIdTxt.setText(passengerDto.getPassengerId());
            psNameTxt.setText(passengerDto.getPassengerName());
        }

    }

    @FXML
    void onClickTable(MouseEvent event) {
        BookingCancellationTM bookingCancellationTM = sheduleInfoTable.getSelectionModel().getSelectedItem();

        if (bookingCancellationTM != null) {
            cancelIdTxt.setText(bookingCancellationTM.getCancellationId());
            psIdTxt.setText(bookingCancellationTM.getPassengerId());
            dateTxt.setValue(bookingCancellationTM.getDate().toLocalDate());
            reasonTxt.setText(bookingCancellationTM.getReason());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelIdColumn.setCellValueFactory(new PropertyValueFactory<>("CancellationId"));
        passengerIdColumn.setCellValueFactory(new PropertyValueFactory<>("passengerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        try{
            loadTableData();
            loadContacts();
            configureDatePicker();

            String getNextBcId = bookingCancellationModel.getNextBcId();
            cancelIdTxt.setText(getNextBcId);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load booking cancellation id").show();
        }
    }

    BookingCancellationModel bookingCancellationModel = new BookingCancellationModel();

    public void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<BookinCancellationDto> bookinCancellationDtos = bookingCancellationModel.getAllBookingCs();

        ObservableList<BookingCancellationTM> bookingCancellationTMS = FXCollections.observableArrayList();

        for (BookinCancellationDto bookinCancellationDto : bookinCancellationDtos) {
            BookingCancellationTM bookingCancellationTM = new BookingCancellationTM(
                    bookinCancellationDto.getCancellationId(),
                    bookinCancellationDto.getPassengerId(),
                    bookinCancellationDto.getBookingId(),
                    bookinCancellationDto.getReason(),
                    bookinCancellationDto.getDate()
            );
            bookingCancellationTMS.add(bookingCancellationTM);
        }
        sheduleInfoTable.setItems(bookingCancellationTMS);
    }

    PassengerModel passengerModel = new PassengerModel();

    public void loadContacts() throws SQLException, ClassNotFoundException {
        ArrayList<String> passengerIds = passengerModel.getAllPassengerContacts();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(passengerIds);
        contactMenuTxt.setItems(observableList);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        cancelIdTxt.setText("");
        contactMenuTxt.setValue(null);
        dateTxt.setValue(null);
        psIdTxt.setText("");
        psNameTxt.setText("");
        reasonTxt.setText("");
    }

    private void configureDatePicker() {
        dateTxt.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
            @Override
            public void updateItem(java.time.LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(java.time.LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }
}
