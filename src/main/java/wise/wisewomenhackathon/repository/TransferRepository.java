package wise.wisewomenhackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.model.Transfer;

import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {

    Transfer save(Transfer transfer);
}
