package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.FeedbackDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.SQLException;

public class FeedbackModel {
    public boolean saveFeedback(FeedbackDto feedbackDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into feedback values (?,?,?,?)",
                feedbackDto.getFeedbackId(),
                feedbackDto.getPsId(),
                feedbackDto.getComment(),
                feedbackDto.getRating()
        );
    }
}
