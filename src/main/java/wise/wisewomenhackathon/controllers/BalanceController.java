package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import wise.wisewomenhackathon.Exceptions.BalanceNotFoundException;
import wise.wisewomenhackathon.controllers.commands.BalanceCommand;
import wise.wisewomenhackathon.controllers.response.BalanceResponse;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.service.BalanceService;

@RestController
@RequestMapping(value = "/api/v1")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @PostMapping(value = "/balances")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody BalanceCommand balanceCommand) {
        balanceService.saveOrUpdateBalance(balanceCommand);
    }

    @GetMapping(value = "/balance/{id}")
    public ResponseEntity<BalanceResponse> balance(@PathVariable(value = "id") Long userId) {
        Long userIdFromToken = getUserIdFromToken();
        if (!userIdFromToken.equals(userId)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Balance balance = balanceService.balance(userId).orElseThrow(() -> new BalanceNotFoundException("Balance not found for user ID: " + userId));
        return ResponseEntity.ok()
                .body(new BalanceResponse(balance.getBalanceId(), balance.getAmount()));
    }

    private Long getUserIdFromToken() {
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authorities.stream().findFirst().isEmpty()) {
            throw new RuntimeException("Provided token does not contain authorities");
        }
        return Long.parseLong(authorities.stream().findFirst().get().toString());
    }
}
