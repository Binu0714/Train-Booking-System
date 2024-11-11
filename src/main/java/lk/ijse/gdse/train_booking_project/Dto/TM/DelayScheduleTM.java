package lk.ijse.gdse.train_booking_project.Dto.TM;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class DelayScheduleTM {
    private String delayId;
    private String scheduleId;
    private Time delayTime;
    private Date date;
    private String reason;
}
