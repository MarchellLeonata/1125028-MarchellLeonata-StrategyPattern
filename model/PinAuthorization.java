package model;

public class PinAuthorization implements AuthorizationStrategy{

    @Override 
    public boolean authorize(String input, String expectedValue){
        return input.equals(expectedValue);
    }
    
}
