package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.EndStationDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DestinationModel {
    public ArrayList<String> getAllStations() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select end_station from end_station");

        ArrayList<String> destinations = new ArrayList<>();

        while (rst.next()) {
            destinations.add(rst.getString(1));
        }
        return destinations;
    }

    public EndStationDto findById(String selectedStationId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from end_station where end_station=?", selectedStationId);

        if (rst.next()) {
            return new EndStationDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getDouble(5)
            );
        }
        return null;
    }

    public double getPrice(String classType, String selectedDes ) throws SQLException, ClassNotFoundException {
        String columnName;

        System.out.println("1.."+selectedDes);
        System.out.println("2.."+classType);

         //Determine the column name based on class type
        switch (classType) {
            case "First Class":
                columnName = "1st_class_price";
                break;
            case "Second Class":
                columnName = "2nd_class_price";
                break;
            case "Third Class":
                columnName = "3rd_class_price";
                break;
            default:
                throw new IllegalArgumentException("Invalid class type: " + classType);
        }

//        if (classType.equals("First Class")) {
//            columnName = "1st_class_price";
//            System.out.println("3.."+selectedDes);
//        }

        System.out.println("Selected Destination: " + selectedDes);
        System.out.println("Class Type: " + classType);
        System.out.println("Column Name: " + columnName );

        // Execute the query to fetch the price for the selected destination and class type
        ResultSet rst = CrudUtil.execute("SELECT "+ columnName +" FROM end_station WHERE end_station=?", selectedDes);
        if (rst.next()) {
            System.out.println("Price: " + rst.getDouble(1));
            return rst.getDouble(1);
        } else {
            System.out.println("No data found for the selected destination and class type.");
        }
        return 0;
    }

}
