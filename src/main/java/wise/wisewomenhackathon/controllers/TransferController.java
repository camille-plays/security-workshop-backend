package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wise.wisewomenhackathon.controllers.commands.TransferCommand;
import wise.wisewomenhackathon.controllers.response.TransferResponse;
import wise.wisewomenhackathon.model.Transfer;
import wise.wisewomenhackathon.service.BalanceService;
import wise.wisewomenhackathon.service.TransferService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TransferController {

    @Autowired
    TransferService transferService;

    @Autowired
    BalanceService balanceService;

    @PostMapping("/transfers")
    public ResponseEntity<TransferResponse> save(@RequestBody TransferCommand transferCommand) {
        return ResponseEntity.ok().body(new TransferResponse(
                transferService.createTransfer(transferCommand))
        );
    }

    @GetMapping("/transfers")
    public ResponseEntity<List<Transfer>> getTransfers() {
        List<Transfer> transfers = transferService.getTransfers();
        return ResponseEntity.ok(transfers);
    }

}
