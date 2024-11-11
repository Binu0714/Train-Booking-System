package lk.ijse.gdse.train_booking_project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.gdse.train_booking_project.Dto.ScheduleDto;
import lk.ijse.gdse.train_booking_project.Dto.TM.ScheduleTM;
import lk.ijse.gdse.train_booking_project.Model.ScheduleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ScheduleManageController implements Initializable {

    @FXML
    private TableColumn<ScheduleTM, Time> arrivalTimeColumn;

    @FXML
    private TextField arrivalTimeTxt;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button delayBtn;

    @FXML
    private TableColumn<ScheduleTM, Date> dateColumn;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private TableColumn<ScheduleTM, Time> depTimeColumn;

    @FXML
    private TextField depTimeTxt;

    @FXML
    private TableColumn<ScheduleTM, String> durationColumn;

    @FXML
    private TextField durationTxt;

    @FXML
    private TableColumn<ScheduleTM, String> endStationColumn;

    @FXML
    private TextField endStationTxt;

    @FXML
    private TableColumn<ScheduleTM, String> sheduleIdColumn;

    @FXML
    private TextField sheduleIdTxt;

    @FXML
    private TableView<ScheduleTM> sheduleInfoTable;

    @FXML
    private TableColumn<ScheduleTM, String> stStationColumn;

    @FXML
    private TextField stStationTxt;

    @FXML
    private TableColumn<ScheduleTM, String> trainIdColumn;

    @FXML
    private TextField trainIdTxt;


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("delete button clicked");

        String Schedule_id = sheduleIdTxt.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = scheduleModel.deleteSchedule(Schedule_id);

            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Schedule deleted successfully...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Schedule...!").show();
            }
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("save button clicked..");

        String sheduleId = sheduleIdTxt.getText();
        String trainId = trainIdTxt.getText();
        Time arrivalTime = Time.valueOf(arrivalTimeTxt.getText());
        Time departureTime = Time.valueOf(depTimeTxt.getText());
        String arrivalSation = stStationTxt.getText();
        String departureSation = endStationTxt.getText();
        String duration = durationTxt.getText();
        Date date = Date.valueOf(dateTxt.getValue());

        ScheduleDto scheduleDto = new ScheduleDto(
                sheduleId,
                trainId,
                arrivalTime,
                departureTime,
                arrivalSation,
                departureSation,
                duration,
                date
        );

        boolean isSaved = scheduleModel.saveSchedule(scheduleDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("update button clicked");

        String sheduleId = sheduleIdTxt.getText();
        String trainId = trainIdTxt.getText();
        Time arrivalTime = Time.valueOf(arrivalTimeTxt.getText());
        Time departureTime = Time.valueOf(depTimeTxt.getText());
        String arrivalSation = stStationTxt.getText();
        String departureSation = endStationTxt.getText();
        String duration = durationTxt.getText();
        Date date = Date.valueOf(dateTxt.getValue());

        ScheduleDto scheduleDto = new ScheduleDto(
                sheduleId,
                trainId,
                arrivalTime,
                departureTime,
                arrivalSation,
                departureSation,
                duration,
                date
        );

        boolean isUpdate = scheduleModel.updateSchedule(scheduleDto);

        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Train updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Train...!").show();
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("reset button clicked");

        refreshPage();
    }

    @FXML
    void DelayBtnOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/View/DelayScheduleManage.fxml"));
        Scene scene = new Scene(load);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sheduleIdColumn.setCellValueFactory(new PropertyValueFactory<>("sheduleId"));
        trainIdColumn.setCellValueFactory(new PropertyValueFactory<>("trainId"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        depTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        stStationColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalSation"));
        endStationColumn.setCellValueFactory(new PropertyValueFactory<>("departureSation"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        try{
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }

    ScheduleModel scheduleModel = new ScheduleModel();

    public void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<ScheduleDto> scheduleDtos = scheduleModel.getAllschedules();

        ObservableList<ScheduleTM> scheduleTMS = FXCollections.observableArrayList();

        for (ScheduleDto scheduleDto : scheduleDtos) {
            ScheduleTM scheduleTM = new ScheduleTM(
                    scheduleDto.getSheduleId(),
                    scheduleDto.getTrainId(),
                    scheduleDto.getArrivalTime(),
                    scheduleDto.getDepartureTime(),
                    scheduleDto.getArrivalSation(),
                    scheduleDto.getDepartureSation(),
                    scheduleDto.getDuration(),
                    scheduleDto.getDate()
            );
            scheduleTMS.add(scheduleTM);
        }
        sheduleInfoTable.setItems(scheduleTMS);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        ScheduleTM scheduleTM = sheduleInfoTable.getSelectionModel().getSelectedItem();
        if (scheduleTM != null) {
            sheduleIdTxt.setText(scheduleTM.getSheduleId());
            trainIdTxt.setText(scheduleTM.getTrainId());
            arrivalTimeTxt.setText(String.valueOf(scheduleTM.getArrivalTime()));
            depTimeTxt.setText(String.valueOf(scheduleTM.getDepartureTime()));
            stStationTxt.setText(scheduleTM.getArrivalSation());
            endStationTxt.setText(scheduleTM.getDepartureSation());
            durationTxt.setText(scheduleTM.getDuration());
            dateTxt.setValue(scheduleTM.getDate().toLocalDate());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        sheduleIdTxt.setText("");
        trainIdTxt.setText("");
        arrivalTimeTxt.setText("");
        depTimeTxt.setText("");
        stStationTxt.setText("");
        endStationTxt.setText("");
        durationTxt.setText("");
        dateTxt.setValue(null);
    }

}
