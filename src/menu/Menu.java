package menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Menu class with a singleton design pattern.
 *  Singleton means only a single instance of the class is permitted.
 *  Since we only need one menu, this is a good use case for singleton.
 */

public class Menu implements Serializable {

    private List<Item> menuItems;

    // Field accessible from a static context, with a new instance of the menu class.
    private static final Menu instance = new Menu();

    // Private constructor to prevent other classes from creating new instances.
    private Menu() {
        menuItems = new ArrayList<>();
        loadMenu();
    }

    // Getter for instance, the only way to access the object from another class.
    public static Menu getInstance() {
        return instance;
    }

    public List<Item> getMenuItems() {
        return this.menuItems;
    }

    public Item getMenuItem(int index) {
        return this.menuItems.get(index);
    }

    public void removeDish(int index) {
        menuItems.remove(index);
        saveMenu();
    }

    public void addDish(Item item) {
        menuItems.add(item);
        saveMenu();
    }

    private void saveMenu() {
        try {
            FileOutputStream fileOut = new FileOutputStream("menu.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        }
        catch (IOException ex) {
            System.out.println("Something went wrong when saving the menu: " + ex.getMessage());
        }
    }

    private void loadMenu() {
        try {
            FileInputStream fileIn = new FileInputStream("menu.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Menu loadedMenu = (Menu)in.readObject();
            transferVariables(loadedMenu);
            in.close();
            fileIn.close();
        }
        catch (IOException | ClassNotFoundException ex) {
            System.out.println("Something went wrong when saving the menu: " + ex.getMessage());
        }
    }

    private void transferVariables(Menu menu) {
        this.menuItems = menu.getMenuItems();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("|%-12s|%-42s|%-11s|\n", "Navn", "Beskrivelse", "Pris"));
        builder.append("|------------|------------------------------------------|-----------|\n");
        for (int i = 0; i < getMenuItems().size(); i++) {
            builder.append(String.format("%s", getMenuItem(i)));
            if (i != getMenuItems().size()-1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
