package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ClassLoadDto {
    private String trainId;
    private String classId;
    private String classNo;
    private String description;
    private String configId;
    private int noOfSeats;
}
