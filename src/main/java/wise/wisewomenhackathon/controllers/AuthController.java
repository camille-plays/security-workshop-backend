package wise.wisewomenhackathon.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import wise.wisewomenhackathon.Exceptions.UsernameAlreadyExists;
import wise.wisewomenhackathon.config.security.JwtGenerator;
import wise.wisewomenhackathon.controllers.commands.LoginCommand;
import wise.wisewomenhackathon.controllers.commands.RegisterCommand;
import wise.wisewomenhackathon.controllers.response.AuthResponse;
import wise.wisewomenhackathon.repository.UserRepository;
import wise.wisewomenhackathon.service.BalanceService;
import wise.wisewomenhackathon.service.UserService;


@RestController
//@CrossOrigin(origins = "http://127.0.0.1:3000", allowCredentials = "true")
@RequestMapping("/api/auth")
public class AuthController {
    private final BalanceService balanceService;
    private final UserService userService;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, BalanceService balanceService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.balanceService = balanceService;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody LoginCommand loginCommand, HttpServletResponse response) {
        try {

            AuthResponse authResponse = authenticationAndGenerateToken(loginCommand.getUsername(), loginCommand.getPassword());

            ResponseCookie jwtTokenCookie = ResponseCookie.from("accessToken", authResponse.accessToken)
                    .httpOnly(true)
                    .sameSite("None")
                    .secure(true)
                    .path("/")
                    .domain("localhost")
                    .maxAge(Math.toIntExact(3600))
                    .build();
            ResponseCookie userIdCookie = ResponseCookie.from("userId", authResponse.userId)
                    .httpOnly(true)
                    .sameSite("None")
                    .secure(true)
                    .path("/")
                    .domain("localhost")
                    .maxAge(Math.toIntExact(3600))
                    .build();
            response.addHeader("Set-Cookie", jwtTokenCookie.toString());
            response.addHeader("Set-Cookie", userIdCookie.toString());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            System.out.println("error was " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterCommand registerCommand) {
        if (userRepository.existsByUsername(registerCommand.getUsername())) {
            throw new UsernameAlreadyExists("username is already taken");
        }
        userService.saveNewUserInitialiser(registerCommand.getUsername(), registerCommand.getPassword(), "user");
        return new ResponseEntity<>(authenticationAndGenerateToken(registerCommand.getUsername(), registerCommand.getPassword()), HttpStatus.OK);
    }

    private AuthResponse authenticationAndGenerateToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String userId = authentication.getAuthorities().stream().findFirst().get().toString();
        String token = jwtGenerator.generateToken(authentication, userId);
        return new AuthResponse(token, userId);
    }
}
