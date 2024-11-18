package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.FeedbackDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
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

    public String getNextFeedbackId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select F_id from feedback order by F_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1); // Last passenger ID
            String numericPart = lastId.substring(2); // Extract the numeric part after "PS"
            int nextIdNumber = Integer.parseInt(numericPart) + 1; // Increment by 1
            return String.format("F%03d", nextIdNumber); // Format with leading zeros (e.g., PS0008)
        }
        return "F001"; // Default ID if no data is found
    }
}
