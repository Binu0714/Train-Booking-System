package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.gdse.train_booking_project.Model.LoginModel;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane DashBoardAnc;

    @FXML
    private AnchorPane LoadPane;

    @FXML
    private Button bookingBtn;

    @FXML
    private Button cancellationBtn;

    @FXML
    private Button classBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button passengerBtn;

    @FXML
    private Button scheduleBtn;

    @FXML
    private Button supportBtn;

    @FXML
    private Button trainBtn;

    @FXML
    private Label usernameLoad;

    @FXML
    private VBox vbox1;

    @FXML
    void bookingBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("booking manage button clicked...");

        LoadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/BookingManage.fxml"));
        LoadPane.getChildren().add(load);

    }

    @FXML
    void cancellationBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("booking cancellation button clicked...");

        LoadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/BookingCancellation.fxml"));
        LoadPane.getChildren().add(load);

    }

    @FXML
    void classBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("class manage button clicked...");

        LoadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/ClassManage.fxml"));
        LoadPane.getChildren().add(load);

    }

    @FXML
    void dashboardBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("dashboard button clicked...");

        LoadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
        LoadPane.getChildren().add(load);

    }

    @FXML
    void logoutBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("logout button clicked...");

        DashBoardAnc.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        DashBoardAnc.getChildren().add(load);

    }

    @FXML
    void passengerBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("passenger manage button clicked...");

        LoadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/PassengerManage.fxml"));
        LoadPane.getChildren().add(load);

    }

    @FXML
    void scheduleBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("schedule manage button clicked...");

        LoadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/ScheduleManage.fxml"));
        LoadPane.getChildren().add(load);

    }

    @FXML
    void supportBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("customer support button clicked...");

        LoadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/CustomerSupport.fxml"));
        LoadPane.getChildren().add(load);

    }

    @FXML
    void trainBtnOnAction(ActionEvent event) throws IOException {
        System.out.println("train manage button clicked...");

        LoadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/TrainManage.fxml"));
        LoadPane.getChildren().add(load);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            LoadPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            LoadPane.getChildren().add(load);

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load the Dashboard");
        }
    }


}
