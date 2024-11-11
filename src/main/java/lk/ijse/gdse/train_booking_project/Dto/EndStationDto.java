package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class EndStationDto {
    private String stationId;
    private String stationName;
    private double firstClass;
    private double secondClass;
    private double thirdClass;
}
