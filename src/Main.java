import java.io.*;
import java.util.*;

public class Main {

    //Declarations
    private static int menuCount = 0;
    private static ArrayList<Item> menuList = new ArrayList<Item>();
    private static ArrayList<String> orderLines = new ArrayList<String>();

    public static void main(String[] args) {
        loadMenu();
        menu0();
    }

    //Main menu
    public static void menu0 () {
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
                menu1();
            case 2:
                //newOrder();
            case 3:
                //activeOrders();
            case 4:
                //allOrders();
            case 5:
                //statistics();
            case 0:
                System.out.println("Afslutter program...");
                System.exit(0);
            default:
                System.out.println("Input ikke accepteret, prøv igen.");
                wait(2000);
                menu0();
        }
    }

    //Menu
    public static void menu1 () {
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
                menu0();
            default:
                System.out.println("Input ikke accepteret, prøv igen.");
                wait(2000);
                menu1();
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
        menu1();
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
        menu1();
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

    }

    public static void activeOrders () {

    }

    public static void allOrders () {

    }

    public static void statistics () {

    }

}