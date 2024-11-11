package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BookinCancellationDto {
    private String CancellationId;
    private String bookingId;
    private String passengerId;
    private String reason;
    private Date Date;
}
