package com.example.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class UserDataDetails implements UserDetails {

    private UserData userData;

    public UserDataDetails(UserData userData) {
        this.userData = userData;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(userData.getRoles().split(","))
                     .map(SimpleGrantedAuthority::new)
                     .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userData.getPassword();
    }

    @Override
    public String getUsername() {
        return userData.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
