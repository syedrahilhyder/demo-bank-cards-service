package com.demo.bank.cards.application;

import com.demo.bank.cards.domain.*;
import com.demo.bank.events.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardAuthorizationService {

  private final CardAuthorizationRepository repo;
  private final KafkaTemplate<String, Object> kafka;

  public CardAuthorizationService(CardAuthorizationRepository repo,
                                  KafkaTemplate<String, Object> kafka) {
    this.repo = repo;
    this.kafka = kafka;
  }

  public String S_authorize(String cardToken, long amountMinor, String currency) {
    String authId = UUID.randomUUID().toString();

    CardAuthorization auth = new CardAuthorization(
        authId, cardToken, amountMinor, currency, "AUTHORIZED"
    );
    repo.save(auth);

    // Reuse PaymentInitiatedEvent for demo purposes
    PaymentInitiatedEvent event = new PaymentInitiatedEvent(
        authId, "CARD", "CARD_CUSTOMER", amountMinor, currency, "CARD-AUTH"
    );
    kafka.send(EventTopics.PAYMENTS_EVENTS, authId, event);

    return authId;
  }
}
