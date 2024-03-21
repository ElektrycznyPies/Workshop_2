package pl.coderslab.entity;
import pl.coderslab.DbUtil;
import java.sql.*;
import java.util.Arrays;

public class UserDao extends DbUtil {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? where id = ?";
    private static final String DEL_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String LIST_ALL_QUERY = "SELECT * FROM users";



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

//          DELETE
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

    //      LIST ALL

    public User[] findAll() {
        try (Connection conn = connect();
             PreparedStatement prepStat = conn.prepareStatement(LIST_ALL_QUERY)) {
            ResultSet rs = prepStat.executeQuery();

            User[] allUsers = new User[0];

            while (rs.next()) {
                User nextUser = new User();
                nextUser.setId(rs.getInt("id"));
                nextUser.setUserName(rs.getString("username"));
                nextUser.setEmail(rs.getString("email"));
                nextUser.setPassword(rs.getString("password"));

                allUsers = addToArray(nextUser, allUsers);

            }
            return allUsers;
        } catch (SQLException e) {
            System.out.println("Database problem: " + e.getMessage());
        }
        return null;
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }

}
