import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Scanner;

public class Main {
    public static HashMap<String, Command> commands;
    public static Player player;
    private static Level level;

    public static void main(String[] args) {
        Level g = new Level();
        Level.initLevel(g);
        initCommands();

        String response = "";
        Scanner in = new Scanner(System.in);
        // System.out.println("Your possible actions are: go <roomName>, look, add <roomName>, take <itemName>, drop <itemName>, or quit");
        response = in.nextLine();
        Command command = lookupCommand(response);
        command.execute();
    }

    private static Command lookupCommand(String response) {
        for(String a: commands.keySet()){
            if (a.equals(response)) {
                return commands.get(a);
            }
        }
        return null;
    }

    private static void initCommands() {
        commands.put("take", new TakeCommand(level));
        commands.put("look", new LookCommand(player));
        commands.put("add ", new AddRoomCommand(player));
        commands.put("go ", new GoCommand(player));

    }

}
