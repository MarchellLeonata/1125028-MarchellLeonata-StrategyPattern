package view;
import model.PaymentStrategy;
import java.util.List;
import java.util.Scanner;

public class PaymentView {
    private Scanner scanner = new Scanner(System.in);

    public int showMenu() {

        System.out.println("\n===== PAYMENT SYSTEM =====");
        System.out.println("1. Lakukan Pembayaran");
        System.out.println("2. Tambah Metode Pembayaran");
        System.out.println("3. Lihat Metode Pembayaran");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");

        return scanner.nextInt();
    }

    public double inputAmount() {

        System.out.print("Masukkan jumlah pembayaran: Rp");

        return scanner.nextDouble();
    }

    public void showMethods(List<PaymentStrategy> methods) {

        System.out.println("\nMetode Pembayaran:");

        for (int i = 0; i < methods.size(); i++) {

            System.out.println(
                    (i + 1) + ". " + methods.get(i).getName()
            );
        }
    }

    public int choosePaymentMethod() {

        System.out.print("Pilih metode pembayaran: ");

        return scanner.nextInt();
    }

    public String inputAuthorization(String method) {

        if (method.equals("OTP")) {
            System.out.println("(OTP simulasi: 123456)");
        }

        System.out.print("Masukkan " + method + ": ");

        return scanner.next();
    }

    public int chooseNewMethod() {

        System.out.println("\nTambah Metode Pembayaran");
        System.out.println("1. Wallet");
        System.out.println("2. Debit Card");
        System.out.println("3. Credit Card");
        System.out.println("4. E-Wallet");
        System.out.print("Pilih: ");

        return scanner.nextInt();
    }

    public String inputText(String message) {

        System.out.print(message);

        return scanner.next();
    }

    public double inputDouble(String message) {

        System.out.print(message);

        return scanner.nextDouble();
    }

    public void showMessage(String message) {

        System.out.println(message);
    }
}
