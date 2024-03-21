package pl.coderslab.entity;
import pl.coderslab.DbUtil;
import java.sql.*;

public class UserDao extends DbUtil {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? where id = ?";
    private static final String DEL_USER_QUERY = "DELETE FROM users WHERE id = ?";

    //         CREATE
    public User create(User user) {
        try (Connection conn = connect();) {
            PreparedStatement prepStat = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStat.setString(1, user.getUserName());
            prepStat.setString(2, user.getEmail());
            prepStat.setString(3, user.getPassword()); // hashPassword(user.getPassword()));
            prepStat.executeUpdate();

            ResultSet rs = prepStat.getGeneratedKeys();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user:
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database problem: " + e.getMessage());
            return null;
        }
        return user;
    }


    //         READ
    public User read(int userId) {

        //DODAĆ: czy obiekt ma takie same dane jak w DB

        try (Connection conn = connect();
             PreparedStatement prepStat = conn.prepareStatement(READ_USER_QUERY)) {
            prepStat.setInt(1, userId); // przekazuje userId jako pierwszy (i jedyny) parametr do zapytania SQL READ_U_Q
            ResultSet rs = prepStat.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                User user = new User(username, email, password);
                user.setId(rs.getInt("id"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Database problem: " + e.getMessage());
        }
        System.out.println("No record under this ID.");
        return null; // null, jeśli wiersz o podanym id nie został znaleziony
    }

    //         UPDATE
    public void update(User user) {
        try (Connection conn = connect();
             PreparedStatement prepStat = conn.prepareStatement(UPDATE_USER_QUERY)) {
            prepStat.setString(1, user.getUserName());
            prepStat.setString(2, user.getEmail());
            prepStat.setString(3, user.getPassword());
            prepStat.setInt(4, user.getId());
            int rowsUpdated = prepStat.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User no. " + user.getId() + " updated.");
            } else {
                System.out.println("No users updated.");
            }

        } catch (SQLException e) {
            System.out.println("Database problem: " + e.getMessage());
        }
    }

    public void delete(int userId) {
        try (Connection conn = connect();
             PreparedStatement prepStat = conn.prepareStatement(DEL_USER_QUERY)) {
            prepStat.setInt(1, userId); // przekazuje userId jako pierwszy (i jedyny) parametr do zapytania SQL READ_U_Q
            int rowsUpdated = prepStat.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User no. " + userId + " deleted.");
            } else {
                System.out.println("No users deleted.");
            }

        } catch (SQLException e) {
            System.out.println("Database problem: " + e.getMessage());
        }
    }
}
