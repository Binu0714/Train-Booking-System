package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ClassDto {
    private String classId;
    private String trainId;
    private String classNo;
    private String description;
    private int seats;

}
