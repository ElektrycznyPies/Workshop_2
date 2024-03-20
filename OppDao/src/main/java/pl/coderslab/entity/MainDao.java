package pl.coderslab.entity;
import pl.coderslab.DbUtil;


public class MainDao {
    public static void main(String[] args) {
//        User newUser1 = new User("Fruzia Zuzia", "fziaaafnet.pl", "fruzia5");
//        User newUser2 = new User("Zinadin Zidane", "zsxaaae@wp.uk", "dupa.6");
//
        UserDao userDao = new UserDao();  // Tworzymy obiekt UserDao
//
//        User createdUser1 = userDao.create(newUser1);  // Wywołujemy metodę create na obiekcie UserDao
//        User createdUser2 = userDao.create(newUser2);
        System.out.println(userDao.read(16));

//        System.out.println(newUser1.toString() + " created user with id = " + createdUser1.getId());
//        System.out.println(newUser2.toString() + " created user with id = " + createdUser2.getId());
    }
}