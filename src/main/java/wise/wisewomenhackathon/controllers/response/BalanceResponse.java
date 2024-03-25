package wise.wisewomenhackathon.controllers.response;

import java.math.BigDecimal;
import java.util.UUID;

public class BalanceResponse {

    public final UUID balanceId;

    public final BigDecimal amount;

    public BalanceResponse(UUID balanceId, BigDecimal amount) {
        this.balanceId = balanceId;
        this.amount = amount;
    }
}
