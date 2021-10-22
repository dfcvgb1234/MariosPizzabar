import java.io.*;
import java.util.*;

public class Order implements Serializable {

    //Declarations
    private int orderID;
    private ArrayList<Item> orderLines;
    private int total;

    //Constructor
    public Order (int orderID, ArrayList<Item> orderLines, int total) {
        this.orderID = orderID;
        this.orderLines = orderLines;
        this.total = total;
    }

    //Print format for order
    public void printOrder () {

    }




}