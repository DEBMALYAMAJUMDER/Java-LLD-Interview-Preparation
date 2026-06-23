package atm.cardcomponent.repository;

import atm.cardcomponent.entity.Card;
import atm.exception.InvalidCardDetailsException;
import atm.cardcomponent.service.ValidationService;
import atm.cardcomponent.service.impl.ValidationServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class CardRepository {
    private ConcurrentHashMap<String, Card> cardMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> cardAccountLink = new ConcurrentHashMap<>();
    private ValidationService validationService = new ValidationServiceImpl();

    public void linkAccount(String cardNo, String accountNo) {
        cardAccountLink.put(cardNo, accountNo);
        System.out.println("Link Successful, card No = " + cardNo + ", account No = " + accountNo);
    }

    public String fetchAccountNo(String cardNo) {
        if (!cardAccountLink.containsKey(cardNo)) {
            throw new InvalidCardDetailsException("Card is Not Link to any account,Card No : " + cardNo);
        }
        return cardAccountLink.get(cardNo);
    }

    public void deactivateCard(Card card, String pin) {
        if (authenticateCard(card, pin)) {
            card.setActive(false);
            cardMap.put(card.getCardNo(), card);
            System.out.println("Successfully Deactivated" + card);
        } else {
            throw new InvalidCardDetailsException("Authentication Failed" + card);
        }
    }

    public void changePin(Card card, String oldPin, String newPin) {
        if (authenticateCard(card, oldPin)) {
            String encode = Base64.getEncoder().encodeToString(newPin.getBytes(StandardCharsets.UTF_8));
            card.setAtmPin(encode);
            cardMap.put(card.getCardNo(), card);
            System.out.println("Atm pin is Successfully Updated" + card);
        }
    }

    public void saveCard(Card card) {
        if (validationService.validateCard(card)) {
            String encode = Base64.getEncoder()
                    .encodeToString(card.getAtmPin().getBytes(StandardCharsets.UTF_8));
            card.setAtmPin(encode); // In Spring Boot Application It'll be replaced by BCrypt
            cardMap.put(card.getCardNo(), card);
            System.out.println("Saving Card details in the system : " + card);
        } else {
            throw new InvalidCardDetailsException("Please Provide Valid Card Details");
        }
    }

    public boolean authenticateCard(Card card, String pin) {
        if (!cardMap.containsKey(card.getCardNo())) {
            throw new InvalidCardDetailsException("Please Check the card details, It's not registered");
        }
        if (validationService.validateCard(card)) {
            var cardNo = card.getCardNo();
            Card existingCard = cardMap.get(cardNo);
            var actualPin = new String(Base64.getDecoder().decode(existingCard.getAtmPin()));
            boolean isValidPin = pin.equals(actualPin);
            return isValidPin && card.getExpDate().equals(existingCard.getExpDate()) && card.getCustomerName().equals(existingCard.getCustomerName());
        }
        return false;
    }
}
