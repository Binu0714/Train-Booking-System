package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class TrainDto {
    private String adminId;
    private String trainId;
    private String name;
    private int capacity;

}
