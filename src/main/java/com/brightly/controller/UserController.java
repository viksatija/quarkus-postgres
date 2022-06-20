package com.brightly.controller;

import com.brightly.entity.User;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @GET
    public Collection<User> list() {
        System.out.println("WOrking -----------------");
        return User.listAll();
    }

    @GET
    @Path("/{id}")
    public User get(@PathParam("id") Integer id) {
        User f  = User.findById(id);
        if(f!=null){
            return f;
        }else{
            throw new NotFoundException("Unknown User id : " + id);
        }
    }

    @POST
    @Transactional
    public Collection<User> add(User User) {
        User.id=null; //ignore id
        User.persist(User);
        return User.listAll();
    }

    @PUT
    @Transactional
    public User update(User User) {
        User UserUpdated = User.findById(User.id);
        if(UserUpdated!=null){
            UserUpdated.name = User.name;
            UserUpdated.description = User.description;
            User.persist(UserUpdated);
            return UserUpdated;
        }else{
            throw new NotFoundException("Unknown User id : " + User.id);
        }

    }

    @DELETE
    @Transactional
    public void delete(User User) {
        User f  = User.findById(User.id);
        if(f!=null){
            User.deleteById(User.id);
        }else{
            throw new NotFoundException("Unknown User id : " + User.id);
        }
    }
}