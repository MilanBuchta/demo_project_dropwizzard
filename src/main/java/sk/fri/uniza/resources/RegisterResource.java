package sk.fri.uniza.resources;

import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.Auth;
import sk.fri.uniza.auth.BasicDao;
import sk.fri.uniza.auth.Roles;
import sk.fri.uniza.auth.User;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class RegisterResource {

    private BasicDao basicDao;

    public RegisterResource(BasicDao basicDao) {
        this.basicDao = basicDao;
    }

    @POST
    @Path("/register")
    public String register(@HeaderParam("Authorization") String authorization) {
        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        // credentials = username:password
        final String[] values = credentials.split(":", 2);

        basicDao.save( new User(values[0], values[1], ImmutableSet.of(Roles.READ_ONLY)));

        return "success";
    }
}
