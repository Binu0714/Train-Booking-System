package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TrainManageLayoutController {

    @FXML
    private AnchorPane ancor1;

    @FXML
    private AnchorPane ancorLoad;

    @FXML
    private Button classManageBtn;

    @FXML
    private Button trainManageBtn;

    @FXML
    private VBox vbox1;

    @FXML
    void ClassManageOnAction(ActionEvent event) throws IOException {
        ancorLoad.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/ClassManage.fxml"));
        ancorLoad.getChildren().add(load);
    }

    @FXML
    void TrainManageAction(ActionEvent event) throws IOException {
        ancorLoad.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/TrainManage.fxml"));
        ancorLoad.getChildren().add(load);
    }

}
