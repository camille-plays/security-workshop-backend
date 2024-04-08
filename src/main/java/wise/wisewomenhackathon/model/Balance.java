package wise.wisewomenhackathon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "balances")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private UUID balanceId;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Column(name = "user_id")
    @NotNull
    @Getter
    private Long userId;

    @Column(name = "type")
    @Getter
    private String type;

    public Balance() {
    }

    public Balance(Long userId, BigDecimal amount, String type) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
    }

    public UUID getBalanceId() {
        return this.balanceId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Long getUserId() {return this.userId;}

}
