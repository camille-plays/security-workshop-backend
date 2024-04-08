package wise.wisewomenhackathon.controllers.commands;

import java.math.BigDecimal;

public class BalanceCommand {

    private BigDecimal amount = new BigDecimal("10.0");

    public BigDecimal getAmount() {
        return amount;
    }

    private String type = "user";

    public String getType() {
        return type;
    }

    public BalanceCommand(BigDecimal amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public BalanceCommand() {
        this.amount = new BigDecimal("10.0");
        this.type = "user";
    }
}
