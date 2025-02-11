package org.example.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);

            try{
                username = jwtUtil.extractUsername(token);
            }catch (ExpiredJwtException e) {
                // Handle expired token exception
                System.out.println("JWT Token has expired");
            } catch (Exception e) {
                // Handle other exceptions
                System.out.println("Error extracting username from JWT Token");
            }
        }else {
            System.out.println("JWT Token doesn't begin with bearer string");
        }

        if(username != null && SecurityContextHolder
                .getContext().getAuthentication() == null) {
            var userDetails = userDetailService.loadUserByUsername(username);
            if(jwtUtil.validateToken(token, userDetails.getUsername())){

                // Create an authentication token
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());

                // Set additional details
                authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));

                // Set the authentication in the context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                // Create authentication object and set it in the SecurityContext
//                var authenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource()
//                        .buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(
//                        authenticationToken);

            }
        }
        filterChain.doFilter(request,response);
    }
}
