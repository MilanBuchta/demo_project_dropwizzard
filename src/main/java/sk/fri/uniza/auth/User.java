package sk.fri.uniza.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;
import java.util.Set;

public class User implements Principal {
    final private String userName;
    final private  String password;
    final private Set<String> roles;

    public User(@JsonProperty("userName") String userName, @JsonProperty("password")String password, @JsonProperty("roles")Set<String> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return userName;
    }
}
