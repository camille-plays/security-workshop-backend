package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wise.wisewomenhackathon.controllers.commands.TransferCommand;
import wise.wisewomenhackathon.service.TransferService;

@RestController
@RequestMapping(value = "/api/v1")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping("/transfers")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody TransferCommand transferCommand) {
        transferService.createTransfer(transferCommand);
    }

}
