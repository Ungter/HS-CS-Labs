/*
 *                                          ,----, 
                                       ,/   .`| 
    ,---,.     ,---,.     ,---,.     ,`   .'  : 
  ,'  .' |   ,'  .' |   ,'  .' |   ;    ;     / 
,---.'   | ,---.'   | ,---.'   | .'___,/    ,'  
|   |   .' |   |   .' |   |   .' |    :     |   
:   :  :   :   :  |-, :   :  |-, ;    |.';  ;   
:   |  |-, :   |  ;/| :   |  ;/| `----'  |  |   
|   :  ;/| |   :   .' |   :   .'     '   :  ;   
|   |   .' |   |  |-, |   |  |-,     |   |  '   
'   :  '   '   :  ;/| '   :  ;/|     '   :  |   
|   |  |   |   |    \ |   |    \     ;   |.'    
|   :  \   |   :   .' |   :   .'     '---'      
|   | ,'   |   | ,'   |   | ,'                  
`----'     `----'     `----'                    
                                                
                                                      
                ,----..        ,----..           ,--. 
    ,---,.     /   /   \      /   /   \      ,--/  /| 
  ,'  .'  \   /   .     :    /   .     :  ,---,': / ' 
,---.' .' |  .   /   ;.  \  .   /   ;.  \ :   : '/ /  
|   |  |: | .   ;   /  ` ; .   ;   /  ` ; |   '   ,   
:   :  :  / ;   |  ; \ ; | ;   |  ; \ ; | '   |  /    
:   |    ;  |   :  | ; | ' |   :  | ; | ' |   ;  ;    
|   :     \ .   |  ' ' ' : .   |  ' ' ' : :   '   \   
|   |   . | '   ;  \; /  | '   ;  \; /  | |   |    '  
'   :  '; |  \   \  ',  /   \   \  ',  /  '   : |.  \ 
|   |  | ;    ;   :    /     ;   :    /   |   | '_\.' 
|   :   /      \   \ .'       \   \ .'    '   : |     
|   | ,'        `---`          `---`      ;   |,'     
`----'                                    '---'       
 */
/* REQUIREMENTS
 *  - Functions/Commands:
 *   -- P (name) Create a new profile
 *   -- F (N1) (N2) Create a friendship between N1 and N2
 *   -- U (N1) (N2) Remove a friendship between N1 and N2
 *   -- L (N1) List all friends of N1
 *   -- Q (N1) (N2) Check if N1 and N2 are friends -- Yes ? No
 *   -- X Close the program
 * 
 *  - Data Structures:
 *   -- Hash maps
 *   -- Linked lists
 * 
 *  - Classes: 
 *   -- Person
 *    --- Name
 *    --- Linked list of friends (Person objects)
 * 
 *  - Other:
 *   -- Use Hash maps to store Person objects
 *   -- Use Linked lists to store friends of Person objects
 *   -- A second Hash map that uses a pair of names as a key to check if two people are friends
 *    --- Example: "Sam" and "Lisa" becomes "Sam*Lisa" as the key
 *    --- The second hash map uses these strings as keys, and boolean as values.
 *    --- If the value is true, then the two people are friends.   
 * 
 *  OPTIONAL thing I did:
 *    - Merged the extension with the main file.
 *    - Added option to generate random names whenever.
 *    - Added a help command.
 *    - Added a custom command handler, I originally 
 *      had a switch loop, but wanted to do something new
 *      and look nicer, because there are too many commands,
 *      and it would be spagetthi code.
 */

/*
 * Name: Ted Sha
 * Period: 6th
 * Name of the Lab: FeetBook
 * The program works and handle all user errors.
 * 
 * What I learned: 
 * - Handling user errors is like 90% of the work.
 * - First I wanted to put the checkFriendship and checkIfFriendshipExists
 *   as one function, but I realized that it would make it very hard to handle
 *   other functions that tie into the check. 
 * - At first, I also wanted to make a general function that checks for user
 *   errors, it would include every single check. But I realized that it would
 *   not work because every function has different pre-conditions.
 * - commands needs to be declared at a higher scope than in main() to preserve 
 *   profiles.
 * - I initially called askForMode() recursively when the user entered an invalid
 *   number, but I realized that if the user kept on inputing invalid numbers, it
 *   would cause an stackoverflow error. So I used a while loop instead.
 * - Closing the scanner in the try-with-resources block also closes the program.
 * 
 * Some notes:
 * - I used a universal scanner, so I can close it and not have resource leaks - 
 *   probably not necessary, but it looks nice and good practice.
 */
import java.util.*;

public class Pd6TedShaMicroFB {

    // commands is declared as a static member so when I use generateRandomName(),
    // the object is not lost everytime the function is called in the loop.
    private static Commands commands = new Commands();

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            int mode = askForMode(sc);

            // Realistically we need to only check for mode 1,
            // because there's only 2 options
            if (mode == 1) {
                System.out.print("How many profiles would you like to create?: ");

                while (!sc.hasNextInt()) { // while the next input is not an integer
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // discard the invalid input
                }

                int numProfiles = sc.nextInt();
                for (int i = 0; i < numProfiles; i++) {
                    generateRandomName();
                }

                System.out.println("\n" + numProfiles + " Profiles created.\n\n" +
                        "----------------------------------------\n");
            }

            System.out.println("Commands:\n" +
                    "P <name> - Create a profile with the given name\n" +
                    "R <num> - Create <num> random profiles\n" +
                    "F <name1> <name2> - Create a friendship between two profiles\n" +
                    "U <name1> <name2> - Remove a friendship between two profiles\n" +
                    "L <name> - List all friends of a profile\n" +
                    "Q <name1> <name2> - Check if two profiles are friends\n" +
                    "H - Display this help message\n" +
                    "X - Exit the program\n\n" +
                    "Enter your command:\n");

            Map<String, Command> commandMap = new HashMap<>();
            commandMap.put("P", new CreateProfileCommand());
            commandMap.put("R", new CreateRandomProfileOnCommand());
            commandMap.put("F", new CreateFriendshipCommand());
            commandMap.put("U", new UnfriendCommand());
            commandMap.put("L", new ListFriendsCommand());
            commandMap.put("Q", new CheckIfFriendsCommand());
            commandMap.put("H", new DisplayHelpCommand());
            commandMap.put("X", new ExitCommand());

            while (sc.hasNext()) {
                String commandString = sc.next();
                Command command = commandMap.get(commandString);
                if (command != null) {
                    command.execute(sc, commands);
                } else {
                    System.out.println("Invalid command: " + commandString);
                }
            }

        }
    }

    /*
     * @dev Asks the user for the mode they want to run in.
     * 
     * @param sc The scanner object.
     * 
     * @return The mode the user wants to run in.
     */
    public static int askForMode(Scanner sc) {
        int mode = 0;

        while (mode != 1 && mode != 2) {
            System.out.println("Which mode would you like to run in?:\n\n" +
                    "Automatically create profiles (1)" +
                    "\nManually create profiles (2)");

            // Ensuring the input is an integer
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter 1 or 2");
                sc.next(); // discard non-integer input
            }
            mode = sc.nextInt();

            // Check if input is valid
            if (mode != 1 && mode != 2) {
                System.out.println("Invalid mode, please enter 1 or 2");
            }
        }
        return mode;
    }

    /*
     * @dev Generates a random name for the profile.
     */
    public static void generateRandomName() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomName = sb.toString();

        // Check if the random name already exists, very very unlikely but just in case.
        // Here I used a recursive function, but if a same random name is generated
        // too many times, it can cause a stackoverflow error. However, the chances of
        // that is about 1 in 1,216,652,902,400 - assuming the max stack size is set to
        // 512KB.
      // NOTE at a later date: the above is actually not *that* correct because for every name generated,
      //                       the chances go up, so that number is actually only valid for the first name.
        if (commands.checkIfProfileAlreadyExist(randomName)) {
            System.out.println("Error: randomly generated profile name already exists, generating a new one");
            generateRandomName();
        } else {
            commands.createProfile(randomName);
            System.out.println("Created profile: " + randomName);
        }
    }
}

interface Command {
    void execute(Scanner sc, Commands commands);
}

class CreateProfileCommand implements Command {
    @Override
    public void execute(Scanner sc, Commands commands) {
        String name = sc.next();

        // Check for user error.
        if (commands.checkIfProfileAlreadyExist(name)) {
            System.out.println("Error: profile already exists");
            return;
        }

        // Else create profile
        commands.createProfile(name);
        System.out.println("Profile created for " + name);
    }
}

class CreateRandomProfileOnCommand implements Command {
    @Override
    public void execute(Scanner sc, Commands commands) {
        while (!sc.hasNextInt()) { // while the next input is not an integer
            System.out.println("Invalid input. Please enter a number.");
            sc.next(); // discard the invalid input
        }

        int numProfilesToCreate = sc.nextInt();
        if (numProfilesToCreate > 0) {
            for (int i = 0; i < numProfilesToCreate; i++) {
                Pd6TedShaMicroFB.generateRandomName();
            }
        } else {
            System.out.println("Error: Invalid input. Please enter a valid number after the command R.");
        }

        System.out.println("Created " + numProfilesToCreate + " profiles.\n\n" +
                "Tip: Use the command H to see a list of commands.");
    }
}

class CreateFriendshipCommand implements Command {
    @Override
    public void execute(Scanner sc, Commands commands) {
        String name1 = sc.next();
        String name2 = sc.next();

        // Check for user error.
        if (commands.checkIfFriendshipExist(name1, name2) ||
                commands.checkIfProfileExist(name1, name2) ||
                commands.checkIfProfileIsSame(name1, name2)) {
            System.out.println("Error: profile does not exist/is same or friendship already exists");
            return;
        }

        // Else create friendship
        commands.createFriendship(name1, name2);
        System.out.println("Friendship created between " + name1 + " and " + name2);

    }
}

class UnfriendCommand implements Command {
    @Override
    public void execute(Scanner sc, Commands commands) {
        String name3 = sc.next();
        String name4 = sc.next();

        // Check for user error.
        if (!commands.checkIfFriendshipExist(name3, name4) ||
                commands.checkIfProfileExist(name3, name4)) {
            System.out.println("Error: profile does not exist or friendship does not exist");
            return;
        }

        // Else remove friendship
        System.out.println("Friendship removed between " + name3 + " and " + name4);
        commands.removeFriendship(name3, name4);
    }
}

class ListFriendsCommand implements Command {
    @Override
    public void execute(Scanner sc, Commands commands) {
        String name5 = sc.next();

        // Check for user error.
        if (commands.checkIfProfileExist(name5)) {
            System.out.println("Error: profile does not exist");
            return;
        }

        // Else list friends
        if (commands.profiles.get(name5).getFriends().isEmpty()) {
            System.out.println(name5 + " has no friends");
            return;
        }

        commands.listFriends(name5);
    }
}

class CheckIfFriendsCommand implements Command {
    @Override
    public void execute(Scanner sc, Commands commands) {
        String name6 = sc.next();
        String name7 = sc.next();

        // Check for user error.

        if (commands.checkIfProfileExist(name6, name7)) {
            System.out.println("Error: profile(s) does not exist");
            return;
        }

        // Else check friendship
        commands.checkFriendship(name6, name7);
    }
}

class DisplayHelpCommand implements Command {
    @Override
    public void execute(Scanner sc, Commands commands) {
        System.out.println("Commands:\n" +
                "P <name> - Create a profile with the given name\n" +
                "R <num> - Create <num> random profiles\n" +
                "F <name1> <name2> - Create a friendship between two profiles\n" +
                "U <name1> <name2> - Remove a friendship between two profiles\n" +
                "L <name> - List all friends of a profile\n" +
                "Q <name1> <name2> - Check if two profiles are friends\n" +
                "H - Display this help message\n" +
                "X - Exit the program\n\n" +
                "Enter your command:\n");
    }
}

class ExitCommand implements Command {
    @Override
    public void execute(Scanner sc, Commands commands) {
        System.out.println("See you next time!");
        System.exit(0);
    }
}

class Person {
    private String name;
    private LinkedList<Person> friends;

    public Person(String name) {
        this.name = name;
        friends = new LinkedList<Person>();
    }

    public String getName() {
        return name;
    }

    public LinkedList<Person> getFriends() {
        return friends;
    }

    public void addFriend(Person friend) {
        friends.add(friend);
    }

    public void removeFriend(Person friend) {
        friends.remove(friend);
    }
}

class Commands {
    public Map<String, Person> profiles;
    public Map<String, Boolean> friends;

    public Commands() {
        profiles = new HashMap<String, Person>();
        friends = new HashMap<String, Boolean>();
    }

    /*
     * @dev Creates a new profile.
     * 
     * @param name
     * 
     * @return void
     */
    public void createProfile(String name) {
        profiles.put(name, new Person(name));
    }

    /*
     * @dev Creates a keypair friendship between two people.
     * 
     * @param name1 name2
     * 
     * @return void
     */
    public void createFriendship(String name1, String name2) {
        Person p1 = profiles.get(name1);
        Person p2 = profiles.get(name2);

        p1.addFriend(p2);
        p2.addFriend(p1);

        friends.put(name1 + "*" + name2, true);
        friends.put(name2 + "*" + name1, true);
    }

    /*
     * @dev Sets the key to false if the friendship exists.
     * 
     * @param name1
     * 
     * @param name2
     * 
     * @return void
     */
    public void removeFriendship(String name1, String name2) {
        Person p1 = profiles.get(name1);
        Person p2 = profiles.get(name2);

        p1.removeFriend(p2);
        p2.removeFriend(p1);

        friends.put(name1 + "*" + name2, false);
        friends.put(name2 + "*" + name1, false);
    }

    /*
     * @dev (prints)Lists all the friends of a person.
     * 
     * @param name
     * 
     * @return void
     */
    public void listFriends(String name) {
        Person p = profiles.get(name);
        LinkedList<Person> friends = p.getFriends();

        System.out.println(p.getName() + "\'s friends: ");
        for (Person friend : friends) {
            System.out.print(friend.getName() + '\n');
        }
        System.out.println();
    }

    // Start of functions for readability for handling user errors.

    /*
     * @dev This function checks for existence of keys, or else if
     * the user inputs a name that doesn't exist, it will return
     * an NullPointerException.
     * 
     * @param name1, name2
     * 
     * @return boolean
     */
    public boolean checkIfKeyExist(String name1, String name2) {
        return !(friends.get(name1 + "*" + name2) == null);
    }

    /*
     * @dev This function is basically a duplicate of the checkFriendship function -
     * but it is needed for the checkFriendship function to work and to handle
     * users errors since this returns a boolean.
     * 
     * @param name1, name2
     * 
     * @return boolean
     */
    public boolean checkIfFriendshipExist(String name1, String name2) {
        if (checkIfKeyExist(name1, name2)) {
            return (friends.get(name1 + "*" + name2) == true);
        }
        return false;
    }

    /*
     * @dev This function checks for existence of profiles.
     * 
     * @param name1, name2
     * 
     * @return boolean
     */
    public boolean checkIfProfileExist(String name1, String name2) {
        return (profiles.get(name1) == null || profiles.get(name2) == null);
    }

    /*
     * @dev This function checks if the two names are the same, to prevent
     * the user from adding themselves as a friend.
     * 
     * @param name1, name2
     * 
     * @return boolean
     */
    public boolean checkIfProfileIsSame(String name1, String name2) {
        return (name1.equals(name2));
    }

    /*
     * @dev Overrides the above function for listFriends function,
     * since it only takes one name as input.
     * 
     * @param name1
     * 
     * @return boolean
     */
    public boolean checkIfProfileExist(String name1) {
        return (profiles.get(name1) == null);
    }

    /*
     * @dev Pre-check for createProfile function.
     * 
     * @param name1
     * 
     * @return boolean
     */
    public boolean checkIfProfileAlreadyExist(String name1) {
        return (profiles.get(name1) != null);
    }
    // End of functions for readability.

    /*
     * @dev This the actual function that checks if a friendship exist between two
     * names,
     * and prints out to the console.
     * 
     * @param name1, name2
     * 
     * @return void
     */
    public void checkFriendship(String name1, String name2) {

        if (checkIfKeyExist(name1, name2)) {
            if (friends.get(name1 + "*" + name2) == true) {
                System.out.println("Yes");
                // If key's bool is true, then friendship exist.
            } else {
                System.out.println("No");
            } // If key's bool is false, then friendship doesn't exist.
        } else {
            System.out.println("No");
        } // If key haven't been created, friendship doesn't exist.
    }

    /*
     * @dev Closes the program.
     * 
     * @param void
     * 
     * @return void
     */
    public void close() {
        System.exit(0);
    }
}

/*
 * Test runs:
 * 
 * If AUTOMATIC mode is chosen:
 * 
 * Which mode would you like to run in?:
 * 
 * Automatically create profiles (1)
 * Manually create profiles (2)
 * 1
 * How many profiles would you like to create?: 10
 * Created profile: luylg
 * Created profile: kppku
 * Created profile: mczjp
 * Created profile: llmku
 * Created profile: ehrrx
 * Created profile: jcgvh
 * Created profile: rqxmo
 * Created profile: uiyxr
 * Created profile: pumzi
 * Created profile: tjxle
 * 
 * 10 Profiles created.
 * 
 * ----------------------------------------
 * 
 * Commands:
 * P <name> - Create a profile with the given name
 * R <num> - Create <num> random profiles
 * F <name1> <name2> - Create a friendship between two profiles
 * U <name1> <name2> - Remove a friendship between two profiles
 * L <name> - List all friends of a profile
 * Q <name1> <name2> - Check if two profiles are friends
 * H - Display this help message
 * X - Exit the program
 * 
 * Enter your command:
 * 
 * L tjxle
 * tjxle has no friends
 * P kppku
 * Error: profile already exists
 * P ted
 * Profile created for ted
 * P james
 * Profile created for james
 * P audrey
 * Profile created for audrey
 * L ted
 * ted has no friends
 * F ted james
 * Friendship created between ted and james
 * Q james ted
 * Yes
 * U audrey james
 * Error: profile does not exist or friendship does not exist
 * U james ted
 * Friendship removed between james and ted
 * R 2
 * Created profile: pslul
 * Created profile: qvwwm
 * Created 2 profiles.
 * 
 * Tip: Use the command H to see a list of commands.
 * P qvwwm
 * Error: profile already exists
 * H
 * Commands:
 * P <name> - Create a profile with the given name
 * R <num> - Create <num> random profiles
 * F <name1> <name2> - Create a friendship between two profiles
 * U <name1> <name2> - Remove a friendship between two profiles
 * L <name> - List all friends of a profile
 * Q <name1> <name2> - Check if two profiles are friends
 * H - Display this help message
 * X - Exit the program
 * 
 * Enter your command:
 * 
 * L ted
 * ted has no friends
 * F ted james
 * Friendship created between ted and james
 * F ted audrey
 * Friendship created between ted and audrey
 * L ted
 * ted's friends:
 * james
 * audrey
 * 
 * X
 * See you next time!
 * 
 * _______________________________________________________
 * 
 * If MANUAL mode is chosen:
 * 
 * Which mode would you like to run in?:
 * 
 * Automatically create profiles (1)
 * Manually create profiles (2)
 * 2
 * Commands:
 * P <name> - Create a profile with the given name
 * R <num> - Create <num> random profiles
 * F <name1> <name2> - Create a friendship between two profiles
 * U <name1> <name2> - Remove a friendship between two profiles
 * L <name> - List all friends of a profile
 * Q <name1> <name2> - Check if two profiles are friends
 * H - Display this help message
 * X - Exit the program
 * 
 * Enter your command:
 * ...
 * 
 * 
 */
