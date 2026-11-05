package model;
import java.util.List;

public class CreditCard implements PaymentStrategy {
    private String cardNumber;
    private double limit;
    private double currentDebt;
    private String ccv;
    private String pin;

    public CreditCard(String cardNumber, double limit, double currentDebt, String ccv, String pin){
        this.cardNumber = cardNumber;
        this.limit = limit;
        this.currentDebt = currentDebt;
        this.ccv = ccv;
        this.pin = pin;
    }

    @Override
    public boolean validate(double amount) {
        return currentDebt + amount <= limit;
    }

    @Override
    public void pay(double amount) {
        currentDebt += amount;
    }

    @Override
    public List<String> getRequireAuthorization() {
        return List.of("CCV", "PIN");
    }

    @Override
    public boolean canPay(double amount) {
        return amount >= 100000 && amount <= 100000000;
    }

    @Override
    public String getAuthorizationValue(String method) {

        if (method.equals("CCV")) {
            return ccv;
        }

        if (method.equals("PIN")) {
            return pin;
        }

        return "";
    }

    @Override
    public String getName() {
        return "Credit Card " + cardNumber;
    }
    
}
