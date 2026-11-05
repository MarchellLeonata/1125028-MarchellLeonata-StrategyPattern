package model;

public class CcvAuthorization implements AuthorizationStrategy  {
    
    @Override
    public boolean authorize(String input, String expectedValue) {
        return input.equals(expectedValue);
    }
}
