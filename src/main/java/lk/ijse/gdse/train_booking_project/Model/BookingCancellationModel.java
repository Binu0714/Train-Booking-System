package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.BookinCancellationDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingCancellationModel {
    public ArrayList<BookinCancellationDto> getAllBookingCs() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from booking_cancellation");

        ArrayList<BookinCancellationDto> bookinCancellationDtos = new ArrayList<>();

        while (rst.next()) {
            BookinCancellationDto bookinCancellationDto = new BookinCancellationDto(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getString(2),
                    rst.getString(4),
                    rst.getDate(5)
            );
            bookinCancellationDtos.add(bookinCancellationDto);
        }
        return bookinCancellationDtos;
    }

    public String getNextBcId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Cancel_id from booking_cancellation order by Cancel_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1); // Last passenger ID
            String numericPart = lastId.substring(2); // Extract the numeric part after "PS"
            int nextIdNumber = Integer.parseInt(numericPart) + 1; // Increment by 1
            return String.format("BC%03d", nextIdNumber); // Format with leading zeros (e.g., PS0008)
        }
        return "BC001"; // Default ID if no data is found
    }

}
