package de.ait_tr.g_40_1_shop.security;

import de.ait_tr.g_40_1_shop.domain.entity.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class AuthInfo implements Authentication {
    private boolean authenticated;
    private String userName;
    private Set<Role> roles;

    public AuthInfo(String userName, Set<Role> roles) {
        this.userName = userName;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return userName;
    }
}
