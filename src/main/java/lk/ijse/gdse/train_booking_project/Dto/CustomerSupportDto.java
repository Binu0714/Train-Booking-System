package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CustomerSupportDto {
    private String csId;
    private String adminId;
    private Date date;
    private String issue;
    private String passengerId;
}
