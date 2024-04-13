package wise.wisewomenhackathon.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.model.UserEntity;
import wise.wisewomenhackathon.service.BalanceService;
import wise.wisewomenhackathon.service.UserService;

@Service
public class DataInitializer {
    @Autowired
    private UserService userService;

    @Autowired
    private BalanceService balanceService;

    @PostConstruct
    public void initializeCharitiesAndUsers() {
        userService.saveNewUserInitialiser("Dog4Care", "password", "charity");
        userService.saveNewUserInitialiser("EduPriv", "password", "charity");
        userService.saveNewUserInitialiser("Green4ever", "password", "charity");
        userService.saveNewUserInitialiser("user1", "password", "user");
    }

    private void createBalanceForUser(UserEntity user) {
        balanceService.internalInitialiseBalance(user.getUserId(), "charity");
    }
}
