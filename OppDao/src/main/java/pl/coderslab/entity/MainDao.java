package pl.coderslab.entity;
import pl.coderslab.DbUtil;


public class MainDao {
    public static void main(String[] args) {

        // Tworzenie usera:
        // User newUser41 = new User("Janina Kowalska", "jankow@wp.pl", "fruzia5@");
        // User createUser41 = userDao.create(newUser41);

        // Odczyt usera:
        // - istniejącego:
        // System.out.println(userDao.read(15));
        // - nieistniejącego (informuje o błędzie, zwraca null):
        // System.out.println(userDao.read(99999));

        // Aktualizacja usera:
        // UserDao userDao = new UserDao();
        // User userToUpdate1 = userDao.read(20);
        // userToUpdate1.setUserName("Maria Żółkiewska");
        // userToUpdate1.setEmail("mazol@wp.pl");
        // userToUpdate1.setPassword("fruzia66&");
        // userDao.update(userToUpdate1);

        // Usunięcie usera:
        // UserDao userDao = new UserDao();
        // userDao.delete(25);

        // Listing wszystkich userów:
        UserDao userDao = new UserDao();
        User[] allUsers = userDao.findAll();
        if (allUsers.length != 0) {
            for (User a : allUsers) {
                    System.out.println(a);
                }
        } else {
            System.out.println("Database is empty.");
        }
    }
}