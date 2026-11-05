package model;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationService {
    
    private Map<String, AuthorizationStrategy> strategies;

    public AuthorizationService() {

        strategies = new HashMap<>();

        strategies.put("PIN", new PinAuthorization());
        strategies.put("CCV", new CcvAuthorization());
        strategies.put("OTP", new OtpAuthorization());
    }

    public boolean authorize(
            String method,
            String input,
            String expectedValue) {

        AuthorizationStrategy strategy = strategies.get(method);

        if (strategy == null) {
            return false;
        }

        return strategy.authorize(input, expectedValue);
    }
}
