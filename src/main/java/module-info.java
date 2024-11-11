module lk.ijse.gdse.train_booking_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.gdse.train_booking_project.Controller to javafx.fxml;
    opens lk.ijse.gdse.train_booking_project.Dto.TM to javafx.base;
    exports lk.ijse.gdse.train_booking_project;
}