import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

    String name, description;
    ArrayList<Item> items;
    Level.Room currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItem(String itemName) {
        //what is the description i put in here??
        Item item = new Item(itemName, getDescription());
        items.add(item);
    }

    public Item removeItem(String name) {
        Item item = null;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
            }
        }
        return item;
    }

    public boolean destroyItem(String name) {
        Item item = null;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
            }
        }
        return items.remove(item);
    }

    public void displayInventory() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom) {
        currentRoom = newRoom;
    }

    public boolean moveToRoom(Level.Room room) {
        if (currentRoom.getNeighborNames().contains(room.getName())) {
            setCurrentRoom(room);
            return true;
        }
        return false;
    }

    public boolean moveToRoom(String roomName) {
        Level g = new Level();
        Level.Room newRoom = g.getRoom(roomName);
        if (currentRoom.getNeighborNames().contains(newRoom.getName())) {
            setCurrentRoom(newRoom);
            return true;
        }
        return false;
    }
}



