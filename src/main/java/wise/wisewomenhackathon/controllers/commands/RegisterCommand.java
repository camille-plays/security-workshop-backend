package wise.wisewomenhackathon.controllers.commands;

import jakarta.validation.constraints.NotNull;

public class RegisterCommand {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
