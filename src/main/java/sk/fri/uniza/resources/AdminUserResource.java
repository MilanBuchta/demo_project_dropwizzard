package sk.fri.uniza.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import sk.fri.uniza.api.Saying;
import sk.fri.uniza.auth.BasicDao;
import sk.fri.uniza.auth.Roles;
import sk.fri.uniza.auth.User;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminUserResource {
    private BasicDao basicDao;

    public AdminUserResource(BasicDao basicDao) {
        this.basicDao = basicDao;
    }

    @GET
    @Timed
    @Path("/users")
    @RolesAllowed({Roles.ADMIN})
    public List<User> getUsers(@Auth User user) {
        return basicDao.getAll();
    }

    @DELETE
    @Timed
    @Path("{name}")
    @RolesAllowed({Roles.ADMIN})
    public String deleteUser(@PathParam("name") String name) {
        basicDao.delete(name);
        return "success";
    }

    @PUT
    @Path("/update")
    @RolesAllowed({Roles.ADMIN})
    public String updateUser(User user) {
        basicDao.update(user);
        return "success";
    }

    @POST
    @Path("/save")
    @RolesAllowed({Roles.ADMIN})
    public String saveUser(User user) {
        basicDao.save(user);
        return "success";
    }
}
