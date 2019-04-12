public class TakeCommand implements Command {

    Level level;
    String itemName;

    public TakeCommand(Level level){
        this.level = this.level;
    }

    @Override
    public void init(String userString) {
        this.itemName = getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        String lastWord = "";
        String[] words = userString.split(" ");
        lastWord = words[words.length -1 ];
        return lastWord;
    }

    @Override
    public boolean execute() {
        Player p = level.getPlayer();
        boolean success = p.takeItem(itemName);
        return success;

    }
}
