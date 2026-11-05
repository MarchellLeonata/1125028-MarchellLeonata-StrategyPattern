package model;

public class OtpAuthorization implements AuthorizationStrategy{
    
    @Override
    public boolean authorize(String input, String expectedValue) {
        return input.equals(expectedValue);
    }
}
