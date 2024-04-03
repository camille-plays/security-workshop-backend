package wise.wisewomenhackathon.controllers.response;

import lombok.Data;

@Data
public class AuthResponse {
    public String accessToken;
    public String tokenType;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
