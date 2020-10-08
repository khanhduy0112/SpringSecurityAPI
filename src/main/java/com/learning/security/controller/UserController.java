package com.learning.security.controller;


import com.learning.security.dto.UserDTO;
import com.learning.security.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Object findAll() {
        try {
            List<UserDTO> users = userService.findAll();
//            System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            return new ResponseEntity<Object>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("not find all users", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/{id}")
    public Object findUserById(@PathVariable("id") Integer id) {
        try {
            UserDTO userDTO = userService.findById(id);
            return new ResponseEntity<Object>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(String.format("not found user have id : %s", id), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/search={keywords}")
    public Object findByNameOrEmail(@PathVariable("keywords") String keywords) {
        try {
            List<UserDTO> userDTOList = userService.findByNameContains(keywords);
            return new ResponseEntity<Object>(userDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(String.format("not found username or email : %s", keywords), HttpStatus.BAD_REQUEST);
    }


}



