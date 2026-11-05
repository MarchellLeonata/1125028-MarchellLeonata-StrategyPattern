package model;
import java.util.List;

public class EWallet implements PaymentStrategy {
    private String phoneNumber;
    private double balance;
    private String pin;

    public EWallet(String phoneNumber, double balance, String pin) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.pin = pin;
    }

     @Override
    public boolean validate(double amount) {
        return balance >= amount;
    }

    @Override
    public void pay(double amount) {
        balance -= amount;
    }

    @Override
    public List<String> getRequireAuthorization() {
        return List.of("PIN", "OTP");
    }

    @Override
    public boolean canPay(double amount) {
        return amount > 0 && amount <= 10000000;
    }

    @Override
    public String getAuthorizationValue(String method) {

        if (method.equals("PIN")) {
            return pin;
        }

        if (method.equals("OTP")) {
            return "123456";
        }

        return "";
    }

    @Override
    public String getName() {
        return "E-Wallet " + phoneNumber;
    }
}
