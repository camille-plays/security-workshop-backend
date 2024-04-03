package wise.wisewomenhackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.Exceptions.BalanceNotFoundException;
import wise.wisewomenhackathon.controllers.commands.TransferCommand;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.repository.TransferRepository;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    BalanceService balanceService;

    public void createTransfer(TransferCommand transferCommand) {
        Balance sourceBalance = balanceService.balance(transferCommand.getSourceBalanceId()).orElseThrow(() -> new BalanceNotFoundException("source balance not found"));
        Balance destinationBalance = balanceService.balance(transferCommand.getDestinationBalanceId()).orElseThrow(() -> new BalanceNotFoundException("destination balance not found"));
        if (sourceBalance.getAmount().compareTo(transferCommand.getAmount()) >= 0) {

        }

    }
}
