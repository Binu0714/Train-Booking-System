package lk.ijse.gdse.train_booking_project.Model;

import javafx.scene.control.Alert;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public boolean checkCredentials(String enteredUsername, String enteredPassword) throws SQLException, ClassNotFoundException {
        try {
            ResultSet result = CrudUtil.execute("SELECT*FROM admin WHERE username=? AND password=?",enteredUsername,enteredPassword);

            if (result.next()) {
                return true;
            }else{
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password..!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean authenticateUsername(String enteredUsername) throws SQLException, ClassNotFoundException {
        try{
            ResultSet result = CrudUtil.execute("SELECT * FROM admin WHERE username = ?", enteredUsername);


            if (result.next()) {
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Authentication failed
    }

    public boolean authenticatePsw(String enteredPassword) throws SQLException, ClassNotFoundException {
        try{
            ResultSet result = CrudUtil.execute("SELECT * FROM admin WHERE password = ?", enteredPassword);


            if (result.next()) {
                return true;  // Authentication successful
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Authentication failed
    }

}
