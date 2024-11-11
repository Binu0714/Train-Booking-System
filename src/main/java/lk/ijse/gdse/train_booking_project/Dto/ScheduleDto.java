package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ScheduleDto {
    private String sheduleId;
    private String trainId;
    private Time arrivalTime;
    private Time departureTime;
    private String arrivalSation;
    private String departureSation;
    private String duration;
    private Date date;
}
