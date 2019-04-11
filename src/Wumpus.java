import java.util.ArrayList;

public class Wumpus extends Animal {

    public Wumpus(String name, String description, Level.Room currentRoom) {
        super(name, description, currentRoom);
    }

    @Override
    public void move() {
        ArrayList<Level.Room> neighbors = playerRoom.getNeighborList();
        int randIndex = (int)(Math.random() * neighbors.size());
        Level.Room nextRoom = neighbors.get(randIndex);
        if (neighbors.contains(currentRoom)) {
            currentRoom.removeAnimal(this);
            moveToRoom(nextRoom);
            currentRoom.addAnimal(this);
        }
    }

}
