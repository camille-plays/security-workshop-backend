package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wise.wisewomenhackathon.controllers.response.UserResponse;
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

    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable(value = "id") Long userId) {
        return userService.delete(userId);
    }

    @DeleteMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUsers() {
        return userService.deleteAll();
    }
}
