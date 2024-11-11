package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PassengerDto {
    private String passengerId;
    private String aid;
    private String passengerName;
    private String email;
    private int contactNo;
}
