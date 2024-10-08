package wise.wisewomenhackathon.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.model.UserEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE Balance b SET b.amount = :amount, b.type = :type WHERE b.userId = :userId")
    void updateBalanceByUserId(Long userId, BigDecimal amount, String type);

    @Transactional
    @Modifying
    @Query("UPDATE Balance b SET b.amount = :amount WHERE b.balanceId = :balanceId")
    void updateBalanceByBalanceId(UUID balanceId, BigDecimal amount);

    Optional<Balance> findByUserId(Long userId);

    Optional<Balance> findByBalanceId(UUID balanceId);

    Optional<List<Balance>> findAllByType(String type);

}
