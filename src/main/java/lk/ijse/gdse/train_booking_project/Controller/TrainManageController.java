package lk.ijse.gdse.train_booking_project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.train_booking_project.Dto.TM.TrainTM;
import lk.ijse.gdse.train_booking_project.Dto.TrainDto;
import lk.ijse.gdse.train_booking_project.Model.TrainModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TrainManageController implements Initializable {

    @FXML
    private TableColumn<TrainTM, String> ColumnAdminId;

    @FXML
    private TableColumn<TrainTM, String> ColumnCapacity;

    @FXML
    private TableColumn<TrainTM, String> ColumnName;

    @FXML
    private TableColumn<TrainTM, String> ColumnTrainId;

    @FXML
    private TextField adminIdTxt;

    @FXML
    private TextField capacityTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button reportBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField trainIdTxt;

    @FXML
    private TableView<TrainTM> trainInfoTable;

    @FXML
    private Button updateBtn;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("delete button clicked");

        String trainId = trainIdTxt.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = trainModel.deleteTrain(trainId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Train deleted successfully...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete train...!").show();
            }
        }
    }

    @FXML
    void reportOnAction(ActionEvent event) {
        System.out.println("report button clicked");
    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("save button clicked");

        String adminId = adminIdTxt.getText();
        String trainId = trainIdTxt.getText();
        String name = nameTxt.getText();
        int capacity = Integer.parseInt(capacityTxt.getText());

        TrainDto trainDto = new TrainDto(
                adminId,
                trainId,
                name,
                capacity
        );

        boolean isSaved = trainModel.saveTrain(trainDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("update button clicked");

        String adminId = adminIdTxt.getText();
        String trainId = trainIdTxt.getText();
        String name = nameTxt.getText();
        int capacity = Integer.parseInt(capacityTxt.getText());

        TrainDto trainDto = new TrainDto(
                adminId,
                trainId,
                name,
                capacity
        );

        boolean isUpdate = trainModel.updateTrain(trainDto);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColumnTrainId.setCellValueFactory(new PropertyValueFactory<>("trainId"));
        ColumnAdminId.setCellValueFactory(new PropertyValueFactory<>("adminId"));
        ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        try{
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }

    TrainModel trainModel = new TrainModel();

    public void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<TrainDto> trainDtos = trainModel.getAllTrains();

        ObservableList<TrainTM> trainTMS = FXCollections.observableArrayList();

        for (TrainDto trainDto : trainDtos) {
            TrainTM trainTM = new TrainTM(
                    trainDto.getTrainId(),
                    trainDto.getAdminId(),
                    trainDto.getName(),
                    trainDto.getCapacity()
            );
            trainTMS.add(trainTM);
        }
        trainInfoTable.setItems(trainTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        //saveBtn.setDisable(false);
        //updateBtn.setDisable(true);
        //deleteBtn.setDisable(true);

        adminIdTxt.setText("");
        trainIdTxt.setText("");
        nameTxt.setText("");
        capacityTxt.setText("");
    }

    @FXML
    void onClickTable(MouseEvent event) {
        TrainTM trainTM = trainInfoTable.getSelectionModel().getSelectedItem();
        if (trainTM != null) {
            trainIdTxt.setText(trainTM.getTrainId());
            adminIdTxt.setText(trainTM.getAdminId());
            nameTxt.setText(trainTM.getName());
            capacityTxt.setText(String.valueOf(trainTM.getCapacity()));

            //saveBtn.setDisable(true);
            //deleteBtn.setDisable(false);
            //updateBtn.setDisable(false);

        }
    }



}
