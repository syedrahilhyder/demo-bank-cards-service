package com.demo.bank.cards.api;

import com.demo.bank.cards.application.CardAuthorizationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardsController {

  private final CardAuthorizationService service;

  public CardsController(CardAuthorizationService service) {
    this.service = service;
  }

  @PostMapping("/authorize")
  public String authorize(@RequestParam String cardToken,
                          @RequestParam long amountMinor,
                          @RequestParam String currency) {
    return service.authorize(cardToken, amountMinor, currency);
  }
}
