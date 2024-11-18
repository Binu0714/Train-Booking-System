package lk.ijse.gdse.train_booking_project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.train_booking_project.Dto.*;
import lk.ijse.gdse.train_booking_project.Dto.TM.ClassTM;
import lk.ijse.gdse.train_booking_project.Dto.TM.TrainTM;
import lk.ijse.gdse.train_booking_project.Model.ClassModel;
import lk.ijse.gdse.train_booking_project.Model.TrainModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClassManageController implements Initializable {

    @FXML
    private TextField cinfigIdTxt;

    @FXML
    private Label classId;

    @FXML
    private TableColumn<?, ?> classIdColumn;

    @FXML
    private TextField classIdTxt;

    @FXML
    private TableView<ClassTM> classInfoTable;

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
    private ComboBox<String> trainIdMenu;

    @FXML
    private TextField trainNameLoadTxt;

    @FXML
    private Button updateBtn;

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("delete button clicked..");

        String classId = classIdTxt.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = classModel.deleteClass(classId);

            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Train deleted successfully...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete train...!").show();
            }
        }
    }

    @FXML
    void ResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("reset button clicked..");

        refreshPage();
    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("save button clicked..");

        String classId = classIdTxt.getText();
        String trainId = trainIdMenu.getValue();
        String classNo = classNoTxt.getText();
        String description = descriptionTxt.getText();
        int seats = Integer.parseInt(seatsTxt.getText());

        if(classId.isEmpty() || trainId.isEmpty() || classNo.isEmpty() || description.isEmpty() || seats == 0) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields...").show();
            return;
        }

        ClassDto classDto = new ClassDto(
                classId,
                trainId,
                classNo,
                description,
                seats
        );

        boolean isSaved = classModel.saveClass(classDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Train saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save train...!").show();
        }

    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("update button clicked..");

        String classId = classIdTxt.getText();
        String trainId = trainIdMenu.getValue();
        String classNo = classNoTxt.getText();
        String description = descriptionTxt.getText();
        int seats = Integer.parseInt(seatsTxt.getText());

        if(classId.isEmpty() || trainId.isEmpty() || classNo.isEmpty() || description.isEmpty() || seats == 0) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields...").show();
            return;
        }

        ClassDto classDto = new ClassDto(
                classId,
                trainId,
                classNo,
                description,
                seats
        );

        boolean isUpdate = classModel.updateClass(classDto);

        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Train updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Train...!").show();
        }

    }

    @FXML
    void trainIdMenuOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedTrainId = trainIdMenu.getSelectionModel().getSelectedItem();
        TrainDto trainDto = trainModel.findById(selectedTrainId);

        if (trainDto != null) {
            trainNameLoadTxt.setText(trainDto.getName());
        }

    }

    private final TrainModel trainModel= new TrainModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trainIdColumn.setCellValueFactory(new PropertyValueFactory<>("trainId"));
        classIdColumn.setCellValueFactory(new PropertyValueFactory<>("classId"));
        classNoColumn.setCellValueFactory(new PropertyValueFactory<>("classNo"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));

        try{
            loadTrainIds();
            loadTableData();

            String getNextClassId = classModel.getNextClassId();
            classIdTxt.setText(getNextClassId);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load ...").show();
        }
    }

    public void loadTrainIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> trainIds = trainModel.getAllTrainIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(trainIds);
        trainIdMenu.setItems(observableList);
    }

    ClassModel classModel = new ClassModel();

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ClassDto> classDtos = classModel.getAllClass();

        ObservableList<ClassTM> classTMS = FXCollections.observableArrayList();

        for (ClassDto classDto : classDtos) {
            ClassTM classTM = new ClassTM(
                    classDto.getClassId(),
                    classDto.getTrainId(),
                    classDto.getClassNo(),
                    classDto.getDescription(),
                    classDto.getSeats()
            );
            classTMS.add(classTM);
        }
        classInfoTable.setItems(classTMS);
    }

    @FXML
    void onClickACtion(MouseEvent event) {
        ClassTM classTM = classInfoTable.getSelectionModel().getSelectedItem();
        if (classTM != null) {
            trainIdMenu.setValue(classTM.getTrainId());
            classIdTxt.setText(classTM.getClassId());
            classNoTxt.setText(classTM.getClassNo());
            descriptionTxt.setText(classTM.getDescription());
            seatsTxt.setText(String.valueOf(classTM.getSeats()));
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        trainIdMenu.setValue(null);
        classIdTxt.setText("");
        classNoTxt.setText("");
        descriptionTxt.setText("");
        seatsTxt.setText("");
        trainNameLoadTxt.setText("");
    }

}
