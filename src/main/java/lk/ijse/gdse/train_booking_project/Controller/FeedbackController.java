package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.gdse.train_booking_project.Dto.FeedbackDto;
import lk.ijse.gdse.train_booking_project.Model.FeedbackModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FeedbackController implements Initializable {

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
            new Alert(Alert.AlertType.INFORMATION, "Customer Feedback added successfully..").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to add Customer Feedback...").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{

            String getNextFeedbackId = feedbackModel.getNextFeedbackId();
            feedbackIdTxt.setText(getNextFeedbackId);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load feedback id").show();
        }

    }
}
