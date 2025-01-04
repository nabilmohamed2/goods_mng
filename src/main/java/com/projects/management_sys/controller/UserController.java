package com.projects.management_sys.controller;

import com.projects.management_sys.dto.LoginResponse;
import com.projects.management_sys.entity.User;
import com.projects.management_sys.exception.ResourceNotFoundException;
import com.projects.management_sys.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;

    @Value("${role.admin}")
    private String roleAdmin;

    @Value("${role.user}")
    private String roleUser;

    @GetMapping("/protectedData")
    public ResponseEntity<LoginResponse> getProtectedData (@RequestHeader("Authorization") String token) throws ResourceNotFoundException {
        System.out.println("----------------------- Came in Token: "+token);
        if ( (token != null) && (token.startsWith("Bearer ")) ) {
            String jwtToken = token.substring(7);
            System.out.println("----------------------- Token: "+jwtToken);
            try{
                if( jwtUtil.isValid(jwtToken) ) {
                    String username = jwtUtil.extractUsername(jwtToken);
                    Set<String> roles = jwtUtil.extractRoles(jwtToken);
                    LoginResponse loginResponse = new LoginResponse(username, roles);
                    System.out.println("----------------" +"access: "+username);
                    return new ResponseEntity<>(loginResponse, HttpStatus.OK);
                }
            }
            catch (Exception e) {
                throw new ResourceNotFoundException("Role doesn't exist");
            }
        }
        LoginResponse loginResponse = new LoginResponse("Token not valid", null);
        return new ResponseEntity<>(loginResponse,HttpStatus.OK);

    }

}
