package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wise.wisewomenhackathon.controllers.commands.UserCommand;
import wise.wisewomenhackathon.controllers.response.UserResponse;
import wise.wisewomenhackathon.model.User;
import wise.wisewomenhackathon.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> users() {
        return ResponseEntity.ok()
                .body(userService.users().stream().map(u -> new UserResponse(u.getUsername())).toList());
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> user(@PathVariable(value = "id") Long userId) {
        return ResponseEntity.ok(new UserResponse(userService.user(userId).getUsername()));
    }

    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody UserCommand userCommand) {
        return userService.save(userCommand);
    }

    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable(value = "id") Long userId) {
        return userService.delete(userId);
    }
}
