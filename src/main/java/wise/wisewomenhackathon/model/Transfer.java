package wise.wisewomenhackathon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private UUID transferId;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Column(name = "source_balance_id")
    @NotNull
    @Getter
    private UUID sourceBalanceId;

    @Column(name = "destination_balance_id")
    @NotNull
    @Getter
    private UUID destinationBalanceId;
}
