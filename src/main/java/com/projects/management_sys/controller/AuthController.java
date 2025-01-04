package com.projects.management_sys.controller;

import com.projects.management_sys.dto.RegisterRequest;
import com.projects.management_sys.entity.Item;
import com.projects.management_sys.entity.Role;
import com.projects.management_sys.entity.User;
import com.projects.management_sys.repository.ItemRepository;
import com.projects.management_sys.repository.RoleRepository;
import com.projects.management_sys.repository.UserRepository;
import com.projects.management_sys.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    //Needed stuffs before jumping into API.

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ItemRepository itemRepository;
    //As we don't want the passwords to be blindly visible on the DB.
    //We encode the password and update in db.
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ItemRepository itemRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.itemRepository = itemRepository;
    }

    //API PART

    //1. Register new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser (@RequestBody RegisterRequest registerRequest) {

        //Check if user already present
        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken!");
        }
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        newUser.setPassword(encodedPassword);
        System.out.println("Encoded password: "+encodedPassword);
        System.out.println("-------------------- Roles: "+registerRequest.getRoles());
        Set<Role> roles = new HashSet<>();
        for ( String roleName : registerRequest.getRoles() ) {
            Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found: "+roleName));
            roles.add(role);
        }

        newUser.setRoles(roles);
        userRepository.save(newUser);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    //2. Login User
    @PostMapping("/login")
    public ResponseEntity<String> login ( @RequestBody User loginRequest ) {
        try {
            //authenticationManager will refer to loadUserByUsername -> CustomUserDetailsService
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                    loginRequest.getPassword()));
        }
        catch ( Exception e ) {
            System.out.println("Exception: "+e);
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }
        System.out.println(loginRequest.getUsername());
        String token = jwtUtil.generateToken(loginRequest.getUsername());
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

}
