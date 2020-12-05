package br.com.udemy.controller;

import br.com.udemy.repository.UserRepository;
import br.com.udemy.security.AccountCredentialsVO;
import br.com.udemy.security.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    @ApiOperation("Authenticate a user")
    @PostMapping(value="/signin", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity signin(@RequestBody AccountCredentialsVO credentials)  {
        try {
            var userName = credentials.getUserName();
            var password = credentials.getPassword();
            manager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            var user = repository.findByUsername(userName);
            var token = "";

            if (user != null) {
                token = tokenProvider.createToken(userName, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + userName+ " not found!");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", userName);
            model.put("token", token);

            return ok(model);
        } catch (AuthenticationException er) {
            throw new BadCredentialsException("Invalid username/password provided");
        }
    }
}
