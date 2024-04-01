package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wise.wisewomenhackathon.controllers.commands.LoginCommand;
import wise.wisewomenhackathon.controllers.commands.RegisterCommand;
import wise.wisewomenhackathon.model.UserEntity;
import wise.wisewomenhackathon.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginCommand loginCommand) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginCommand.getUsername(),
                            loginCommand.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("user signed in successfully", HttpStatus.OK);
        } catch(Exception e) {
            System.out.println("error was " + e.getMessage());
        }
        return new ResponseEntity<>("could not authenticate user", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterCommand registerCommand) {
        if (userRepository.existsByUsername(registerCommand.getUsername())) {
            return new ResponseEntity<>("username is taken", HttpStatus.BAD_REQUEST);
        }
        userRepository.save(new UserEntity(registerCommand.getUsername(), passwordEncoder.encode(registerCommand.getPassword())));
        return new ResponseEntity<>("user registered successfully", HttpStatus.OK);
    }
}
