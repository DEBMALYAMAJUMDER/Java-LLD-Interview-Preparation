package atm.cardcomponent.entity;

public class Card {
    private String cardNo;
    private String accountNo;
    private String customerName;
    private String atmPin;
    private String expDate;
    private boolean isActive;
    private String type; //Visa,MasterCard Future use
    private String cvv; //Future use


    public Card(String cardNo, String accountNo, String customerName, String atmPin, String expDate) {
        this.cardNo = cardNo;
        this.accountNo = accountNo;
        this.customerName = customerName;
        this.atmPin = atmPin;
        this.expDate = expDate;
        this.isActive = true;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAtmPin() {
        return atmPin;
    }

    public void setAtmPin(String atmPin) {
        this.atmPin = atmPin;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNo='" + cardNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", atmPin='" + atmPin + '\'' +
                ", expDate='" + expDate + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
