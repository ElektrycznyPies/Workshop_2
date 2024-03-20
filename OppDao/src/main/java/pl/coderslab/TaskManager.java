package pl.coderslab;

import java.sql.Connection;
import java.sql.SQLException;

public class TaskManager {
    public static void main(String[] args) {

        try (Connection conn = DbUtil.connect()) {
            System.out.println("Connection successful");
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }
}
