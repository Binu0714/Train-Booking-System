package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeController {

    @FXML
    private AnchorPane DashBoardAnc;

    @FXML
    private AnchorPane LoadingPane;

    @FXML
    private Button bookingBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button customerSupportBtn;

    @FXML
    private Button passengerBtn;

    @FXML
    private Button scheduleBtn;

    @FXML
    private Button trainBtn;

    @FXML
    void BookingManageOnAction(ActionEvent event) throws IOException {
        System.out.println("booking manage clicked..");

        LoadingPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/BookingManage.fxml"));
        LoadingPane.getChildren().add(load);
    }

    @FXML
    void LogoutOnAction(ActionEvent event) throws IOException {
        System.out.println("logout clicked..");

        LoadingPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        LoadingPane.getChildren().add(load);
    }

    @FXML
    void ScheduleManageOnAction(ActionEvent event) throws IOException {
        System.out.println("schedule manage clicked..");

        LoadingPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/ScheduleManage.fxml"));
        LoadingPane.getChildren().add(load);
    }

    @FXML
    void TrainManageOnAction(ActionEvent event) throws IOException {
        System.out.println("train manage clicked..");

        LoadingPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/TrainManageLayout.fxml"));
        LoadingPane.getChildren().add(load);
    }

    @FXML
    void passengerManageOnAction(ActionEvent event) throws IOException {
        System.out.println("passenger manage clicked..");

        LoadingPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/PassengerManage.fxml"));
        LoadingPane.getChildren().add(load);
    }

    @FXML
    void customerSupportOnAction(ActionEvent event) throws IOException {
        System.out.println("customer support manage clicked..");

        LoadingPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/CustomerSupport.fxml"));
        LoadingPane.getChildren().add(load);

    }

}
