package orders;

import menu.Item;
import menu.Menu;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {

    private static final Orders instance = new Orders();

    private List<Order> processedOrders;
    private List<Order> activeOrders;
    private int currentOrderId;

    public List<Order> getActiveOrders() {
        return activeOrders;
    }

    public List<Order> getProcessedOrders() {
        return this.processedOrders;
    }

    private Orders() {
        processedOrders = new ArrayList<>();
        activeOrders = new ArrayList<>();
        loadOrders();
    }

    private void saveOrders() {
        try {
            FileOutputStream fileOut = new FileOutputStream("Orders.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        }
        catch (IOException ex) {
            System.out.println("Something went wrong when saving the menu: " + ex.getMessage());
        }
    }

    private void loadOrders() {
        try {
            FileInputStream fileIn = new FileInputStream("Orders.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Orders loadedOrders = (Orders)in.readObject();
            transferVariables(loadedOrders);
            in.close();
            fileIn.close();
        }
        catch (IOException | ClassNotFoundException ex) {
            System.out.println("Something went wrong when saving the menu: " + ex.getMessage());
        }
    }

    private void transferVariables(Orders loadedInstance) {
        this.activeOrders = loadedInstance.activeOrders;
        this.processedOrders = loadedInstance.processedOrders;
        this.currentOrderId = loadedInstance.currentOrderId;
    }

    public static Orders getInstance() {
        return instance;
    }

    public boolean hasActiveOrderID(int id) {
        for (Order order: activeOrders) {
            if (order.getOrderID() == id) {
                return true;
            }
        }
        return false;
    }

    public void removeActiveOrder(int id) {
        if (hasActiveOrderID(id)) {
            for (int i = 0; i < activeOrders.size(); i++) {
                if (activeOrders.get(i).getOrderID() == id) {
                    activeOrders.remove(i);
                    break;
                }
            }
        }
    }

    public void addActiveOrder(ArrayList<Item> orderLines, int total) {
        currentOrderId++;
        Instant.now();
        Order order = new Order(currentOrderId, Date.from(Instant.now()), orderLines, total);
        this.activeOrders.add(order);
        saveOrders();
    }

    public String printActiveOrders() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getActiveOrders().size(); i++) {
            builder.append(String.format("%-43s", getActiveOrders().get(i)));
            if (i != getActiveOrders().size()-1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
