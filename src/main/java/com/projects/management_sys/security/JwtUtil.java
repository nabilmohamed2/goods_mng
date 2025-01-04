package com.projects.management_sys.security;

import com.projects.management_sys.entity.Role;
import com.projects.management_sys.entity.User;
import com.projects.management_sys.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//This component is used for authorization using token.
//Token is sent by the user.
@Component
public class JwtUtil {
    //secret key
    //Random generation of keys
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // expiration time - 1day = 86400000MS
    private final int jwtExpirationMs = 86400000;

    private UserRepository userRepository;

    @Autowired
    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Generate token
    public String generateToken(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        //Optional.get() without Optional.isPresent() check
        //As we use Optional type we need to use get() before accessing the actual getters.
        System.out.println("-------------------------Login user---------------------"+user.get().getUsername());
        Set<Role> roles = user.get().getRoles();

        //Add roles to the token
        //Subject, Roles, Issued at, Expiration, sign with key, compact(generates a string token)
        return Jwts.builder().setSubject(username).claim("roles", roles.stream()
                        .map(role -> role.getName()).collect(Collectors.joining(",")))
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(secretKey).compact();
    }

    //Extract username
    public String extractUsername(String token) {
        // parserBuilder(signingKey(secretKey)) -> parseClaims(token) -> Content
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Set<String> extractRoles(String token) {
        String rolesString = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
                .getBody().get("roles", String.class);
        //As wet already set the name of roles as "roles" in the generateToken().
        //We need to access the roles by the name we defined before.

        return Set.of(rolesString);
    }

    //Validating tokens
    public boolean isValid( String token ) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
