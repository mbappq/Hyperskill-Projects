package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static final Scanner scanner = new Scanner(System.in);
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    CoffeeMachine() {
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
    }

    @Override
    public String toString() {
        return "The coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                beans + " of coffee beans\n" +
                cups + " of disposable cups\n" +
                money + " of money\n";
    }

    private void processCommand(String command) {
        if ("buy".equals(command)) {
            buy();
        } else if ("fill".equals(command)) {
            fill();
        } else if ("take".equals(command)) {
            take();
        } else if ("remaining".equals(command)) {
            System.out.println(this);
        }
    }

    private void buy() {
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                "back - to main menu:\n> ");
        String input = scanner.nextLine();
        if ("back".equals(input)) {
            return;
        }
        int option = Integer.valueOf(input);
        int check = checkResources(option);
        switch (check) {
            case 0:
                System.out.println("I have enough resources, making you a coffee!");
                makeCoffee(option);
                break;
            case 1:
                System.out.println("Sorry, not enough water!");
                break;
            case 2:
                System.out.println("Sorry, not enough milk!");
                break;
            case 3:
                System.out.println("Sorry, not enough coffee beans!");
                break;
            case 4:
                System.out.println("Sorry, not enough cups!");
        }
    }

    private int checkResources(int choice) {
        if (cups >= 1) {
            switch (choice) {
                case 1: if (water < 250) return 1;
                        if (beans < 16) return 3;
                        break;
                case 2: if (water < 350) return 1;
                        if (milk < 75) return 2;
                        if (beans < 20) return 3;
                        break;
                case 3: if (water < 200) return 1;
                        if (milk < 100) return 2;
                        if (beans < 12) return 3;
                        break;
            }
            return 0;
        }
        return 4;
    }

    private void makeCoffee(int choice) {
        if (choice == 1) {
            water -= 250;
            beans -= 16;
            money += 4;
            cups--;
        } else if (choice == 2) {
            water -= 350;
            milk -= 75;
            beans -= 20;
            money += 7;
            cups--;
        } else if (choice == 3) {
            water -= 200;
            milk -= 100;
            beans -= 12;
            money += 6;
            cups--;
        }
    }

    private void fill() {
        System.out.print("Write how many ml of water do you want to add:\n> ");
        water += scanner.nextInt();
        System.out.print("Write how many ml of milk do you want to add:\n> ");
        milk += scanner.nextInt();
        System.out.print("Write how many grams of coffee beans do you want to add:\n> ");
        beans += scanner.nextInt();
        System.out.print("Write how many disposable cups of coffee do you want to add:\n> ");
        cups += scanner.nextInt();
    }

    private void take() {
        System.out.println("I gave you " + money);
        money = 0;
    }

    public static void main(String[] args) {
        CoffeeMachine m = new CoffeeMachine();

        while (true) {
            System.out.print("Write action (buy, fill, take, remaining, exit):\n> ");
            String command = scanner.nextLine();
            if ("exit".equals(command)) {
                break;
            }
            m.processCommand(command);
        }
    }
}
