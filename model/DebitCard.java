package model;
import java.util.List;

public class DebitCard implements PaymentStrategy {
        private String cardNumber;
        private double balance;
        private String pin;

        public DebitCard(String cardNumber, double balance, String pin){
            this.cardNumber = cardNumber;
            this.balance = balance;
            this.pin= pin;
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
            return List.of("PIN");
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
                return "";
        }   

        @Override
        public String getName() {
            return "Debit Card " + cardNumber;
    }   
}
