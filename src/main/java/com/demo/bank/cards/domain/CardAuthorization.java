package com.demo.bank.cards.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "card_authorizations")
public class CardAuthorization {

  @Id
  private String id;

  private String cardToken;
  private long amountMinor;
  private String currency;
  private String status;
  private Instant createdAt;

  public CardAuthorization() {}

  public CardAuthorization(String id, String cardToken,
                           long amountMinor, String currency, String status) {
    this.id = id;
    this.cardToken = cardToken;
    this.amountMinor = amountMinor;
    this.currency = currency;
    this.status = status;
    this.createdAt = Instant.now();
  }

  public String getId() { return id; }
  public String getStatus() { return status; }
}
