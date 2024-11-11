package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BookingDto {
    private String bookingId;
    private int noOfSeats;
    private String bookingClass;
    private Date bookingDate;
    private String stationId;
    private String passengerId;
    private String scheduleId;
}
