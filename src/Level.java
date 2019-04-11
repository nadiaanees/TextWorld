import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Level {
    private HashMap<String, Room> rooms;

    public Level() {
        rooms = new HashMap<String, Room>();
    }

    public static void init(Level g){
        Item pie = new Item("pie", "what is cream pie?");
        Item apple = new Item("apple", "this is a ripe apple, it's very healthy");
        Item dumbell = new Item("dumbell", "work out, get strong!");
        Item clock = new Item("clock", "tells you the time");
        Item shirt = new Item("shirt", "wear this so you are not naked, unless you want to be naked... but that's not socially acceptable...");

        ArrayList<Item> items = new ArrayList<>();
        items.add(pie);
        items.add(apple);
        items.add(dumbell);
        items.add(clock);

        g.addRoom("hall", "a vibrant hallway painted with yellow sunflowers");
        g.addRoom("closet", "a minimalist's closet with the basics");
        g.addRoom("dungeon", "a not at all scary room where rabbits and cheese can be found");
        g.addRoom("gym", "GeT those gains son!! the power is IN YOu!! DONT STOP UNTIL IT BURNS!!!");

        g.getRoom("closet").addItem(shirt);
        g.getRoom("hall").addItem(clock);
        g.getRoom("dungeon").addItem(apple);
        g.getRoom("gym").addItem(dumbell);

        Animal chicken = new Chicken("Joe the chicken", "plain chicken", g.getRoom("hall") );
        Animal chubbyChicken = new Chicken("Garry the chubby chicken", "a little squishy ", g.getRoom("closet"));
        Animal smallChicken = new Chicken("Latanya the small chicken", "tiny little guy", g.getRoom("gym"));
        Animal wumpus = new Wumpus("Kento the wumpus", "he's a lazy wumpus", g.getRoom("dungeon"));
        ArrayList<Animal> animalsList = new ArrayList<>();
        animalsList.add(chicken);
        animalsList.add(chubbyChicken);
        animalsList.add(smallChicken);
        animalsList.add(wumpus);

        g.getRoom("hall").addAnimal(chubbyChicken);
        g.getRoom("closet").addAnimal(wumpus);
        g.getRoom("dungeon").addAnimal(smallChicken);

        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("closet", "dungeon");
        g.addDirectedEdge("dungeon", "hall");
        g.addUndirectedEdge("dungeon", "gym");
        Player currentPlayer = new Player("Joe", "a nice man");
        currentPlayer.setCurrentRoom(g.getRoom("hall"));
        currentPlayer.setName("Joe");

        ArrayList<Item> playerItems = new ArrayList<>();
        currentPlayer.setItems(playerItems);
        currentPlayer.getDescription();
        Level.Room currentRoom = currentPlayer.getCurrentRoom();

        String response = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Your possible actions are: go <roomName>, look, add <roomName>, take <itemName>, drop <itemName>, or quit");

        do {
            //display the room and the exits
            System.out.println("you are currently in the " + currentPlayer.getCurrentRoom().getName());
            System.out.print("what do you want to do? > ");
            response = in.nextLine();
            //Command command = parseCommand(response);
            //command.execute();

            if (response.contains("go")) {
                String roomName = response.substring(3);
                Level.Room newRoom =  g.getRoom(roomName);
                if (currentPlayer.getCurrentRoom().getNeighborNames().contains(roomName)) {
                    currentPlayer.moveToRoom(newRoom);
                    currentRoom = newRoom;
                }
                else if(!(currentPlayer.getCurrentRoom().getNeighborNames().contains(roomName))){
                    System.out.println(roomName + " is not a neighbor of your current room:  " + currentRoom.getName());
                }
            }

            if (response.contains("drop")) {
                String itemName = response.substring(5);
                currentPlayer.removeItem(itemName);
                currentRoom.addItem(itemName);
                System.out.println("you have removed " + itemName + " from your inventory and added it to " + currentRoom.getName());

            } else if (response.contains("take")) {
                String itemName = response.substring(5);
                currentRoom.removeItem(itemName);
                currentPlayer.addItem(itemName);
                System.out.println("you have removed " + itemName + " from " + currentRoom.getName() + " and added it to your inventory.");

            } else if (response.contains("look")) {
                String msg = currentPlayer.getCurrentRoom().displayAnimalList();
                System.out.println("you can go to: " + currentRoom.getNeighborNames() + ". these are the items in your room: " + currentPlayer.getCurrentRoom().displayItems() +
                        ". these are the animals in your room: " + msg);

            } else if (response.contains("add")) {
                String name = response.substring(3);
                Scanner in2 = new Scanner(System.in);
                System.out.println("type a description for your room");
                String userDescription = "";
                userDescription = in.next();

                g.addRoom(name, userDescription);
                g.addDirectedEdge(currentRoom.getName(), name);
            } else if (response.contains("quit")) {
                System.out.println("buh-bye");
                break;

            } else {
                System.out.println("Your possible actions are: go <roomName>, look, add <roomName>, or quit");
            }
        }
        while (!response.equals("quit"));
        moveAllCreatures(animalsList);
    }


    private static void moveAllCreatures(ArrayList<Animal> animals) {
        for(Animal a: animals){
            a.move();
        }
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public static boolean isANeighbor(String response, Level.Room current) {
        String name = response.substring(3);
        if (current.getNeighborNames().contains(name)) {
            return true;
        }
        return false;
    }

    public void addRoom(String name, String description) {
        rooms.put(name, new Room(name));
        rooms.get(name).setDescription(description);

    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = this.getRoom(name1);
        Room n2 = this.getRoom(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = this.getRoom(name1);
        Room n2 = this.getRoom(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public class Room {
        private String name;
        private HashMap<String, Room> neighbors;
        private String description;
        private ArrayList<Item> items;
        public ArrayList<Animal> animals;

        public Room(String name) {
            this.name = name;
            neighbors = new HashMap<String, Room>();
            this.description = description;
            this.items = new ArrayList<Item>();
            this.animals = new ArrayList<Animal>();
        }

        public void setAnimals(ArrayList<Animal> animals){
            this.animals = animals;
        }

        public ArrayList<Animal> getAnimalList(){
            return animals;
        }
        public void removeAnimal(Animal animal){
            animals.remove(animal);
        }
        public void addAnimal(Animal animal){
            animals.add(animal);
        }

        public void setItems(ArrayList<Item> items){
            this.items = items;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public String displayItems() {
            String allItems = "";
            for (int i = 0; i < items.size(); i++) {
                allItems += items.get(i).getName()+ " ";
            }
            return allItems;
        }

        public String displayAnimalList() {
            String out = "";
            for (int i = 0; i < animals.size(); i++) {
                out += animals.get(i).getName() + ", ";
                if(out.substring(out.length()-1).equals(",")){
                    out.replaceAll(",", " ");
                }
            }
            return out;
        }

        public void addItem(String name, String description) {
            items.add(new Item(name, description));
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public void addItem(String name) {
            //is this correct ?
            items.add(new Item(name, getDescription()));
        }

        public Item removeItem(String name) {
            Item item = null;
            //if the room has an item with this name, then remove it
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(name)) {
                    items.remove(i);
                }
            }
            return item;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return this.name;
        }

        public void addNeighbor(Room n) {
            neighbors.put(n.getName(), n);
        }

        public String getNeighborNames() {
            String names = "";
            for (String name : neighbors.keySet()) {
                names += " " + name;
            }
            return names;
        }

        public ArrayList<Room> getNeighborList() {
            ArrayList<Room> rooms = new ArrayList<>();
            for (String room : neighbors.keySet()) {
                rooms.add(  neighbors.get(room));
            }
            return rooms;
        }


        public Room getNeighbor(String name) {
            return rooms.get(name);
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
