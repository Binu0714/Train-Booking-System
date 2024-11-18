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

    public ArrayList<String> getAllTrainIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select T_id from train");

        ArrayList<String> trainIds = new ArrayList<>();

        while (rst.next()) {
            trainIds.add(rst.getString(1));
        }
        return trainIds;
    }

    public ArrayList<String> getAllTrainNames() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select name from train");

        ArrayList<String> trainNames = new ArrayList<>();

        while (rst.next()) {
            trainNames.add(rst.getString(1));
        }
        return trainNames;
    }

    public TrainDto findById(String selectedTrainId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from train where T_id=?", selectedTrainId);

        if (rst.next()) {
            return new TrainDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    public String getNextTrainId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select T_id from train order by T_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1); // Last passenger ID
            String numericPart = lastId.substring(2); // Extract the numeric part after "PS"
            int nextIdNumber = Integer.parseInt(numericPart) + 1; // Increment by 1
            return String.format("T%03d", nextIdNumber); // Format with leading zeros (e.g., PS0008)
        }
        return "T001"; // Default ID if no data is found
    }

}
