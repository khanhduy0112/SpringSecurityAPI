package com.learning.security.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String SECRET = "NGUYENKHANHDUYNGUYENKHANHDUYNGUYENKHANHDUY";

        String tokenBearer = request.getHeader("Authorization");
        if (tokenBearer != null && tokenBearer.startsWith("Bearer ")) {
            String token = tokenBearer.replace("Bearer ", "");

            String username = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes())).build().parseClaimsJws(token).getBody().getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);

    }
}
