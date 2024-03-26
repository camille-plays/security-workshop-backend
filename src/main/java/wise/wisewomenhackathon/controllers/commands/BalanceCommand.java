package wise.wisewomenhackathon.controllers.commands;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class BalanceCommand {
    @NotNull
    private Long userId;

    @NotNull
    private BigDecimal amount;

    public Long userId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getUserId() {
        return userId;
    }

}
