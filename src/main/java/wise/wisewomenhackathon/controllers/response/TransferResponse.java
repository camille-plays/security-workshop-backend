package wise.wisewomenhackathon.controllers.response;

import java.math.BigDecimal;

public class TransferResponse {

    private final BigDecimal amount;

    public TransferResponse(BigDecimal amount) {
        this.amount = amount;
    }
}
