package test.com.h2rd.refactoring.integration;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;

public class UserIntegrationTest {
	
	@Test
	public void createUserTest() {
		UserResource userResource = new UserResource();
		
		User integration = new User("integration","initial@integration.com",Arrays.asList("Test Role"));
		// user must have alteast one role
        
        Response response = userResource.addUser(integration);
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
	public void updateUserTest() {
		UserResource userResource = new UserResource();
		UserDao.getUserDao().saveUser(new User("Integration_name","email1@integration.com",Arrays.asList("Test Role")));

		User updated = new User("Updated_integration_name","email1@integration.com",Arrays.asList("Test Role"));
        // user must have alteast one role
        
        Response response = userResource.updateUser(updated.getEmail(),updated);
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void findUserTest() {
		UserResource userResource = new UserResource();
		UserDao.getUserDao().saveUser(new User("Integration_name2","email2@integration.com",Arrays.asList("Test Role")));

		Response response = userResource.findUser("Integration_name2");
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void getUserTest() {
		UserResource userResource = new UserResource();
		UserDao.getUserDao().saveUser(new User("Integration_name3","email3@integration.com",Arrays.asList("Test Role")));

		Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void deleteUserTest() {
		UserResource userResource = new UserResource();
		UserDao.getUserDao().saveUser(new User("Integration_name4","email4@integration.com",Arrays.asList("Test Role")));

		Response response = userResource.deleteUser("email4@integration.com");
        Assert.assertEquals(200, response.getStatus());
	}
	
	
}
