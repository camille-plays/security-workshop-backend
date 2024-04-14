package wise.wisewomenhackathon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.Exceptions.AuthorizationFlagException;
import wise.wisewomenhackathon.Exceptions.BalanceNotFoundException;
import wise.wisewomenhackathon.Exceptions.InsufficientFoundsException;
import wise.wisewomenhackathon.controllers.commands.TransferCommand;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.model.Transfer;
import wise.wisewomenhackathon.repository.TransferRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    BalanceService balanceService;

    @Autowired
    private SecurityUtils securityUtils;

    public BigDecimal createTransfer(TransferCommand transferCommand) {
        // Keep no authorization on purpose as vulnerability
        // vulnerability: transfer does not check balance limit of 100
        Balance sourceBalance = balanceService.balance(transferCommand.getSourceBalanceId()).orElseThrow(() -> new BalanceNotFoundException("source balance not found"));
        Balance destinationBalance = balanceService.balance(transferCommand.getDestinationBalanceId()).orElseThrow(() -> new BalanceNotFoundException("destination balance not found"));
        if (sourceBalance.getAmount().compareTo(transferCommand.getAmount()) >= 0) {
            processTransfer(sourceBalance, destinationBalance, transferCommand.getAmount());
            // this check is done after processTransfer on purpose to see the impact of the exploit
            if (violatesAuthorization(sourceBalance.getBalanceId())) {
                System.out.println("Authorization flag triggered");
                throw new AuthorizationFlagException("Well done! The secret flag is: w1s3_w0m3n_c0d3");
            }
            return sourceBalance.getAmount().subtract(transferCommand.getAmount());
        } else {
            throw new InsufficientFoundsException("You dont have enough money in your balance to make this transfer");
        }
    }

    protected void processTransfer(Balance sourceBalance, Balance destinationBalance, BigDecimal amount) {
        // vulnerability: balance can be increased with a negative value
        balanceService.updateBalanceAmount(sourceBalance.getBalanceId(), sourceBalance.getAmount().subtract(amount));
        System.out.println("updating source balance amount to " + sourceBalance.getAmount().subtract(amount) + " at the time of transfer");
        balanceService.updateBalanceAmount(destinationBalance.getBalanceId(), destinationBalance.getAmount().add(amount));
        System.out.println("updating destination balance amount to " + sourceBalance.getAmount().add(amount) + " at the time of transfer");
        transferRepository.save(new Transfer(amount, sourceBalance.getBalanceId(), destinationBalance.getBalanceId()));
    }

    public List<Transfer> getTransfers() {
        return transferRepository.findAll();
    }

    private boolean violatesAuthorization(UUID balanceId) {
        Long userId = securityUtils.getUserIdFromToken();
        System.out.println("userId from token is " + userId);
        Balance userBalance = balanceService.balance(userId)
                .orElseThrow(() -> new BalanceNotFoundException("balance not found for current user"));
        return !userBalance.getBalanceId().equals(balanceId);
    }
}
