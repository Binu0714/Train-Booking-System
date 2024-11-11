package lk.ijse.gdse.train_booking_project.Dto.TM;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EndStationTM {
    private String stationId;
    private String stationName;
    private double firstClass;
    private double secondClass;
    private double thirdClass;
}
