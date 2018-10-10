package com.h2rd.refactoring.web;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes("application/json")
@Produces("application/json")
public class UserResource{
	
    public UserDao userDao;

    @POST       //Post method is used to create resource.
    @Path("/add")
    public Response addUser(User user) {
         // no need to check null for UserDao here.
        return Response.ok().entity(UserDao.getUserDao().saveUser(user)).build();
    }

    @PUT	//PUT method for update
    @Path("/update/{email}")
    public Response updateUser(@PathParam("email") String email, User user) {

        user.setEmail(email); // to make sure email in the PathParam should be same as email in the body
        return Response.ok().entity(UserDao.getUserDao().updateUser(user)).build();
    }

    @DELETE  //DELETE for deleting resource
    @Path("/delete/{email}")
    public Response deleteUser(@PathParam("email") String email) {
        return Response.ok().entity(UserDao.getUserDao().deleteUser(email)).build();
    }

    @GET
    @Path("/users")
    public Response getUsers() {
    	return Response.status(200).entity(UserDao.getUserDao().getUsers()).build();
    }

    @GET
    @Path("/search")
    public Response findUser(@QueryParam("name") String name) {
        return Response.ok().entity(UserDao.getUserDao().findUser(name)).build();
    }
}
