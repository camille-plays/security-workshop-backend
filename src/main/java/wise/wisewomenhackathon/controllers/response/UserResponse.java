package wise.wisewomenhackathon.controllers.response;

import lombok.Value;

@Value
public class UserResponse {

    public final String username;

    public UserResponse(String username) {
        this.username = username;
    }
}
