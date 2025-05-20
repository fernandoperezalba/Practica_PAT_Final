package practicaFinal.pfinal.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import practicaFinal.pfinal.config.JwtTokenUtil;
import practicaFinal.pfinal.model.*;
import practicaFinal.pfinal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(
        path = "/api/users",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/new-user")
    public @ResponseBody
    ResponseEntity<UserDetail> newUser(@Valid @RequestBody NewUserRequest userRequest) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                    SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                    authorities)
        );

        String username = userRequest.getUsername();
        LOGGER.info("Realizando el registro para el usuario: "+username);


        return userService.newUser(userRequest.getId() ,username, userRequest.getPassword1(), userRequest.getPassword2());
    }

    @PostMapping("/update-password")
    public @ResponseBody
    ResponseEntity<UserDetail> updatePassword(@RequestBody UserChangePasswordRequest userChangePasswordRequest) {
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        LOGGER.info("Current authenticated user: {}", currentPrincipalName);
        */
        return userService.updatePassword(userChangePasswordRequest);
    }

    @GetMapping("/user-id")
    public @ResponseBody
    Long getUserId(@RequestParam String username){
        return userService.getIdByUsername(username);
    }

    @GetMapping("/isAbuelo")
    public @ResponseBody
    boolean mostrarUsersAbuelos(@RequestParam Long id){
        return userService.getUsersAbuelos(id);
    }

    @GetMapping("/isAyudante")
    public @ResponseBody
    boolean mostrarUsersAyudantes(@RequestParam Long id){
        return userService.getUsersAyudantes(id);
    }

}
