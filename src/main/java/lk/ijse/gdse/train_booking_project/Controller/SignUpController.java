package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.gdse.train_booking_project.Dto.SignUpDto;
import lk.ijse.gdse.train_booking_project.Model.SignUpModel;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private Hyperlink LoginHyperLink;

    @FXML
    private AnchorPane ancSignUp;

    @FXML
    private Button SignUpButton;

    @FXML
    private Text TrainMate;

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private Label email;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField idTxt;

    @FXML
    private ImageView image1;

    @FXML
    private Label password;

    @FXML
    private PasswordField pwrdTxt;

    @FXML
    private Text signup;

    @FXML
    private Label text1;

    @FXML
    private Label username;

    @FXML
    private TextField usernameTxt;

    SignUpModel signUpModel = new SignUpModel();

    @FXML
    void LoginMouseClickAction(MouseEvent event) throws IOException {
        ancSignUp.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        ancSignUp.getChildren().add(load);

        load.prefWidthProperty().bind(ancSignUp.widthProperty());
        load.prefHeightProperty().bind(ancSignUp.heightProperty());

        AnchorPane.setTopAnchor(load, 0.0);
        AnchorPane.setRightAnchor(load, 0.0);
        AnchorPane.setBottomAnchor(load, 0.0);
        AnchorPane.setLeftAnchor(load, 0.0);
    }

    @FXML
    void SignUpOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("register button clicked");

        String id = idTxt.getText();
        String username = usernameTxt.getText();
        String email = emailTxt.getText();
        String password = pwrdTxt.getText();


        SignUpDto signUpDto = new SignUpDto(
                id,
                username,
                email,
                password

        );

        boolean isSaved = signUpModel.saveUser(signUpDto);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Registration Complete.Please login...!").show();

            ancSignUp.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            ancSignUp.getChildren().add(load);

        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to register...!").show();
        }

    }

}
