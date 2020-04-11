package server.db;

import server.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO implements IDAO {

    private static final String SQL_INSERT = "INSERT INTO sport.store (title, description, quantity,price) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE sport.store SET title=?,description=?,quantity=?,price=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM sport.store WHERE id=?";
    private static final String SQL_SELECT = "SELECT * FROM sport.store";

    private DBConnection dbConnection = new DBConnection();
    private Connection connection = dbConnection.getConnection();

    @Override
    public void save(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());
            int row = preparedStatement.executeUpdate();
            System.out.println("Inserted row: " + row);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    @Override
    public void edit(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getId());
            int row = preparedStatement.executeUpdate();
            System.out.println("Updated row: " + row);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    @Override
    public void delete(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, product.getId());
            int row = preparedStatement.executeUpdate();
            System.out.println("Deleted row: " + row);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(SQL_SELECT)
        ) {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                list.add(new Product(id, title, description, quantity, price));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        System.out.println("Selected list size: " + list.size());
        return list;
    }
}