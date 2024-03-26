package wise.wisewomenhackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.wisewomenhackathon.model.Balance;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, UUID> {

    Optional<Balance> findByUserId(Long userId);
}
