package pl.coderslab;

import java.sql.*;

public class DbUtil {
    public static final String DB_NAME = "workshop2";
    private static final String DB_URL= String.format("jdbc:mysql://localhost:3306/%s?useSSL=false&characterEncoding=utf8&serverTimezone=UTC", DB_NAME);
    private static final String DB_USER= "root";
    private static final String DB_PASS = "coderslab";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
    /*public static void insert(Connection conn, String query, String... params) {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                for (String columnName : columnNames) {
                    System.out.println(resultSet.getString(columnName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";
    public static void remove(Connection conn, String tableName, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(DELETE_QUERY.replace("tableName", tableName));) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double countAvg(Connection conn, String columnName, String tableName) {
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM movies WHERE rating > ?")) {
            statement.setString(1, columnName);
            statement.setString(2, tableName);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getDouble("avg");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/
}


