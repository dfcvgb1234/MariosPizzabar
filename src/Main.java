import menu.Item;
import menu.Menu;
import orders.Orders;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        activeOrders();
    }

    //Main menu
    public static void mainMenu () {
        //Clears screen
        clearScreen();
        //Layout
        System.out.println("+---Hovedmenu-------------------------------+");
        System.out.println("|                                           |");
        System.out.println("| Tryk 1 for at se menuen                   |");
        System.out.println("| Tryk 2 for at lave en ny ordre            |");
        System.out.println("| Tryk 3 for at se igangværende ordrer      |");
        System.out.println("| Tryk 4 for at se alle færdige ordrer      |");
        System.out.println("| Tryk 5 for at se statistik                |");
        System.out.println("| Tryk 0 for at afslutte                    |");
        System.out.println("|                                           |");
        System.out.println("+-------------------------------------------+");
        //Switch for inputs
        Scanner console = IO.keyboard();
        int menuChoice = console.nextInt();
        switch (menuChoice) {
            case 1:
                menu();
                break;
            case 2:
                newOrder();
                break;
            case 3:
                activeOrders();
                break;
            case 4:
                allOrders();
                break;
            case 5:
                statistics();
                break;
            case 0:
                System.out.println("Afslutter program...");
                System.exit(0);
                break;
            default:
                System.out.println("Input ikke accepteret, prøv igen.");
                wait(2000);
                mainMenu();
                break;
        }
    }

    //Menu
    public static void menu () {
        //Clears screen
        clearScreen();
        //Layout
        System.out.println("+---Menu------------------------------------+");
        System.out.println("|                                           |");
        System.out.println("| Tryk 1 for at tilføje en ret              |");
        System.out.println("| Tryk 2 for at fjerne en ret               |");
        System.out.println("| Tryk 0 for hovedmenu                      |");
        System.out.println("|                                           |");
        if (Menu.getInstance().getMenuItems().size() != 0) {
            System.out.println("+---List------------------------------------+");
            showMenu();
        }
        System.out.println("+-------------------------------------------+");
        //Switch for inputs
        Scanner console = IO.keyboard();
        int menuChoice = console.nextInt();
        switch (menuChoice) {
            case 1:
                addDish();
                break;
            case 2:
                removeDish();
                break;
            case 0:
                mainMenu();
                break;
            default:
                System.out.println("Input ikke accepteret, prøv igen.");
                wait(2000);
        }
        menu();
    }

    //Adds a dish to the menu
    public static void addDish () {
        //User interaction
        Scanner console = IO.keyboard();
        System.out.println("Indtast rettens navn:");
        String name = console.nextLine();
        System.out.println("Indtast en kort beskrivelse af retten:");
        String description = console.nextLine();
        System.out.println("Indtast rettens pris i DKK:");
        int price = console.nextInt();
        //Creates new object and adds to array list
        Menu.getInstance().addDish(new Item(name, description, price));
        //Messages user
        System.out.println("Retten er blevet tilføjet til menuen.");
        wait(2000);
    }

    //Removes a dish from menu
    public static void removeDish () {
        //User interaction
        Scanner console = IO.keyboard();
        System.out.println("Indtast nummeret på den ret du vil fjerne:");
        int number = console.nextInt();
        Menu.getInstance().removeDish(number-1);
        //Messages user
        System.out.println("Retten er blevet fjernet fra menuen.");
        wait(2000);
    }

    //Wait timer after wrong input or message
    public static void wait (int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Temporary screen clear
    public static void clearScreen () {
        //Prints 50 lines to simulate a cleared screen
        for (int i = 1; i <= 50; i++) {
            System.out.println();
        }
    }

    public static void showMenu() {
        System.out.println(Menu.getInstance().toString());
    }

    public static void newOrder () {
        ArrayList<Item> orderLines = new ArrayList<>();
        int totalAmount = 0;

        boolean orderComplete = false;

        while (!orderComplete) {
            //Clears screen
            clearScreen();
            //Layout
            System.out.println("+---Ny ordre--------------------------------+");
            System.out.println("|                                           |");
            System.out.println("| Tryk 1 for at tilføje ret til ordren      |");
            System.out.println("| Tryk 2 for at bekræfte ordren             |");
            System.out.println("| Tryk 0 for at komme tilbage               |");
            System.out.println("|                                           |");
            System.out.println("+-------------------------------------------+");
            //Switch for user input
            Scanner console = IO.keyboard();
            int menuChoice = console.nextInt();
            switch (menuChoice) {
                case 1:
                    List<Item> lines = addLines();
                    for (Item item : lines) {
                        totalAmount += item.getPrice();
                    }
                    orderLines.addAll(lines);
                    break;
                case 2:
                    if (orderLines.isEmpty()) {
                        System.out.println("Ordren er tom.");
                        wait(2000);
                    }
                    else {
                        confirmOrder(orderLines, totalAmount);
                        orderComplete = true;
                        return;
                    }
                    break;
                case 0:
                    wait(2000);
                    return;
                default:
                    System.out.println("Input ikke accepteret, prøv igen.");
                    wait(2000);
                    break;
            }
        }
        newOrder();
    }

    public static List<Item> addLines () {
        List<Item> lines = new ArrayList<>();
        Scanner console = IO.keyboard();
        System.out.println("Indtast nummeret på en retten:");
        int dish = console.nextInt();
        System.out.println("Indtast antal:");
        int amount = console.nextInt();
        for (int i = 1; i <= amount; i++) {
            lines.add(Menu.getInstance().getMenuItems().get(dish-1));
        }
        return lines;
    }

    public static void confirmOrder(ArrayList<Item> orderLines, int total) {
        Orders.getInstance().addActiveOrder(orderLines, total);
    }

    public static void activeOrders () {
        if (Orders.getInstance().getActiveOrders().size() != 0) {
            System.out.println("+---Ordre-----------------------------------+");
            showOrders();
        }
        System.out.println("+---Handlinger------------------------------+");
        System.out.println("|                                           |");
        System.out.println("| Tryk 1 for at tilføje en ordre            |");
        System.out.println("| Tryk 2 for at fjerne en ordre             |");
        System.out.println("| Tryk 0 for hovedmenu                      |");
        System.out.println("|                                           |");
        System.out.println("+-------------------------------------------+");

        //Switch for user input
        Scanner console = IO.keyboard();
        int menuChoice = console.nextInt();
        switch (menuChoice) {
            case 1:
                newOrder();
                break;
            case 2:
                if (Orders.getInstance().getActiveOrders().isEmpty()) {
                    System.out.println("Der er ikke nogen aktive ordrer");
                    wait(1000);
                }
                else {
                    removeOrder();
                }
                break;
            case 0:
                wait(1000);
                mainMenu();
                break;
            default:
                System.out.println("Input ikke accepteret, prøv igen.");
                wait(2000);
                break;
        }
        activeOrders();
    }

    public static void showOrders() {
        System.out.println(Orders.getInstance().printActiveOrders());
    }

    public static void removeOrder() {
        Scanner console = IO.keyboard();
        System.out.println("Indtast id på den ordre du gerne vil have fjernet:");
        int id = console.nextInt();

        if (Orders.getInstance().hasActiveOrderID(id)) {
            Orders.getInstance().removeActiveOrder(id);
        }

    }

    public static void allOrders () {

    }

    public static void statistics () {

    }

}