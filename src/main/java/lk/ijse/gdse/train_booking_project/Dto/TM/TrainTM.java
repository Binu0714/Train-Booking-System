package lk.ijse.gdse.train_booking_project.Dto.TM;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TrainTM {
    private String adminId;
    private String trainId;
    private String name;
    private int capacity;
}
