package net.bearcreekconsulting.geektrek.user.gtspringuser.controller;

import lombok.RequiredArgsConstructor;
import net.bearcreekconsulting.geektrek.user.gtspringuser.model.Role;
import net.bearcreekconsulting.geektrek.user.gtspringuser.model.User;
import net.bearcreekconsulting.geektrek.user.gtspringuser.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Created by David on 8/24/2019. Copyright (c) 2019 Bear Creek Consulting LLC.
 */
@RequiredArgsConstructor
@RestController
public class UserController {
    private UserService userService;

    @PostMapping("/service/registration")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByUserName(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        userService.saveNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/user/service/user")
    public ResponseEntity<?> getUser(Principal principal) {
        if(principal==null || principal.getName()==null) {
            return ResponseEntity.ok(principal);
        }
        return new ResponseEntity<User>(userService.findByUserName(principal.getName()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value= "/service/names")
    public ResponseEntity<?> getUsers(@RequestBody List<UUID> idList) {
        return ResponseEntity.ok(userService.findUsers(idList));
    }
}
