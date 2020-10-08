package com.learning.security.controller;

import com.learning.security.dto.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> getJwtToken(@RequestBody UserLogin userLogin) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //create token
            final String SECRET = "NGUYENKHANHDUYNGUYENKHANHDUYNGUYENKHANHDUY";
            String token = Jwts.
                    builder()
                    .setSubject(userLogin.getUsername())
                    .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 365232323l))
                    .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                    .compact();
            return new ResponseEntity<String>(token, HttpStatus.OK);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("user name or password is incorrect!!", HttpStatus.BAD_REQUEST);
    }

}
