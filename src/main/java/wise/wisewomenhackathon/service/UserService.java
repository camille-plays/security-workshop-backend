package wise.wisewomenhackathon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.model.UserEntity;
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

    @Autowired
    private BalanceService balanceService;

    public List<UserEntity> users() {
        return userRepository.findAll();
    }

    public UserEntity user(Long userId) {
        return userRepository.findByUserId(userId).orElseThrow();
    }

    @Transactional
    public UserEntity saveNewUserInitialiser(String username, String password, String type) {
        if (userRepository.existsByUsername(username)) {
            return userRepository.findByUsername(username).get();
        }
        // Generate a random salt
        //String salt = generateSalt();

        // Concatenate the salt with the plaintext password
        //String saltedPassword = password + salt;

        String hashedPassword = passwordEncoder.encode(password);

        UserEntity user = new UserEntity(username, hashedPassword);

        // Save the user
        userRepository.save(user);

        // Add balance for this user
        balanceService.internalInitialiseBalance(user.getUserId(), type);

        return user;
    }

    public String delete(Long userId) {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }

    public String deleteAll() {
        userRepository.deleteAll();
        return "All users deleted successfully";
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
