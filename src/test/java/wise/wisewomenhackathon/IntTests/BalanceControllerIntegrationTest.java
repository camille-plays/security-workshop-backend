package wise.wisewomenhackathon.IntTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wise.wisewomenhackathon.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BalanceControllerIntegrationTest {

    @BeforeEach
    public void cleanUp() {
        userService.deleteAll();
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void testGetBalanceWithoutToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/balance")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

/*    @Test
    public void testGetBalanceWithToken() throws Exception {
        // Prepare login credentials
        String username = "testUser";
        String password = "testPassword";

        // Create a JSON payload for register request
        String registerRequestBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

        // Perform POST request to /api/auth/register
        MvcResult registerResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestBody))
                .andExpect(status().isNoContent())
                .andReturn();

        // Extract the access token from the response
        String accessToken = registerResult.getResponse().getHeader("Set-Cookie");

        // Perform GET request to /api/v1/balance with the access token
        MvcResult balanceResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/balance")
                        .header("Cookie", accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert that the response body contains the expected balance
        String balanceResponseBody = balanceResult.getResponse().getContentAsString();
        assertThat(balanceResponseBody).contains("balanceId");
        assertThat(balanceResponseBody).contains("amount");
    }*/
}
