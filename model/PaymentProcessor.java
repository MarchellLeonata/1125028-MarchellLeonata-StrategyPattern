package model;

import java.util.List;
import java.util.Map;

public class PaymentProcessor {
    
    private AuthorizationService authorizationService;

    public PaymentProcessor(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    public void processPayment(
            PaymentStrategy paymentStrategy,
            double amount,
            Map<String, String> authorizationInputs) {

        if (!paymentStrategy.canPay(amount)) {
            throw new IllegalArgumentException(
                    "Nominal pembayaran tidak sesuai.");
        }

        if (!paymentStrategy.validate(amount)) {
            throw new IllegalArgumentException(
                    "Saldo tidak cukup atau limit terlampaui.");
        }

        List<String> requiredAuthorizations =
                paymentStrategy.getRequireAuthorization();

        for (int i = 0; i < requiredAuthorizations.size(); i++) {

            String method = requiredAuthorizations.get(i);

            String input = authorizationInputs.get(method);

            String expectedValue =
                    paymentStrategy.getAuthorizationValue(method);

            if (!authorizationService.authorize(
                    method, input, expectedValue)) {

                throw new IllegalArgumentException(
                        "Otorisasi " + method + " salah.");
            }
        }

        paymentStrategy.pay(amount);
    }
}
