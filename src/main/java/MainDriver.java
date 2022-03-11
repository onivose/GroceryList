import models.User;
import repository.UserDAO;
import repository.UserDAOImpl;

public class MainDriver {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        System.out.println(userDAO.getUserGivenUsername("user2"));

        userDAO.createUser(new User("kev1243", "pass123", "Kevin", "Childs")) ;



    }
}
