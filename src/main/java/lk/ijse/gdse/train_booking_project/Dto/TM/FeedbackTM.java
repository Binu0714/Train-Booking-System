package lk.ijse.gdse.train_booking_project.Dto.TM;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class FeedbackTM {
    private String feedbackId;
    private String psId;
    private int rating;
    private String comment;
}
