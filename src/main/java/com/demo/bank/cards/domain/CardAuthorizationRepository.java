package com.demo.bank.cards.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardAuthorizationRepository
    extends MongoRepository<CardAuthorization, String> {}
