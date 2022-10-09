package com.example.gradingsystem.security;

import com.example.gradingsystem.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import java.util.List;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole().name(),
                getGrantedAuthorities(user.getRole().name())
        );
    }

    private static List<GrantedAuthority> getGrantedAuthorities(String userRole) {
        return Collections.singletonList(new SimpleGrantedAuthority(userRole));
    }
}

