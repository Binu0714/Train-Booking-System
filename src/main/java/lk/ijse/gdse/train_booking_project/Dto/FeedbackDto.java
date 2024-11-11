package lk.ijse.gdse.train_booking_project.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class FeedbackDto {
    private String feedbackId;
    private String psId;
    private int rating;
    private String comment;
}
