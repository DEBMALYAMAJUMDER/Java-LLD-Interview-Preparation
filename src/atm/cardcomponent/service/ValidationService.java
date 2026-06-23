package atm.cardcomponent.service;

import atm.cardcomponent.entity.Card;

public interface ValidationService {
    boolean validateCard(Card card);
}
