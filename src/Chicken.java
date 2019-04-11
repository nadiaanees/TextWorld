import java.util.ArrayList;

public class Chicken extends Animal {

    public Chicken(String name, String description, Level.Room currentRoom) {
        super(name, description, currentRoom);
    }

    @Override
    public void move() {
        ArrayList<Level.Room> neighbors = currentRoom.getNeighborList();
        int randIndex = (int)(Math.random() * neighbors.size());
        Level.Room newRoom = neighbors.get(randIndex);
        currentRoom.removeAnimal(this);
        moveToRoom(newRoom);
        currentRoom.addAnimal(this);
    }


}
