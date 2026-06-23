package atm.cardcomponent.service;

import atm.cardcomponent.entity.Card;

import java.util.concurrent.ConcurrentHashMap;

public interface CardService {
    void saveCard(Card card);

    boolean authenticate(Card card, String pin);

    void generateCardAccountLink(String cardNo, String accountNo);

    String fetchAccountNo(String cardNo);

    void deactivateCard(Card card, String Pin);

    void changePin(Card card, String oldPin, String newPin);
}
