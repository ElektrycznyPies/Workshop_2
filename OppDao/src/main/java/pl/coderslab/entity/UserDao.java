package pl.coderslab.entity;
import pl.coderslab.DbUtil;
import java.sql.*;

public class UserDao extends DbUtil {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";

    //         CREATE
    public User create(User user) {
         try (Connection conn = connect();) {
             PreparedStatement prepStat = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
             prepStat.setString(1, user.getUserName());
             prepStat.setString(2, user.getEmail());
             prepStat.setString(3, user.getPassword()); // hashPassword(user.getPassword()));
             //int status =
                     prepStat.executeUpdate();
             //if (status > 0) {
                 ResultSet rs = prepStat.getGeneratedKeys();
                 //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user:
                 if (rs.next()) {
                     user.setId(rs.getInt(1));
                 }
             } //else {
                 //conn.rollback(); // nieudana próba przywrócenia poprzedniego stanu bazy, by nie naliczać pustych ID
             //}
                catch (SQLException e) {
                    e.printStackTrace();
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
            e.printStackTrace();
        }

        return null; // null, jeśli wiersz o podanym id nie został znaleziony
    }
}


