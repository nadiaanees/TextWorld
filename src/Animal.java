import java.util.ArrayList;

public abstract class Animal {
    protected int health;
    protected Level.Room currentRoom;
    protected ArrayList<Item> inventory;
    protected String name;
    protected String description;
    protected String toPlayerDialogue;
    protected Level.Room playerRoom;

    public Animal(String name, String description, Level.Room currentRoom){
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
    }

    public void setPlayerRoom(Level.Room playerRoom) {
        this.playerRoom = playerRoom;
    }

    public Level.Room getPlayerRoom() {
        return playerRoom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public abstract void move();

    public void moveToRoom(Level.Room nextRoom){
        setCurrentRoom(nextRoom);
    }

    public Level.Room getLocation(){
         return currentRoom;
    }

    public void speak(String playerName){
        System.out.println(toPlayerDialogue);
    }

    public String getName() {
        return this.name;
    }
}
