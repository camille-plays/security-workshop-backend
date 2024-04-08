package wise.wisewomenhackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.Exceptions.BalanceLimitReachedException;
import wise.wisewomenhackathon.Exceptions.BalanceNotFoundException;
import wise.wisewomenhackathon.Exceptions.IncorrectBalanceTypeException;
import wise.wisewomenhackathon.Exceptions.UserIdNotFoundException;
import wise.wisewomenhackathon.config.CharityConfiguration;
import wise.wisewomenhackathon.controllers.commands.BalanceCommand;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.repository.BalanceRepository;
import wise.wisewomenhackathon.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<Balance> balance(Long userId) {
        return balanceRepository.findByUserId(userId);
    }

    public Optional<List<Balance>> charities() {
        return balanceRepository.findAllByType("charity");
    }

    public Optional<Balance> balance(UUID balanceId) {
        return balanceRepository.findByBalanceId(balanceId);
    }

    public void internalInitialiseBalance(Long userId, String type) {
        if (userRepository.findByUserId(userId).isPresent()) {
            balanceRepository.save(new Balance(userId, new BigDecimal("10.0"), type));
        } else {
            throw new UserIdNotFoundException();
        }
    }


    public void saveOrUpdateBalance(Long userId, BalanceCommand balanceCommand) {
        validateBalance(balanceCommand);
        if (balanceRepository.findByUserId(userId).isPresent()) {
            balanceRepository.updateBalanceByUserId(userId, balanceCommand.getAmount(), balanceCommand.getType());
        } else {
            Balance balance = new Balance(userId, balanceCommand.getAmount(), balanceCommand.getType());
            balanceRepository.save(balance);
        }
    }

    public void updateBalanceAmount(UUID balanceId, BigDecimal amount) {
        if (balanceRepository.findByBalanceId(balanceId).isPresent()) {
            balanceRepository.updateBalanceByBalanceId(balanceId, amount);
        } else {
            throw new BalanceNotFoundException("Balance not found while updating founds");
        }
    }

    private void validateBalance(BalanceCommand balanceCommand) {
        if (!("user".equals(balanceCommand.getType()) || "charity".equals(balanceCommand.getType()))) {
            throw new IncorrectBalanceTypeException("Invalid balance type");
        }
        if (new BigDecimal("100").compareTo(balanceCommand.getAmount()) < 0) {
            throw new BalanceLimitReachedException("balance limit is 100");
        }
    }
}
