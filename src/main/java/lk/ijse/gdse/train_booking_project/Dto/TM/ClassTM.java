package lk.ijse.gdse.train_booking_project.Dto.TM;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ClassTM {
    private String classId;
    private String trainId;
    private String classNo;
    private String description;
    private int seats;
}
