package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wise.wisewomenhackathon.controllers.response.BalanceResponse;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.service.BalanceService;

@RestController
@RequestMapping(value = "/api/v1")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping(value = "/balance/{id}")
    public ResponseEntity<BalanceResponse> balance(@PathVariable(value = "id") Long userId) {
        Balance balance = balanceService.balance(userId);
        return ResponseEntity.ok()
                .body(new BalanceResponse(balance.getUUID(), balance.getAmount()));
    }
}
