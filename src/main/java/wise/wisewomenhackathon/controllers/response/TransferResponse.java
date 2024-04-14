package wise.wisewomenhackathon.controllers.response;

import java.math.BigDecimal;

public class TransferResponse {

    public final BigDecimal newBalanceAmount;

    public TransferResponse(BigDecimal amount) {
        this.newBalanceAmount = amount;
    }
}
