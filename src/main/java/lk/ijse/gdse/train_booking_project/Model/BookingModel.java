package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingModel {
    public String getNextBookingId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select B_id from booking order by B_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1); // Last passenger ID
            String numericPart = lastId.substring(2); // Extract the numeric part after "PS"
            int nextIdNumber = Integer.parseInt(numericPart) + 1; // Increment by 1
            return String.format("B%03d", nextIdNumber); // Format with leading zeros (e.g., PS0008)
        }
        return "B0001"; // Default ID if no data is found
    }

    public int getTotalBookings() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select count(*) from booking");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
