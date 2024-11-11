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
import lk.ijse.gdse.train_booking_project.Dto.PassengerDto;
import lk.ijse.gdse.train_booking_project.Dto.TM.PassengerTM;
import lk.ijse.gdse.train_booking_project.Model.PassengerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PassengerManageController implements Initializable {

    @FXML
    private TableColumn<PassengerTM, String> adminIdColumn;

    @FXML
    private TextField adminIdTxt;

    @FXML
    private TableColumn<PassengerTM, String> contactColumn;

    @FXML
    private TextField contactTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<PassengerTM, String> emailColumn;

    @FXML
    private TextField emailTxt;

    @FXML
    private Button feedbackBtn;

    @FXML
    private TableColumn<PassengerTM, String> nameColumn;

    @FXML
    private TextField nameTxt;

    @FXML
    private TableColumn<PassengerTM, String> psIdColumn;

    @FXML
    private TextField psIdTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<PassengerTM> sheduleInfoTable;

    @FXML
    private Button updateBtn;

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("delete button clicked..");

        String passengerId = psIdTxt.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = passengerModel.deletePassenger(passengerId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Passenger deleted successfully...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete passenger...!").show();
            }
        }

    }

    @FXML
    void FeedbackBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("feedback button clicked..");

        Parent load = FXMLLoader.load(getClass().getResource("/View/Feedback.fxml"));
        Scene scene = new Scene(load);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("reset button clicked..");

        refreshPage();

    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("save button clicked..");

        String passengerId = psIdTxt.getText();
        String aid = adminIdTxt.getText();
        String passengerName = nameTxt.getText();
        String email = emailTxt.getText();
        int contactNo = Integer.parseInt(contactTxt.getText());

        PassengerDto passengerDto = new PassengerDto(
                passengerId,
                aid,
                passengerName,
                email,
                contactNo
        );

        boolean isSaved = passengerModel.savePassenger(passengerDto);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("update button clicked..");

        String passengerId = psIdTxt.getText();
        String aid = adminIdTxt.getText();
        String passengerName = nameTxt.getText();
        String email = emailTxt.getText();
        int contactNo = Integer.parseInt(contactTxt.getText());

        PassengerDto passengerDto = new PassengerDto(
                passengerId,
                aid,
                passengerName,
                email,
                contactNo
        );

        boolean isUpdate = passengerModel.updatePassenger(passengerDto);

        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Train updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Train...!").show();
        }

    }

    @FXML
    void onClickTable(MouseEvent event) {
        PassengerTM passengerTM = sheduleInfoTable.getSelectionModel().getSelectedItem();

        if (passengerTM != null) {
            psIdTxt.setText(passengerTM.getPassengerId());
            adminIdTxt.setText(passengerTM.getAid());
            nameTxt.setText(passengerTM.getPassengerName());
            emailTxt.setText(passengerTM.getEmail());
            contactTxt.setText(String.valueOf(passengerTM.getContactNo()));

            saveBtn.setDisable(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        psIdColumn.setCellValueFactory(new PropertyValueFactory<>("passengerId"));
        adminIdColumn.setCellValueFactory(new PropertyValueFactory<>("aid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("passengerName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        try{
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }

    }

    PassengerModel passengerModel = new PassengerModel();

    public void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<PassengerDto> passengerDtos = passengerModel.getAllPassenger();

        ObservableList<PassengerTM> passengerTMS = FXCollections.observableArrayList();

        for (PassengerDto passengerDto : passengerDtos) {
            PassengerTM passengerTM = new PassengerTM(
                    passengerDto.getPassengerId(),
                    passengerDto.getAid(),
                    passengerDto.getPassengerName(),
                    passengerDto.getEmail(),
                    passengerDto.getContactNo()
            );
            passengerTMS.add(passengerTM);
        }
        sheduleInfoTable.setItems(passengerTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        //saveBtn.setDisable(false);
        //updateBtn.setDisable(true);
        //deleteBtn.setDisable(true);

        psIdTxt.setText("");
        adminIdTxt.setText("");
        nameTxt.setText("");
        emailTxt.setText("");
        contactTxt.setText("");

    }



}
