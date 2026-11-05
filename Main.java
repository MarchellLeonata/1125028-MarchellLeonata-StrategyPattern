import model.*;
import view.PaymentView;
import controller.PaymentController;

public class Main {
    public static void main(String[] args) {

        PaymentView view = new PaymentView();

        AuthorizationService authorizationService =
                new AuthorizationService();

        PaymentProcessor processor =
                new PaymentProcessor(authorizationService);

        PaymentController controller =
                new PaymentController(view, processor);

        controller.addPaymentMethod(
                new Wallet("Wallet Utama", 10000)
        );

        controller.addPaymentMethod(
                new DebitCard("1234567890", 100000, "1234")
        );

        controller.addPaymentMethod(
                new CreditCard("9876543210", 1000000, "123", "4321")
        );

        controller.addPaymentMethod(
                new EWallet("081234567890", 500000, "1111")
        );

        controller.run();
    }

}
