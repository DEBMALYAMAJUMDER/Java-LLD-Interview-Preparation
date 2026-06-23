package atm.cardcomponent.service.impl;

import atm.cardcomponent.entity.Card;
import atm.cardcomponent.service.ValidationService;
import atm.util.AtmUtils;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validateCard(Card card) {
        if (card == null || card.getCardNo() == null
                || card.getAtmPin() == null || card.getExpDate() == null || !card.isActive()) {
            return false;
        }
        return AtmUtils.isValidYearMonth(card.getExpDate());
    }
}
