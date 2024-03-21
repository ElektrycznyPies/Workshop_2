package pl.coderslab.entity;
import pl.coderslab.DbUtil;


public class MainDao {
    public static void main(String[] args) {

        // Tworzenie usera:
        // User newUser1 = new User("Janina Kowalska", "jankow@wp.pl", "fruzia5@");
        // User createUser1 = userDao.create(newUser1);

        // Odczyt usera:
        // - istniejącego:
        // System.out.println(userDao.read(1));
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
        // userDao.delete(1);

        // Listing wszystkich userów:
//        UserDao userDao = new UserDao();
//        User[] allUsers = userDao.findAll();
//        if (allUsers != null) {
//            for (int i = 0; i < allUsers.length; i++) {
//                if (allUsers[i] == null) {
//                    System.out.println("null");
//                } else {
//                    System.out.println(allUsers[i]);
//                }
//            }
//        } else {
//            System.out.println("Database is empty.");
//        }
    }
}