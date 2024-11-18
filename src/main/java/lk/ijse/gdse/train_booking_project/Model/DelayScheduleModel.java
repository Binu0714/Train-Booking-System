package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.DelayScheduleDto;
import lk.ijse.gdse.train_booking_project.Dto.ScheduleDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DelayScheduleModel {
    public boolean saveDelayNote(DelayScheduleDto delayScheduleDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into train_delay values (?,?,?,?,?)",
                delayScheduleDto.getDelayId(),
                delayScheduleDto.getScheduleId(),
                delayScheduleDto.getDelayTime(),
                delayScheduleDto.getReason(),
                delayScheduleDto.getDate()
        );
    }

    public String getNextDelayId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select delay_id from train_delay order by delay_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1); // Last passenger ID
            String numericPart = lastId.substring(2); // Extract the numeric part after "PS"
            int nextIdNumber = Integer.parseInt(numericPart) + 1; // Increment by 1
            return String.format("TD%03d", nextIdNumber); // Format with leading zeros (e.g., PS0008)
        }
        return "TD001"; // Default ID if no data is found
    }
}
