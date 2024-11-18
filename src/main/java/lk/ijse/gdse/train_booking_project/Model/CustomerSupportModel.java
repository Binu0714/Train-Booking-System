package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.CustomerSupportDto;
import lk.ijse.gdse.train_booking_project.Dto.ScheduleDto;
import lk.ijse.gdse.train_booking_project.Dto.TrainDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerSupportModel {
    public ArrayList<CustomerSupportDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from customer_support");

        ArrayList<CustomerSupportDto> customerSupportDtos = new ArrayList<>();

        while (rst.next()) {
            CustomerSupportDto customerSupportDto = new CustomerSupportDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            customerSupportDtos.add(customerSupportDto);
        }
        return customerSupportDtos;
    }

    public boolean saveCS(CustomerSupportDto customerSupportDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into customer_support values (?,?,?,?,?)",
                customerSupportDto.getCsId(),
                customerSupportDto.getAdminId(),
                customerSupportDto.getDate(),
                customerSupportDto.getIssue(),
                customerSupportDto.getPassengerId()
        );
    }

    public String getNextCsId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select CS_id from customer_support order by CS_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1); // Last passenger ID
            String numericPart = lastId.substring(2); // Extract the numeric part after "PS"
            int nextIdNumber = Integer.parseInt(numericPart) + 1; // Increment by 1
            return String.format("CS%03d", nextIdNumber); // Format with leading zeros (e.g., PS0008)
        }
        return "CS001"; // Default ID if no data is found
    }


}
