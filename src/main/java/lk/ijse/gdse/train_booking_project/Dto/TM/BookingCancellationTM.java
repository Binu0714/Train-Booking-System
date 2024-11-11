package lk.ijse.gdse.train_booking_project.Dto.TM;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BookingCancellationTM {
    private String CancellationId;
    private String bookingId;
    private String passengerId;
    private String reason;
    private java.sql.Date Date;
}
