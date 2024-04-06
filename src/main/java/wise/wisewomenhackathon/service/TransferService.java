package wise.wisewomenhackathon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.Exceptions.BalanceNotFoundException;
import wise.wisewomenhackathon.Exceptions.InsufficientFoundsException;
import wise.wisewomenhackathon.controllers.commands.TransferCommand;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.model.Transfer;
import wise.wisewomenhackathon.repository.TransferRepository;

import java.math.BigDecimal;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    BalanceService balanceService;

    public void createTransfer(TransferCommand transferCommand) {
        // Keep no authorization on purpose as vulnerability
        Balance sourceBalance = balanceService.balance(transferCommand.getSourceBalanceId()).orElseThrow(() -> new BalanceNotFoundException("source balance not found"));
        Balance destinationBalance = balanceService.balance(transferCommand.getDestinationBalanceId()).orElseThrow(() -> new BalanceNotFoundException("destination balance not found"));
        if (sourceBalance.getAmount().compareTo(transferCommand.getAmount()) >= 0) {
            processTransfer(sourceBalance, destinationBalance, transferCommand.getAmount());
        } else {
            throw new InsufficientFoundsException("You dont have enough money in your balance to make this transfer");
        }
    }

    @Transactional
    protected void processTransfer(Balance sourceBalance, Balance destinationBalance, BigDecimal amount) {
        // vulnerability: balance can be increased with a negative value
        balanceService.updateBalanceAmount(sourceBalance.getBalanceId(), sourceBalance.getAmount().subtract(amount));
        balanceService.updateBalanceAmount(destinationBalance.getBalanceId(), destinationBalance.getAmount().add(amount));
        transferRepository.save(new Transfer(amount, sourceBalance.getBalanceId(), destinationBalance.getBalanceId()));
    }
}
