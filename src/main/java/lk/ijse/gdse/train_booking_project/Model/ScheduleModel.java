package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.ScheduleDto;
import lk.ijse.gdse.train_booking_project.Dto.TrainDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleModel {
    public ArrayList<ScheduleDto> getAllschedules() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from Schedule");

        ArrayList<ScheduleDto> scheduleDtos = new ArrayList<>();

        while (rst.next()) {
            ScheduleDto scheduleDto = new ScheduleDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getTime(3),
                    rst.getTime(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDate(8)
            );
            scheduleDtos.add(scheduleDto);
        }
        return scheduleDtos;
    }

    public boolean saveSchedule(ScheduleDto scheduleDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into schedule values (?,?,?,?,?,?,?,?)",
                scheduleDto.getSheduleId(),
                scheduleDto.getTrainId(),
                scheduleDto.getArrivalTime(),
                scheduleDto.getDepartureTime(),
                scheduleDto.getArrivalSation(),
                scheduleDto.getDepartureSation(),
                scheduleDto.getDuration(),
                scheduleDto.getDate()

        );
    }

    public boolean updateSchedule(ScheduleDto scheduleDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE schedule SET T_id=?, arrival_time=?, departure_time=?, arrival_station=?, departure_station=?, duration=?, date=? WHERE Schedule_id=?",
                scheduleDto.getTrainId(),
                scheduleDto.getArrivalTime(),
                scheduleDto.getDepartureTime(),
                scheduleDto.getArrivalSation(),
                scheduleDto.getDepartureSation(),
                scheduleDto.getDuration(),
                scheduleDto.getDate(),
                scheduleDto.getSheduleId()
        );
    }

    public boolean deleteSchedule(String scheduleId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM schedule WHERE Schedule_id=?", scheduleId);
    }


}
