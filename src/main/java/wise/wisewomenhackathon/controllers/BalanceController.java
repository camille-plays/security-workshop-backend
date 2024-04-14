package wise.wisewomenhackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wise.wisewomenhackathon.Exceptions.BalanceNotFoundException;
import wise.wisewomenhackathon.controllers.commands.BalanceCommand;
import wise.wisewomenhackathon.controllers.response.BalanceResponse;
import wise.wisewomenhackathon.controllers.response.CharityResponse;
import wise.wisewomenhackathon.model.Balance;
import wise.wisewomenhackathon.service.BalanceService;
import wise.wisewomenhackathon.service.CharityService;
import wise.wisewomenhackathon.service.SecurityUtils;
import wise.wisewomenhackathon.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private CharityService charityService;

    @PostMapping(value = "/balances")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody(required = false) BalanceCommand balanceCommand) {
        // vulnerability: you can change your balance type to charity, then you will get displayed on other people's account as a recipient
        BalanceCommand balance = balanceCommand != null ? balanceCommand : new BalanceCommand();
        balanceService.saveOrUpdateBalance(securityUtils.getUserIdFromToken(), balance);
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<BalanceResponse> balance(@CookieValue Long userId) {
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
    public ResponseEntity<List<CharityResponse>> charities() {
        List<Balance> charities = balanceService.charities().orElseThrow(() -> new BalanceNotFoundException("No charity balances found"));
        List<CharityResponse> responses = charities.stream()
                .map(charity -> {
                    String username = userService.user(charity.getUserId()).getUsername();
                    return new CharityResponse(charity.getBalanceId(), username, charityService.getCharityDescription(username));
                })
                .toList();
        return ResponseEntity.ok().body(responses);
    }
}
