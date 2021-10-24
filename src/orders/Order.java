package orders;

import menu.Item;
import menu.Menu;

import java.io.*;
import java.util.*;

public class Order implements Serializable {

    //Declarations
    private int orderID;
    private ArrayList<Item> orderLines;
    private Date orderTime;
    private int total;

    //Constructor
    public Order (int orderID, Date orderTime, ArrayList<Item> orderLines, int total) {
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.orderLines = orderLines;
        this.total = total;
    }

    @Override
    public String toString() {
        String lines = "\n";
        for (Item item : orderLines) {
            lines += String.format("  -%s, %d DKK\n", item.getName(), item.getPrice());
        }
        return String.format(" %03d. Ved %-1s %-1s %-4d DKK", orderID, orderTime.toString(), lines, total);
    }

    public int getOrderID() {
        return orderID;
    }

    public ArrayList<Item> getOrderLines() {
        return orderLines;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public int getTotal() {
        return total;
    }
}