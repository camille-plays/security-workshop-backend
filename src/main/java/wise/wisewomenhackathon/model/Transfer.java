package wise.wisewomenhackathon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transfers")
public class Transfer {

    public Transfer() {
    }

    public Transfer(BigDecimal amount, UUID sourceBalanceId, UUID destinationBalanceId) {
        this.amount = amount;
        this.sourceBalanceId = sourceBalanceId;
        this.destinationBalanceId = destinationBalanceId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private UUID transferId;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Column(name = "source_balance_id")
    @NotNull
    private UUID sourceBalanceId;

    @Column(name = "destination_balance_id")
    @NotNull
    private UUID destinationBalanceId;

    public UUID getTransferId() {
        return transferId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UUID getSourceBalanceId() {
        return sourceBalanceId;
    }

    public UUID getDestinationBalanceId() {
        return destinationBalanceId;
    }

}
