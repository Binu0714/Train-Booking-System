package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lk.ijse.gdse.train_booking_project.Model.BookingModel;
import lk.ijse.gdse.train_booking_project.Model.LoginModel;
import lk.ijse.gdse.train_booking_project.Model.PassengerModel;
import lk.ijse.gdse.train_booking_project.Model.PaymentModel;

import java.sql.SQLException;

public class DashboardController {

    @FXML
    private Label PassengerCount;

    @FXML
    private Button PssReportBtn;

    @FXML
    private Label bookingCount;

    @FXML
    private Button bookingReportBtn;

    @FXML
    private Button csReportBtn;

    @FXML
    private BarChart<String, Number> dailyPssChart;

    @FXML
    private Button delayReportBtn;

    @FXML
    private Label revenueCount;

    @FXML
    private Button trainReportBtn;

    @FXML
    void PssReportBtnOnAction(ActionEvent event) {

    }

    @FXML
    void bookingReportBtnOnAction(ActionEvent event) {

    }

    @FXML
    void csReportBtnOnAction(ActionEvent event) {

    }

    @FXML
    void delayReportBtnOnAction(ActionEvent event) {

    }

    @FXML
    void trainReportBtnOnAction(ActionEvent event) {

    }

    public void initialize() {
        try {
            barchart();

            int totalPassengers = new PassengerModel().getTotalPassengers();
            PassengerCount.setText(String.valueOf(totalPassengers));

            int totalRevenue = (int) new PaymentModel().getTotalRevenue();
            revenueCount.setText(String.valueOf("Rs."+totalRevenue));

            int totalBookings = new BookingModel().getTotalBookings();
            bookingCount.setText(String.valueOf(totalBookings));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            PassengerCount.setText("Error fetching passenger count");
        }
    }

    void barchart(){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("ahh patiyoo...");

        series.getData().add(new XYChart.Data<>("1", 3));
        series.getData().add(new XYChart.Data<>("2", 90));
        series.getData().add(new XYChart.Data<>("3", 35));
        series.getData().add(new XYChart.Data<>("4", 88));
        series.getData().add(new XYChart.Data<>("5", 45));
        series.getData().add(new XYChart.Data<>("6", 50));
        series.getData().add(new XYChart.Data<>("7", 32));
        series.getData().add(new XYChart.Data<>("8", 45));
        series.getData().add(new XYChart.Data<>("9", 89));
        series.getData().add(new XYChart.Data<>("10", 4));
        series.getData().add(new XYChart.Data<>("11", 42));
        series.getData().add(new XYChart.Data<>("12", 21));

        dailyPssChart.getData().add(series);//meka wenas karanna one


    }


}
