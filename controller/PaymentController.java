package controller;
import model.*;
import view.PaymentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentController {
    private PaymentView view;
    private PaymentProcessor processor;

    private List<PaymentStrategy> paymentMethods;

    public PaymentController(
            PaymentView view,
            PaymentProcessor processor) {

        this.view = view;
        this.processor = processor;

        paymentMethods = new ArrayList<>();
    }

    public void addPaymentMethod(PaymentStrategy method) {

        paymentMethods.add(method);
    }

    public void run() {

        boolean running = true;

        while (running) {

            int choice = view.showMenu();

            switch (choice) {

                case 1:
                    makePayment();
                    break;

                case 2:
                    addNewPaymentMethod();
                    break;

                case 3:
                    view.showMethods(paymentMethods);
                    break;

                case 0:
                    running = false;
                    view.showMessage("Program selesai.");
                    break;

                default:
                    view.showMessage("Pilihan tidak tersedia.");
            }
        }
    }

    private void makePayment() {

        double amount = view.inputAmount();

        if (amount <= 0) {
            view.showMessage("Nominal harus lebih dari 0.");
            return;
        }

        List<PaymentStrategy> availableMethods = new ArrayList<>();

        for (int i = 0; i < paymentMethods.size(); i++) {

            PaymentStrategy method = paymentMethods.get(i);

            if (method.canPay(amount)) {
                availableMethods.add(method);
            }
        }

        if (availableMethods.isEmpty()) {

            view.showMessage(
                    "Tidak ada metode pembayaran yang sesuai."
            );

            return;
        }

        view.showMethods(availableMethods);

        int choice = view.choosePaymentMethod();

        if (choice < 1 || choice > availableMethods.size()) {

            view.showMessage("Pilihan tidak valid.");

            return;
        }

        PaymentStrategy selectedMethod =
                availableMethods.get(choice - 1);

        Map<String, String> authorizationInputs = new HashMap<>();

        List<String> required =
                selectedMethod.getRequireAuthorization();

        for (int i = 0; i < required.size(); i++) {

            String method = required.get(i);

            String input = view.inputAuthorization(method);

            authorizationInputs.put(method, input);
        }

        try {

            processor.processPayment(
                    selectedMethod,
                    amount,
                    authorizationInputs
            );

            view.showMessage("Pembayaran berhasil!");

        } catch (IllegalArgumentException e) {

            view.showMessage("Pembayaran gagal: " + e.getMessage());
        }
    }

    private void addNewPaymentMethod() {

        int choice = view.chooseNewMethod();

        switch (choice) {

            case 1:

                String name = view.inputText("Nama Wallet: ");

                double cash = view.inputDouble("Saldo Wallet: ");

                addPaymentMethod(new Wallet(name, cash));

                view.showMessage("Wallet berhasil ditambahkan.");

                break;

            case 2:

                String debitNumber =
                        view.inputText("Nomor Debit Card: ");

                double debitBalance =
                        view.inputDouble("Saldo Debit: ");

                String debitPin =
                        view.inputText("PIN: ");

                addPaymentMethod(
                        new DebitCard(debitNumber, debitBalance, debitPin)
                );

                view.showMessage("Debit Card berhasil ditambahkan.");

                break;

            case 3:

                String creditNumber =
                        view.inputText("Nomor Credit Card: ");

                double limit =
                        view.inputDouble("Limit Kredit: ");

                String ccv =
                        view.inputText("CCV: ");

                String creditPin =
                        view.inputText("PIN: ");

                addPaymentMethod(
                        new CreditCard(
                                creditNumber,
                                limit,
                                ccv,
                                creditPin
                        )
                );

                view.showMessage("Credit Card berhasil ditambahkan.");

                break;

            case 4:

                String phone =
                        view.inputText("Nomor HP: ");

                double eBalance =
                        view.inputDouble("Saldo E-Wallet: ");

                String ePin =
                        view.inputText("PIN: ");

                addPaymentMethod(
                        new EWallet(phone, eBalance, ePin)
                );

                view.showMessage("E-Wallet berhasil ditambahkan.");

                break;

            default:

                view.showMessage("Pilihan tidak tersedia.");
        }
    }
}
