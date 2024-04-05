package wise.wisewomenhackathon.controllers.response;

import lombok.Data;

@Data
public class AuthResponse {
    public String accessToken;
    public String tokenType;
    public String userId;

    public AuthResponse(String accessToken, String userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }
}
