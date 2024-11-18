package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.PassengerDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengerModel {
    public ArrayList<PassengerDto> getAllPassenger() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from passenger");

        ArrayList<PassengerDto> passengerDtos = new ArrayList<>();

        while (rst.next()) {
            PassengerDto passengerDto = new PassengerDto(
                    rst.getString(1),
                    rst.getString(5),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
            passengerDtos.add(passengerDto);
        }
        return passengerDtos;
    }

    public boolean savePassenger(PassengerDto passengerDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into passenger values (?,?,?,?,?)",
                passengerDto.getPassengerId(),
                passengerDto.getPassengerName(),
                passengerDto.getEmail(),
                passengerDto.getContactNo(),
                passengerDto.getAid()
        );
    }

    public boolean updatePassenger(PassengerDto passengerDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE passenger SET PS_id=?, name=?, email=?, phone_number=? WHERE PS_id=?",
                passengerDto.getPassengerId(),    // New PS_id value to update
                passengerDto.getPassengerName(), // New name value
                passengerDto.getEmail(),         // New email value
                passengerDto.getContactNo(),     // New phone_number value
                passengerDto.getPassengerId()    // Original PS_id for WHERE clause
        );
    }

    public boolean deletePassenger(String passengerId ) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from passenger where PS_id=?", passengerId);
    }

    public ArrayList<String> getAllPassengerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select PS_id from passenger");

        ArrayList<String> passengerIds = new ArrayList<>();

        while (rst.next()) {
            passengerIds.add(rst.getString(1));
        }
        return passengerIds;
    }

    public ArrayList<String> getAllPassengerContacts() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select phone_number from passenger");

        ArrayList<String> passengerIds = new ArrayList<>();

        while (rst.next()) {
            passengerIds.add(rst.getString(1));
        }
        return passengerIds;
    }

    public PassengerDto findById(String selectedPsId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from passenger where PS_id=?", selectedPsId);

        if (rst.next()) {
            return new PassengerDto(
                    rst.getString(1),
                    rst.getString(5),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    public PassengerDto findByContact(String selectedContact) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from passenger where phone_number=?", selectedContact);

        if (rst.next()) {
            return new PassengerDto(
                    rst.getString(1),
                    rst.getString(5),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    public int getTotalPassengers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select count(*) from passenger");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    public String getNextPassengerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select PS_id from passenger order by PS_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1); // Last passenger ID
            String numericPart = lastId.substring(2); // Extract the numeric part after "PS"
            int nextIdNumber = Integer.parseInt(numericPart) + 1; // Increment by 1
            return String.format("PS%04d", nextIdNumber); // Format with leading zeros (e.g., PS0008)
        }
        return "PS0001"; // Default ID if no data is found
    }

}
