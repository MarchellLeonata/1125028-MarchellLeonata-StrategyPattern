package model;
import java.util.List;
public interface  PaymentStrategy {
    boolean validate(double amount);
    void pay(double amount);
    List<String> getRequireAuthorization();
    boolean canPay (double amount);
    String getAuthorizationValue(String method);
    String getName();
}
