package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class UserDaoUnitTest {

    UserDao userDao;

    @Test
    public void saveUserTest() {
        userDao = UserDao.getUserDao();
        User user = new User("Fake Name","fake@email.com",Arrays.asList("admin", "master"));
        assertNotNull(userDao.saveUser(user)); // successful save will return newly added user object
        
    }

    @Test
    public void deleteUserTest() {
       User user = new User("Fake Name1","fake1@email1.com",Arrays.asList("admin", "master"));
       userDao = UserDao.getUserDao();
       userDao.saveUser(user);
       assertNotNull(userDao.deleteUser(user.getEmail())); // success delete will return deleted user object

    }
    
    @Test(expected=RuntimeException.class)
    public void deleteUserTest2() {
       userDao.deleteUser("notRegistered@email.com"); 

    }
    
    @Test
    public void getUserTest() {
        User user = new User("Fake Name2","fake2@email.com",Arrays.asList("admin", "master"));
        userDao = UserDao.getUserDao();
        userDao.saveUser(user);
        assertTrue(userDao.getUsers().size()>1); 
     }
    
    @Test
    public void findUserTest() {
        User user = new User("Fake Name3","fake3@email.com",Arrays.asList("admin", "master"));
        userDao = UserDao.getUserDao();
        userDao.saveUser(user);
        assertNotNull(userDao.findUser(user.getName()));
     }
    
    @Test
    public void updateUserTest() {
        User user = new User("Fake Name4","fake4@email.com",Arrays.asList("admin", "master"));
        userDao = UserDao.getUserDao();
        userDao.saveUser(user);
        
        User userToUpdate = new User("updated Fake Name4","fake4@email.com",Arrays.asList("admin", "master"));
        assertEquals(userDao.updateUser(userToUpdate).getName(),userToUpdate.getName());
     }
    
}