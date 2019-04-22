import java.util.Scanner;

public class AddRoomCommand implements Command {

    Level level;
    Player player;
    String roomName;

    public AddRoomCommand(Player player) {
     this.player = player;
    }

    @Override
    public void init(String userString) {
        roomName = getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        String lastWord = "";
        String[] words = userString.split(" ");
        lastWord = words[words.length -1 ];
        return lastWord;
    }

    @Override
    public boolean execute() {

        Scanner in = new Scanner(System.in);
        System.out.println("type a description for your room");
        String userDescription = "";
        userDescription = in.next();

        level.addRoom(roomName, userDescription);
        level.addDirectedEdge(level.getRoom(roomName).getName(), roomName);


        return true;
    }
}
