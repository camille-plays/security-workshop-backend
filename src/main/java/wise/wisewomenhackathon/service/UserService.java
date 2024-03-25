package wise.wisewomenhackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.controllers.commands.UserCommand;
import wise.wisewomenhackathon.model.User;
import wise.wisewomenhackathon.repository.UserRepository;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> users() {
        return userRepository.findAll();
    }

    public User user(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public User save(UserCommand userCommand) {
        // Generate a random salt
        //String salt = generateSalt();

        // Concatenate the salt with the plaintext password
        //String saltedPassword = userCommand.getPassword() + salt;

        String hashedPassword = passwordEncoder.encode(userCommand.getPassword());

        User user = new User(userCommand.getUsername(), hashedPassword);

        // Save the user
        return userRepository.save(user);
    }

    public String delete(Long userId) {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }

    private String generateSalt() {
        // Create a byte array to hold the salt
        byte[] salt = new byte[16]; // 16 bytes = 128 bits

        // Generate a random salt using SecureRandom
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        // Encode the salt to Base64 to store it as a string
        return Base64.getEncoder().encodeToString(salt);
    }
}
