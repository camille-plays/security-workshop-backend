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
        userService.saveNewUserInitialiser("charity1", "password", "charity");
        userService.saveNewUserInitialiser("charity2", "password", "charity");
        userService.saveNewUserInitialiser("charity3", "password", "charity");
        userService.saveNewUserInitialiser("user1", "password", "user");
    }

    private void createBalanceForUser(UserEntity user) {
        balanceService.internalInitialiseBalance(user.getUserId(), "charity");
    }
}
