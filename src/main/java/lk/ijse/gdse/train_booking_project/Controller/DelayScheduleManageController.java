package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.train_booking_project.Dto.DelayScheduleDto;
import lk.ijse.gdse.train_booking_project.Model.DelayScheduleModel;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class DelayScheduleManageController {

    @FXML
    private Button addNoteBtn;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private TextField delayIdTxt;

    @FXML
    private TextField delayTimeTxt;

    @FXML
    private AnchorPane popUpPane;

    @FXML
    private TextField reasonTxt;

    @FXML
    private TextField scheduleIdTxt;

    DelayScheduleModel delayScheduleModel = new DelayScheduleModel();

    @FXML
    void AddNoteBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("add delay note button clicked");

        String delayId = delayIdTxt.getText();
        String scheduleId = scheduleIdTxt.getText();
        Time delayTime = Time.valueOf(delayTimeTxt.getText());
        Date date = Date.valueOf(dateTxt.getValue());
        String reason = reasonTxt.getText();

        DelayScheduleDto dto = new DelayScheduleDto(
                delayId,
                scheduleId,
                delayTime,
                date,
                reason
        );

        boolean isSaved = delayScheduleModel.saveDelayNote(dto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Delay notice added successfully..").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to add Delay notice...").show();
        }
    }

}

