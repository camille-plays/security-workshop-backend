package wise.wisewomenhackathon.controllers.commands;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class BalanceCommand {

    @NotNull
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    private String type = "user";

    public String getType() {
        return type;
    }
}
