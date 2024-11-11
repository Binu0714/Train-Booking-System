package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class DelayScheduleDto {
    private String delayId;
    private String scheduleId;
    private Time delayTime;
    private Date date;
    private String reason;
}
