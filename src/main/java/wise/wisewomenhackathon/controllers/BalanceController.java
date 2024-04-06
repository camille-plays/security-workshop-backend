package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wise.wisewomenhackathon.Exceptions.BalanceNotFoundException;
import wise.wisewomenhackathon.controllers.commands.BalanceCommand;
import wise.wisewomenhackathon.controllers.response.BalanceResponse;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.service.BalanceService;
import wise.wisewomenhackathon.service.SecurityUtils;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private SecurityUtils securityUtils;

    @PostMapping(value = "/balances")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody(required = false) BalanceCommand balanceCommand) {
        // vulnerability: you can change your balance type to charity, then you will get displayed on other people's account as a recipient
        BalanceCommand balance = balanceCommand != null ? balanceCommand : new BalanceCommand();
        balanceService.saveOrUpdateBalance(securityUtils.getUserIdFromToken(), balance);
    }

    @GetMapping(value = "/balance/{id}")
    public ResponseEntity<BalanceResponse> balance(@PathVariable(value = "id") Long userId) {
        // vulnerability: there is no authorization check here
        /*Long userIdFromToken = getUserIdFromToken();
        if (!userIdFromToken.equals(userId)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }*/
        Balance balance = balanceService.balance(userId).orElseThrow(() -> new BalanceNotFoundException("Balance not found for user ID: " + userId));
        return ResponseEntity.ok()
                .body(new BalanceResponse(balance.getBalanceId(), balance.getAmount()));
    }

    @GetMapping(value = "/charities")
    public ResponseEntity<List<BalanceResponse>> charities() {
        List<Balance> charities = balanceService.charities().orElseThrow(() -> new BalanceNotFoundException("No charity balances found"));
        return ResponseEntity.ok().body(charities.stream().map(charity -> new BalanceResponse(charity.getBalanceId(), charity.getAmount())).toList());
    }
}
