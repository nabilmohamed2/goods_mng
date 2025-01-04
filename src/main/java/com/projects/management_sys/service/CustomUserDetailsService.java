package com.projects.management_sys.service;

import com.projects.management_sys.entity.User;
import com.projects.management_sys.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
//UserDetailsService already provided by Spring Security
//As the interface is already written and provided by spring security we just implement it,
//without creating a new interface for abstraction
public class CustomUserDetailsService implements UserDetailsService {

    public UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //This method is used by AuthenticationManager (delegates task to) -> AuthenticationProvider
    //AuthenticationProvider (refers) -> UserDetails.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        //userDetails (Provides authority to) -> User (Class created by us) -> (provide access through) SimpleGrantedAuthority meth.
        //Returning userDetails by passing userName, password, roles
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        //User (mapped with) -> Role
    }
}
