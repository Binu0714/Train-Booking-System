package lk.ijse.gdse.train_booking_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.gdse.train_booking_project.Model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class LoginController {

    @FXML
    private Button LogInButton;

    @FXML
    private Hyperlink SignUpHiperLink;

    @FXML
    private Text TrainMate;

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private ImageView image;

    @FXML
    private Text loginText;

    @FXML
    private Label password;

    @FXML
    private PasswordField pwrdTxt;

    @FXML
    private Label text1;

    @FXML
    private Label username;

    @FXML
    private TextField usernameTxt;

    LoginModel loginModel = new LoginModel();

    @FXML
    void LoginAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("Login Clicked");

        String EnteredUsername = usernameTxt.getText();
        String EnteredPassword = pwrdTxt.getText();

        if (loginModel.checkCredentials(EnteredUsername, EnteredPassword)) {
            System.out.println("Login successful!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Home.fxml"));
            AnchorPane newPane = loader.load();

            AnchorPane.setTopAnchor(newPane, 0.0);
            AnchorPane.setRightAnchor(newPane, 0.0);
            AnchorPane.setBottomAnchor(newPane, 0.0);
            AnchorPane.setLeftAnchor(newPane, 0.0);

            ancLogin.getChildren().setAll(newPane);

        } else {

            if (loginModel.authenticateUsername(EnteredUsername) && !(loginModel.authenticatePsw(EnteredPassword))) {
                usernameTxt.setStyle("-fx-text-box-border: blue; ");
                pwrdTxt.setStyle("-fx-text-box-border: red; ");
            } else {
                pwrdTxt.setStyle("-fx-text-box-border: red; ");
                usernameTxt.setStyle("-fx-text-box-border: red; ");

                pwrdTxt.setText("");
                usernameTxt.setText("");

            }

        }
    }

    @FXML
    void SignUpMouseClickAction(MouseEvent event) throws IOException {
        System.out.println("Sign up clicked..");

        ancLogin.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/SignUp.fxml"));
        ancLogin.getChildren().add(load);

        load.prefWidthProperty().bind(ancLogin.widthProperty());
        load.prefHeightProperty().bind(ancLogin.heightProperty());

        AnchorPane.setTopAnchor(load, 0.0);
        AnchorPane.setRightAnchor(load, 0.0);
        AnchorPane.setBottomAnchor(load, 0.0);
        AnchorPane.setLeftAnchor(load, 0.0);

    }
}
