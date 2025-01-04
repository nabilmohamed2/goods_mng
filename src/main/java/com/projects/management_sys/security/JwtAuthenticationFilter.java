package com.projects.management_sys.security;

import com.projects.management_sys.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;


import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authentication");

        if ( (token != null) && (token.startsWith("Bearer")) ) {
            //Remove string "Bearer"
            token = token.substring(7);
            String userName = jwtUtil.extractUsername(token);

            //Verifying user name
            //SecurityContextHolder refers to Authentication object.
            if((userName != null) && SecurityContextHolder.getContext().getClass() == null) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

                if( jwtUtil.isValid(token) ) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
                            (userDetails, null, userDetails.getAuthorities());
                    //Setting type of authentication -> request
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
        }

        filterChain.doFilter(request,response);
    }

    // SecurityContextHolder is a fundamental class in Spring Security that holds the
    // security context of the current execution thread. The security context typically
    // includes details about the authenticated user, such as their identity and granted
    // authorities (roles or permissions). The role of SecurityContextHolder is to manage and
    // provide access to this context across various parts of your application during a user's
    // interaction with the system.
}
