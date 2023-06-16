package db;

import Models.Shippers;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public int insertNewShipper(Shippers shipper) {
        String query = "INSERT INTO shippers (companyName, phone) values (?,?);";
        int rows = 0;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, shipper.getCompanyName());
            preparedStatement.setString(2, shipper.getPhone());

            rows = preparedStatement.executeUpdate();
            System.out.println(rows + " row have been updated");

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                while (keys.next()) {
                    keys.getString(1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return rows;
    }

    public List<Shippers> getAllShippers(){
        List<Shippers> shippers = new ArrayList<>();
        String query = "SELECT * FROM shippers;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
             {
                 ResultSet resultSet = preparedStatement.executeQuery();
                 while (resultSet.next()){
                     int id = resultSet.getInt(1);
                     String companyName = resultSet.getString(2);
                     String phone = resultSet.getString(3);

                     Shippers shipper = new Shippers(id, companyName, phone);
                     shippers.add(shipper);

                     System.out.println(shippers);
                 }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shippers;
    }
}
