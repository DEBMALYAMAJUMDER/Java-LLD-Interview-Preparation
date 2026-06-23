package atm.cardcomponent.service.impl;

import atm.cardcomponent.entity.Card;
import atm.cardcomponent.repository.CardRepository;
import atm.cardcomponent.service.CardService;
import atm.exception.CardAuthenticationException;

public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.saveCard(card);
    }

    @Override
    public boolean authenticate(Card card, String pin) {
        return cardRepository.authenticateCard(card,pin);
    }

    @Override
    public void generateCardAccountLink(String cardNo, String accountNo) {
        cardRepository.linkAccount(cardNo, accountNo);
    }

    @Override
    public String fetchAccountNo(String cardNo) {
        return cardRepository.fetchAccountNo(cardNo);
    }

    @Override
    public void deactivateCard(Card card, String pin) {
        cardRepository.deactivateCard(card, pin);
    }

    @Override
    public void changePin(Card card, String oldPin, String newPin) {
        cardRepository.changePin(card, oldPin, newPin);
    }
}
