import java.io.*;

public class Item implements Serializable {

    //Declarations
    private String name;
    private String description;
    private int price;

    //Constructor
    public Item (String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    //Print format for items
    public String printItem (int menuNumber) {
        return String.format("%-2d . %-20s\n%-35s%-2d DKK", menuNumber, name,description, price);
    }

    //Getters
    public String getName () {
        return name;
    }

    public String getDescription () {
        return description;
    }

    public int getPrice () {
        return price;
    }

    //Setters
    public void setName (String name) {
        this.name = name;
    }

    public void setDescription (String topping) {
        this.description = topping;
    }

    public void setPrice (int price) {
        this.price = price;
    }

}