import java.util.ArrayList;

public class Popstar extends Animal {

    public Popstar(String name, String description, Level.Room currentRoom) {
        super(name, description, currentRoom);
    }

    @Override
    public void move() {
        ArrayList<Level.Room> neighbors = playerRoom.getNeighborList();
        if (neighbors.contains(currentRoom)) {
            currentRoom.removeAnimal(this);
            moveToRoom(playerRoom);
            currentRoom.addAnimal(this);
        }
    }


}
