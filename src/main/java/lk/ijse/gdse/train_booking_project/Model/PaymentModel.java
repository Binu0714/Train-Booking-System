package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    public double getTotalRevenue() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT SUM(amount) FROM payment");

        if (rst.next()) {
            return rst.getDouble(1);
        }
        return 0.0;
    }

}
