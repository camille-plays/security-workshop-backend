package wise.wisewomenhackathon.controllers.response;

import java.util.UUID;

public class CharityResponse {

    public final UUID balanceId;

    public final String name;

    public final String description;

    public CharityResponse(UUID balanceId, String name, String description) {
        this.balanceId = balanceId;
        this.name = name;
        this.description = description;
    }

}
