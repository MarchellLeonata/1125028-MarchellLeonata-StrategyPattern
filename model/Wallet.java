package model;
import java.util.List;

public class Wallet implements PaymentStrategy {
    private String name;
    private double cashValue;
   
    public Wallet(String name, double cashValue){
        this.name = name;
        this.cashValue = cashValue;
    }


    @Override
    public boolean validate(double amount){
        return cashValue >= amount;
    }

    @Override 
    public void pay(double amount){
        cashValue -= amount;
    }
    @Override
    public List<String> getRequireAuthorization(){
        return List.of();
    }
    @Override 
    public boolean canPay(double amount){
        return amount >= 100 && amount <=1000000;
    }
    @Override 
    public String getAuthorizationValue(String method){
        return "";
    }
    @Override 
    public String getName(){
        return name + "( Cash)";
    }
}
