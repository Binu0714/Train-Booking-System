package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.TrainDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class TrainModel {
    public ArrayList<TrainDto> getAllTrains() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from train");

        ArrayList<TrainDto> trainDtos = new ArrayList<>();

        while (rst.next()) {
            TrainDto trainDto = new TrainDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
            trainDtos.add(trainDto);
        }
        return trainDtos;
    }

    public boolean saveTrain(TrainDto trainDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into train values (?,?,?,?)",
                trainDto.getTrainId(),
                trainDto.getAdminId(),
                trainDto.getName(),
                trainDto.getCapacity()
        );
    }

    public boolean updateTrain(TrainDto trainDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update train set T_id=?, A_id=?, name=?, capacity=? where T_id=?",
                trainDto.getTrainId(),  // SET clause - T_id
                trainDto.getAdminId(),  // SET clause - A_id
                trainDto.getName(),     // SET clause - name
                trainDto.getCapacity(), // SET clause - capacity
                trainDto.getTrainId()   // WHERE clause - T_id (fifth parameter)
        );
    }


    public boolean deleteTrain(String trainId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from train where T_id=?", trainId);
    }

}
