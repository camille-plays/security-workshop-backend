package wise.wisewomenhackathon.IntTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wise.wisewomenhackathon.model.UserEntity;
import wise.wisewomenhackathon.service.BalanceService;
import wise.wisewomenhackathon.service.UserService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @BeforeEach
    public void cleanUp() {
        userService.deleteAll();
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private BalanceService balanceService;

    @Test
    public void testRegister() throws Exception {
        // Prepare login credentials
        String username = "testUser";
        String password = "testPassword";

        // Create a JSON payload for login request
        String requestBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

        // Perform POST request to /api/auth/login
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNoContent())
                .andReturn();

        // Check if the user was created
        UserEntity user = userService.user(username);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(balanceService.balance(user.getUserId())).isPresent();
    }
}
