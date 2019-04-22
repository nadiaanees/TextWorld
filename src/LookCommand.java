import java.util.ArrayList;

public class LookCommand implements Command {

    Player player;

    public LookCommand(Player player) {
        this.player = player;
    }

    @Override
    public void init(String userString) {
    }

    @Override
    public boolean execute() {
        Level.Room currentRoom = player.getCurrentRoom();
        String neighborNames = player.getCurrentRoom().getNeighborNames();
        System.out.println("You are currently in: " + currentRoom.getName() + ". You can go to " + neighborNames);
        return true;
    }
}
