package wise.wisewomenhackathon.controllers.commands;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferCommand {

    @NotNull
    private UUID sourceBalanceId;

    @NotNull
    private UUID destinationBalanceId;

    @NotNull
    private BigDecimal amount;

    public UUID getSourceBalanceId() {
        return sourceBalanceId;
    }

    public UUID getDestinationBalanceId() {
        return destinationBalanceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
