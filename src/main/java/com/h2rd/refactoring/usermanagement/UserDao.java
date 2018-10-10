package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;
import java.util.Objects;

public class UserDao {

    private ArrayList<User> users = new ArrayList<>();

    private static volatile UserDao userDao;

    public static UserDao getUserDao() {
    	
        if (Objects.isNull(userDao)) {
        	synchronized (UserDao.class) {    // lazy initialization 
        		 if (Objects.isNull(userDao)){
        			 userDao = new UserDao();
        		 }
			}
        }
        return userDao;
    }

    public synchronized User saveUser(User user) {
    	validateUser(user);
    	
    	if(isExistingEmail(user.getEmail())){
    		throw new RuntimeException("Given email id is already registered with some user");
    	}
    	
        if (users.add(user)){
        	return user;
        }else{
        	throw new RuntimeException("Error in saving user");
        }
        
    }

    public ArrayList<User> getUsers() {
            return users;
    }

    public synchronized User deleteUser(String email) {
    	//two users may have same name, so it is better to remove user based on email.
    	//user will be deleted on the basis of email as email is the unique identifier,
    	//this functionality is based on the overiding equals and hashcode method of User class
    	//no more concurrent modification exception will be thrown from this part.
    	User userToDelete = users.stream().filter(user->user.getEmail().equals(email)).findFirst()
    							.orElseThrow(()->new RuntimeException("No user exist with this email"));
    	users.remove(userToDelete);
    	return userToDelete;
    
    }

    public synchronized User updateUser(User userToUpdate) { //update operation will be performed in synchronously
        validateUser(userToUpdate);
    	//filtering user based on email as email is unique identifier.
        return users.stream().filter(user->user.getEmail().equals(userToUpdate.getEmail())).map(user->{
    		user.setName(userToUpdate.getName());
    		user.setRoles(userToUpdate.getRoles());
    		return user;
    	}).findFirst().orElseThrow(()->new RuntimeException("No user found with given email"));
    }
    
	public User findUser(String name) {
    	return users.stream().filter(user->user.getName().equals(name)).findFirst()
    			.orElseThrow(()-> new RuntimeException("No user found with name:" + name));
    }

    private void validateUser(User userToUpdate) {
    	if(Objects.isNull(userToUpdate.getName())){
    		throw new RuntimeException("User name cannot be empty");
    	}else if(userToUpdate.getRoles().isEmpty()){ // Bussiness requirement: user must have one role
    		throw new RuntimeException("User must have one role assigned");
    	}else if(Objects.isNull(userToUpdate.getEmail())){ // Bussiness requirement to have unique email
    		throw new RuntimeException("Email field cannot be empty");	
    	}
		
	}
    
    private boolean isExistingEmail(String email){  // to check uniqueness of email
    	return users.stream().anyMatch(user->user.getEmail().equals(email));
    	
    }
}
