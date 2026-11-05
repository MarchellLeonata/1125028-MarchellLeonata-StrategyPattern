package model;

public interface AuthorizationStrategy {
    boolean authorize(String input, String expectedValue);
}
