import java.io.*;
import java.util.*;

public class Main {

    //Declarations
    private static int menuCount = 0;
    private static ArrayList<Item> menuList = new ArrayList<Item>();
    private static ArrayList<Item> orderLines = new ArrayList<Item>();
    private static ArrayList<Item> orderList = new ArrayList<Item>();
    private static ArrayList<Item> activeOrders = new ArrayList<Item>();

    public static void main(String[] args) {
        loadMenu();
        mainMenu();
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
            case 2:
                newOrder();
            case 3:
                activeOrders();
            case 4:
                allOrders();
            case 5:
                statistics();
            case 0:
                System.out.println("Afslutter program...");
                System.exit(0);
            default:
                System.out.println("Input ikke accepteret, prøv igen.");
                wait(2000);
                mainMenu();
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
        System.out.println("+-------------------------------------------+");
        //Prints menu
        for (int i = 0; i < menuList.size(); i++) {
            Item next = menuList.get(i);
            int menuNumber = i + 1;
            System.out.println();
            System.out.println(next.printItem(menuNumber));
        }
        //Switch for inputs
        Scanner console = IO.keyboard();
        int menuChoice = console.nextInt();
        switch (menuChoice) {
            case 1:
                addDish();
            case 2:
                removeDish();
            case 0:
                mainMenu();
            default:
                System.out.println("Input ikke accepteret, prøv igen.");
                wait(2000);
                menu();
        }
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
        Item newItem = new Item(name, description, price);
        menuList.add(newItem);
        menuCount++;
        //Messages user
        System.out.println("Retten er blevet tilføjet til menuen.");
        wait(2000);
        //Saves menu and returns
        saveMenu();
        menu();
    }

    //Saves menu to file
    public static void saveMenu () {
        try {
            FileOutputStream fos = new FileOutputStream("menu.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(menuCount);
            for (int i = 0; i < menuCount; i++) {
                oos.writeObject(menuList.get(i));
            }
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Loads menu from file
    public static void loadMenu () {
        try {
            FileInputStream fis = new FileInputStream("menu.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            menuCount = ois.readInt();
            for (int i = 0; i < menuCount; i++) {
                Item next = (Item) ois.readObject();
                menuList.add(next);
            }
            ois.close();
            fis.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Removes a dish from menu
    public static void removeDish () {
        //User interaction
        Scanner console = IO.keyboard();
        System.out.println("Indtast nummeret på den ret du vil fjerne:");
        int number = console.nextInt();
        //Removes dish from array list
        menuList.remove(number - 1);
        menuCount--;
        //Messages user
        System.out.println("Retten er blevet fjernet fra menuen.");
        wait(2000);
        //Saves menu and returns
        saveMenu();
        menu();
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

    public static void newOrder () {
        //Clears screen
        clearScreen();
        //Layout
        System.out.println("+---Ny ordre--------------------------------+");
        System.out.println("|                                           |");
        System.out.println("| Tryk 1 for at tilføje ret til ordren      |");
        System.out.println("| Tryk 2 for at bekræfte ordren             |");
        System.out.println("| Tryk 0 for hovedmenu                      |");
        System.out.println("|                                           |");
        System.out.println("+-------------------------------------------+");
        //Switch for user input
        Scanner console = IO.keyboard();
        int menuChoice = console.nextInt();
        switch (menuChoice) {
            case 1:
                addLine();
            case 2:
                if (orderLines.isEmpty()) {
                    System.out.println("Ordren er tom.");
                    wait(2000);
                    mainMenu();
                }
                else {
                    confirmOrder();
                }
            case 0:
                orderLines = null;
                System.out.println("Ordren er slettet.");
                wait(2000);
                mainMenu();
        }
    }

    public static void addLine () {
        Scanner console = IO.keyboard();
        System.out.println("Indtast nummeret på en retten:");
        int dish = console.nextInt();
        System.out.println("Indtast antal:");
        int amount = console.nextInt();
        for (int i = 1; i <= amount; i++) {
            orderLines.add(menuList.get(dish));
        }

    }

    public static void confirmOrder() {

    }

    public static void activeOrders () {

    }

    public static void allOrders () {

    }

    public static void statistics () {

    }

}