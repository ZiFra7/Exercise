import java.util.Scanner;

enum Drinks {
    COKE_COLA("Coke Cola", 1.50),
    SPRITE("Sprite", 1.25),
    FANTA("Fanta", 1.30);

    private final String name;
    private final double price;

    Drinks(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static void showMenu() {
        System.out.println("Available drinks:");
        for (Drinks drink : Drinks.values()) {
            System.out.printf("%s - $%.2f%n", drink.getName(), drink.getPrice());
        }
    }
}

enum Money {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10),
    TWENTY_FIVE(25),
    FIFTY(50),
    HUNDRED(100);

    private final int denomination;

    Money(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}

public class VendingMachine {
    public void purchaseDrink(Drinks drink, double amountPaid) {
        try {
            if (amountPaid < 0) {
                throw new IllegalArgumentException("Amount cannot be negative");
            }
            if (amountPaid < drink.getPrice()) {
                throw new InsufficientFundsException("Not enought money to purchase " + drink.getName());
            }

            double change = amountPaid - drink.getPrice();
            double actualChange = calculateChange(change);

            System.out.printf("Purchased %s. Change returned: $%.2f%n", drink.getName(), actualChange);
        } catch (IllegalArgumentException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private double calculateChange(double change) {
        double roundedChange = Math.floor(change);
        System.out.printf("Cannot return exact change of $%.2f, returning $%.2f instead.%n", change, roundedChange);
        return roundedChange;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();

        Drinks.showMenu();
        System.out.print("Choose a drink(COKE, SPRITE, FANTA): ");
        String choice = scanner.next().toUpperCase();

        try {
            Drinks selectedDrink = Drinks.valueOf(choice);
            System.out.printf("Insert money to buy %s ($%.2f)", selectedDrink.getName(), selectedDrink.getPrice());
            double amountPaid = scanner.nextDouble();

            vendingMachine.purchaseDrink(selectedDrink, amountPaid);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid choice of input");
        }
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}


