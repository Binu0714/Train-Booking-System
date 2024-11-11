package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.gdse.train_booking_project.Dto.FeedbackDto;
import lk.ijse.gdse.train_booking_project.Model.FeedbackModel;

import java.sql.SQLException;

public class FeedbackController {

    @FXML
    private Button addFeedbackBtn;

    @FXML
    private TextField commentTxt;

    @FXML
    private TextField feedbackIdTxt;

    @FXML
    private TextField passengerIdText;

    @FXML
    private TextField ratingTxt;

    FeedbackModel feedbackModel = new FeedbackModel();

    @FXML
    void AddFeedbackBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("add feedback button clicked");

        String feedbackId = feedbackIdTxt.getText();
        String passengerId = passengerIdText.getText();
        int rating = Integer.parseInt(ratingTxt.getText());
        String comment = commentTxt.getText();

        FeedbackDto feedbackDto = new FeedbackDto(
                feedbackId,
                passengerId,
                rating,
                comment
        );

        boolean isSaved = feedbackModel.saveFeedback(feedbackDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Delay notice added successfully..").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to add Delay notice...").show();
        }

    }

}
