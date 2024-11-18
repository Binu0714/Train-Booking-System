package lk.ijse.gdse.train_booking_project.Model;

import lk.ijse.gdse.train_booking_project.Dto.ClassDto;
import lk.ijse.gdse.train_booking_project.Dto.EndStationDto;
import lk.ijse.gdse.train_booking_project.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassModel {
    String Train;

    public ArrayList<String> getAllClassIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select description from class where T_id=?",Train);

        System.out.println(Train);

        ArrayList<String> classIds = new ArrayList<>();

        while (rst.next()) {
            classIds.add(rst.getString(1));
        }
        return classIds;
    }

    public ArrayList<ClassDto> getAllClass() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from class");

        ArrayList<ClassDto> classDtos = new ArrayList<>();

        while (rst.next()) {
            ClassDto classDto = new ClassDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5)
            );
            classDtos.add(classDto);
        }
        return classDtos;
    }

    public String getNextClassId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select C_id from class order by C_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1); // Last passenger ID
            String numericPart = lastId.substring(2); // Extract the numeric part after "PS"
            int nextIdNumber = Integer.parseInt(numericPart) + 1; // Increment by 1
            return String.format("CL%02d", nextIdNumber); // Format with leading zeros (e.g., PS0008)
        }
        return "CL01"; // Default ID if no data is found
    }

    public boolean saveClass(ClassDto classDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into class values (?,?,?,?,?)",
                classDto.getClassId(),
                classDto.getTrainId(),
                classDto.getClassNo(),
                classDto.getDescription(),
                classDto.getSeats()
        );
    }

    public boolean deleteClass(String classId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from class where C_id=?", classId);
    }

    public boolean updateClass(ClassDto classDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update class set C_id=?, T_id=?, C_no=?, description=?, no_of_seats=? where C_id=?",
                classDto.getClassId(),
                classDto.getTrainId(),
                classDto.getClassNo(),
                classDto.getDescription(),
                classDto.getSeats(),
                classDto.getClassId()
        );
    }

    public void test(String selectedValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select T_id from train where name = ?", selectedValue);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                    Train=resultSet.getString(1);

                }
    }

    public ClassDto findById(String selectedDes) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from class where description=?", selectedDes);

        if (rst.next()) {
            return new ClassDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5)
            );
        }
        return null;
    }

}
