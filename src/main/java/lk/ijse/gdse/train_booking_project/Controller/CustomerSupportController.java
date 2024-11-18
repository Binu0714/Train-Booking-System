package lk.ijse.gdse.train_booking_project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.train_booking_project.Dto.CustomerSupportDto;
import lk.ijse.gdse.train_booking_project.Dto.PassengerDto;
import lk.ijse.gdse.train_booking_project.Dto.TM.CustomerSupportTM;
import lk.ijse.gdse.train_booking_project.Model.CustomerSupportModel;
import lk.ijse.gdse.train_booking_project.Model.PassengerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerSupportController implements Initializable {

    @FXML
    private TableColumn<CustomerSupportTM, String> adminIdColumn;

    @FXML
    private TextField adminIdTxt;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<CustomerSupportTM, String> csIdColumn;

    @FXML
    private TableView<CustomerSupportTM> csInfoTable;

    @FXML
    private TableColumn<CustomerSupportTM, Date> dateColumn;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private TableColumn<CustomerSupportTM, String> issueColumn;

    @FXML
    private TextField issueTxt;

    @FXML
    private TableColumn<CustomerSupportTM, String> psIdColumn;

    @FXML
    private ComboBox<String> psIdMenuTxt;

    @FXML
    private TextField scIdTxt;

    @FXML
    private TextField psNameTxt;

    private final PassengerModel passengerModel = new PassengerModel();

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("save button clicked..");

        String csId = scIdTxt.getText();
        String adminId = adminIdTxt.getText();
        java.sql.Date date = java.sql.Date.valueOf(dateTxt.getValue());
        String issue = issueTxt.getText();
        String passengerId = psIdMenuTxt.getValue();

        CustomerSupportDto customerSupportDto = new CustomerSupportDto(
                csId,
                adminId,
                date,
                issue,
                passengerId
        );

        boolean isSaved = customerSupportModel.saveCS(customerSupportDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Passenger issue saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save passenger issue...!").show();
        }

    }

    @FXML
    void onClickTable(MouseEvent event) {
        CustomerSupportTM customerSupportTM = csInfoTable.getSelectionModel().getSelectedItem();
        if (customerSupportTM != null) {
            scIdTxt.setText(customerSupportTM.getCsId());
            adminIdTxt.setText(customerSupportTM.getAdminId());
            dateTxt.setValue(customerSupportTM.getDate().toLocalDate());
            psIdMenuTxt.setValue(customerSupportTM.getPassengerId());
            issueTxt.setText(customerSupportTM.getIssue());

            //saveBtn.setDisable(true);
            //deleteBtn.setDisable(false);
            //updateBtn.setDisable(false);
        }

    }

    @FXML
    void reportOnAction(ActionEvent event) {
        System.out.println("report button clicked..");

    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("reset button clicked..");

        refreshPage();
    }

    @FXML
    void psIdMenuTxtOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
       String selectedPsId = psIdMenuTxt.getSelectionModel().getSelectedItem();
        PassengerDto passengerDto = passengerModel.findById(selectedPsId);

        if (passengerDto != null) {
            psNameTxt.setText(passengerDto.getPassengerName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        csIdColumn.setCellValueFactory(new PropertyValueFactory<>("csId"));
        adminIdColumn.setCellValueFactory(new PropertyValueFactory<>("adminId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        issueColumn.setCellValueFactory(new PropertyValueFactory<>("issue"));
        psIdColumn.setCellValueFactory(new PropertyValueFactory<>("passengerId"));

        try{
            loadTableData();
            loadPassengerId();
            configureDatePicker();

            String getNextCsId = customerSupportModel.getNextCsId();
            scIdTxt.setText(getNextCsId);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load..").show();
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

    CustomerSupportModel customerSupportModel = new CustomerSupportModel();

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerSupportDto> customerSupportDtos = customerSupportModel.getAll();

        ObservableList<CustomerSupportTM> customerSupportTMS = FXCollections.observableArrayList();

        for (CustomerSupportDto customerSupportDto : customerSupportDtos) {
            CustomerSupportTM customerSupportTM = new CustomerSupportTM(
                    customerSupportDto.getCsId(),
                    customerSupportDto.getAdminId(),
                    customerSupportDto.getDate(),
                    customerSupportDto.getIssue(),
                    customerSupportDto.getPassengerId()
            );
            customerSupportTMS.add(customerSupportTM);
        }
        csInfoTable.setItems(customerSupportTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();
        loadPassengerId();

        //saveBtn.setDisable(false);
        //updateBtn.setDisable(true);
        //deleteBtn.setDisable(true);

        scIdTxt.setText("");
        adminIdTxt.setText("");
        dateTxt.setValue(null);
        psIdMenuTxt.setValue(null);
        issueTxt.setText("");
    }

    public void loadPassengerId() throws SQLException, ClassNotFoundException {
        ArrayList<String> passengerIds = passengerModel.getAllPassengerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(passengerIds);
        psIdMenuTxt.setItems(observableList);
    }


}
