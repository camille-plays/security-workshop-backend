package wise.wisewomenhackathon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "balances")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid")
    private UUID balanceId;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Column(name = "user_id")
    @NotNull
    private Long userId;

    public Balance() {
    }

    public UUID getUUID() {
        return this.balanceId;
    }

    public BigDecimal getAmount() { return this.amount; }

}
