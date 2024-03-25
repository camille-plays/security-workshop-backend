package wise.wisewomenhackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.repository.BalanceRepository;

@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    public Balance balance(Long userId) {
        return balanceRepository.findByUserId(userId);
    }
}
