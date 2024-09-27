import Exchange.MoneyExchanger;
import Modelo.Currency.Currency;
import Utils.PrinterUtils;

import java.util.Scanner;
import java.util.Map;

public class Main {

    Scanner scanner;
    MoneyExchanger exchanger;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.exchanger = new MoneyExchanger();
    }

    /**
     * Print a custom menu with all options
     * 
     * @return option selected
     */
    private int printMenu() {
        try {
            this.clearScreen();
            System.out.println("Welcome to Exchange Exchange");
            System.out.println("1. List available currencies");
            System.out.println("2. Exchange currency");
            System.out.println("3. Check available codes for currency");
            System.out.println("0. Exit");
            int option = scanner.nextInt();
            return option;
        } catch (Exception e) {
            return -1;
        } finally {
            this.scanner.nextLine(); // clear buffer + if error, clear buffer
            this.clearScreen();
        }
    }

    /**
     * Clear the screen
     */
    public void clearScreen() {
        try {
            // Set the TERM environment variable to support terminal commands
            ProcessBuilder pb = new ProcessBuilder("clear");
            pb.environment().put("TERM", "xterm");
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Unable to clean screen");
        }
    }

    /**
     * List all available currencies
     */
    public void listCurrencies() {
        Map<String, String> currencies = this.exchanger.fetchSupported();
        // print all currencies
        new PrinterUtils<String, String>().printMapKeyValue(currencies);
    }

    /**
     * Get available exchanges for a currency
     */
    public void getAvailableExchanges() {
        System.out.print("Currency code: ");
        String code = this.scanner.nextLine().trim();
        Currency currency = this.exchanger.getAvailableExchanges(code);
        System.out.println("Available exchanges for " + code);
        new PrinterUtils<String, Float>().printMapKeyValue(currency.getConversionRates());
    }

    public void exchange() {
        try {
            System.out.print("Initial currency code: ");
            String initialCurrencyCode = this.scanner.nextLine().trim().toUpperCase();
            System.out.print("Objective currency code: ");
            String objectiveCurrencyCode = this.scanner.nextLine().trim().toUpperCase();
            System.out.print("Value to convert: ");
            float value = this.scanner.nextFloat();
            float exchangedValue = this.exchanger.exchange(initialCurrencyCode,
                    objectiveCurrencyCode, value);
            System.out.printf("%1$f %2$s = %3$f %4$s\n", value, initialCurrencyCode, exchangedValue,
                    objectiveCurrencyCode);
        } catch (Exception e) {
            System.out.println("Unable to exchange currency, " + e.getMessage());
        } finally {
            this.scanner.nextLine(); // clear buffer
        }
    }

    /**
     * Run the main menu + call functions to make app work
     */
    public void run() {
        do {
            int option = this.printMenu();
            switch (option) {
                case 1:
                    this.listCurrencies();
                    break;
                case 2:
                    this.exchange();
                    break;
                case 3:
                    this.getAvailableExchanges();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.print("Invalid option. Please try again. ");

            }
            System.out.println("Press enter to continue...");
            this.scanner.nextLine();
        } while (true);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
