package lk.ijse.gdse.train_booking_project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.gdse.train_booking_project.Dto.ClassDto;
import lk.ijse.gdse.train_booking_project.Dto.EndStationDto;
import lk.ijse.gdse.train_booking_project.Dto.PassengerDto;
import lk.ijse.gdse.train_booking_project.Dto.ScheduleDto;
import lk.ijse.gdse.train_booking_project.Model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingManageController implements Initializable {

    @FXML
    private TableColumn<?, ?> bookinIdColumn;

    @FXML
    private TextField bookingIdTxt;

    @FXML
    private TableView<?> bookingInfoTable;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> classColumn;

    @FXML
    private ComboBox<String> classComboTxt;

    @FXML
    private TextField classSeatstxt;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> dateColumn1;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private ComboBox<String> destinationComboTxt;

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
    private ComboBox<String> passengerIdComboTxt;

    @FXML
    private TextField passengerNameTxt;

    @FXML
    private Button paymentBtn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private Button resetBtn;

    @FXML
    private ComboBox<String> scheduleComboTxt;

    @FXML
    private TableColumn<?, ?> scheduleIdColumn;

    @FXML
    private TextField stationIdTxt;

    @FXML
    private TextField stationTxt;

    @FXML
    private TextField ticketPriceTxt;

    @FXML
    private ComboBox<String> trainTxt;

    String selectedStationId;
    String selectedDes;

    private final PassengerModel passengerModel = new PassengerModel();
    private final ScheduleModel scheduleModel = new ScheduleModel();
    private final ClassModel classModel =  new ClassModel();
    private final DestinationModel destinationModel = new DestinationModel();
    private final TrainModel trainModel = new TrainModel();
    private final BookingModel bookingModel = new BookingModel();

    @FXML
    void btnResetOnAction(ActionEvent event) {
        System.out.println("reset button clicked..");

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        System.out.println("update button clicked..");

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void trainTxtOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedValue = trainTxt.getSelectionModel().getSelectedItem();

        classModel.test(selectedValue);
        ArrayList<String> classIds = classModel.getAllClassIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();

        observableList.addAll(classIds);
        classComboTxt.setItems(observableList);
    }

    @FXML
    void classComboTxtOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        selectedDes = classComboTxt.getSelectionModel().getSelectedItem();
        ClassDto classDto = classModel.findById(selectedDes);

        if (classDto != null) {
            classSeatstxt.setText(String.valueOf(classDto.getSeats()));
            System.out.println(classSeatstxt.getText());
        }
    }


    @FXML
    void destinationComboTxtOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        selectedStationId = destinationComboTxt.getSelectionModel().getSelectedItem();
        EndStationDto endStationDto = destinationModel.findById(selectedStationId);

        selectedDes = classComboTxt.getSelectionModel().getSelectedItem();
        System.out.println(selectedDes);

        double price = destinationModel.getPrice(selectedDes, selectedStationId);
        System.out.println("double"+price);

        ticketPriceTxt.setText(String.valueOf(price));

        if (endStationDto != null) {
            stationIdTxt.setText(endStationDto.getStationId());
        }
    }

    @FXML
    void passengerIdComboTxtOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedPsId = passengerIdComboTxt.getSelectionModel().getSelectedItem();
        PassengerDto passengerDto = passengerModel.findById(selectedPsId);

        if (passengerDto != null) {
            passengerNameTxt.setText(passengerDto.getPassengerName());
        }

    }

    @FXML
    void scheduleComboTxtOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedScheduleId = scheduleComboTxt.getSelectionModel().getSelectedItem();
        ScheduleDto scheduleDto = scheduleModel.findById(selectedScheduleId);

        if (scheduleDto != null) {
            stationTxt.setText(scheduleDto.getDepartureSation());
            durationTxt.setText(scheduleDto.getDuration());
        }
    }

    @FXML
    void placeBtnAction(ActionEvent event) throws IOException {
        System.out.println("place order button clicked..");

        Parent load = FXMLLoader.load(getClass().getResource("/View/Payment.fxml"));
        Scene scene = new Scene(load);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadPassengerId();
            loadScheduleId();
            loadClassId();
            loadDestination();
            loadTrainNames();
            configureDatePicker();

            String getNextBookingId = bookingModel.getNextBookingId();
            bookingIdTxt.setText(getNextBookingId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public void loadPassengerId() throws SQLException, ClassNotFoundException {
        ArrayList<String> passengerIds = passengerModel.getAllPassengerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(passengerIds);
        passengerIdComboTxt.setItems(observableList);
    }

    public void loadScheduleId() throws SQLException, ClassNotFoundException {
        ArrayList<String> scheduleIds = scheduleModel.getAllScheduleIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(scheduleIds);
        scheduleComboTxt.setItems(observableList);
    }

    public void loadClassId() throws SQLException, ClassNotFoundException {
        ArrayList<String> classIds = classModel.getAllClassIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(classIds);
        classComboTxt.setItems(observableList);
    }

    public void loadDestination() throws SQLException, ClassNotFoundException {
        ArrayList<String> destinations = destinationModel.getAllStations();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(destinations);
        destinationComboTxt.setItems(observableList);
    }

    public void loadTrainNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> trainNames = trainModel.getAllTrainNames();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(trainNames);
        trainTxt.setItems(observableList);

        ObservableList<String> savedTrainNames = trainTxt.getItems();

        System.out.println(savedTrainNames);
    }

}
