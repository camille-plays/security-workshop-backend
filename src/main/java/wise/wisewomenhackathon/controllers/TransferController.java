package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wise.wisewomenhackathon.controllers.commands.TransferCommand;
import wise.wisewomenhackathon.controllers.response.TransferResponse;
import wise.wisewomenhackathon.model.Transfer;
import wise.wisewomenhackathon.service.TransferService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping("/transfers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TransferResponse> save(@RequestBody TransferCommand transferCommand) {
        transferService.createTransfer(transferCommand);
        return ResponseEntity.ok().body(new TransferResponse(transferCommand.getAmount()));
    }

    @GetMapping("/transfers")
    public ResponseEntity<List<Transfer>> getTransfers() {
        List<Transfer> transfers = transferService.getTransfers();
        return ResponseEntity.ok(transfers);
    }

}
