package wise.wisewomenhackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.controllers.commands.BalanceCommand;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.repository.BalanceRepository;

import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    public Optional<Balance> balance(Long userId) {
        return balanceRepository.findByUserId(userId);
    }

    public void saveOrUpdateBalance(BalanceCommand balanceCommand) {
        if (balanceRepository.findByUserId(balanceCommand.getUserId()).isPresent()) {
            balanceRepository.updateBalanceByUserId(balanceCommand.getUserId(), balanceCommand.getAmount());
        } else {
            Balance balance = new Balance(balanceCommand.getUserId(), balanceCommand.getAmount());
            balanceRepository.save(balance);
        }
    }
}
