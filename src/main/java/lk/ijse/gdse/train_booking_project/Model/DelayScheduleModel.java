package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.DelayScheduleDto;
import lk.ijse.gdse.train_booking_project.Dto.ScheduleDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

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
}
