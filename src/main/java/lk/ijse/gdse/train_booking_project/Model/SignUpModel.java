package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.SignUpDto;
import lk.ijse.gdse.train_booking_project.Dto.TrainDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.SQLException;

public class SignUpModel {
    public boolean saveUser(SignUpDto signUpDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into admin values (?,?,?,?)",
                signUpDto.getId(),
                signUpDto.getUsername(),
                signUpDto.getEmail(),
                signUpDto.getPassword()

        );
    }
}
