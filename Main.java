//AdventureGme
import java.util.Scanner;

public class Main {
    // Time limits for thirst and hunger
    private static final long THIRST_TIME_LIMIT = 3 * 24 * 60 * 60 * 1000L; // 3 days in milliseconds
    private static final long HUNGER_TIME_LIMIT = 10 * 24 * 60 * 60 * 1000L; // 10 days in milliseconds

    // Length of a day in milliseconds
    private static final long DAY_LENGTH = 60 * 1000L; // 1 minute in milliseconds

    // Number of coins required to win the game
    private static final int COINS_TO_WIN = 100;

    // Size of the grid and starting position
    private static final int GRID_SIZE = 5;
    private static final int STARTING_ROW = 2;
    private static final int STARTING_COL = 2;

    // Chance of an item being in a room and values of items
    private static final double ITEM_CHANCE = 0.2; // 20% chance of an item being in a room
    private static final int COIN_VALUE = 5;

    // Game state variables
    private static long startTime;
    private static long lastThirstTime;
    private static long lastHungerTime;
    private static int coins;
    private static int currentRow;
    private static int currentCol;
    private static int foodCount;
    private static int drinkCount;

    public static void main(String[] args) {
        startGame();
    }
//Starting the game function and how to play the
    private static void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Adventuregame Game! You have 10 coins to start with.");
        System.out.println("This is how you play the game. 0 to quit the game. 1 to move north. 2 to move south. 3 to move east. 4 to move west. 5 to use item. 6 to show inventory.");

    // Initialize game state variables
        coins = 10;
        startTime = System.currentTimeMillis();
        lastThirstTime = startTime;
        lastHungerTime = startTime;
        currentRow = STARTING_ROW;
        currentCol = STARTING_COL;
        foodCount = 0;
        drinkCount = 0;

        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsedDays = (currentTime - startTime) / DAY_LENGTH;

    // Check if the player has died of thirst or hunger
            if (elapsedDays >= 3 && currentTime - lastThirstTime >= THIRST_TIME_LIMIT) {
                System.out.println("You died of thirst!");
                endGame();
                break;
            }

            if (elapsedDays >= 10 && currentTime - lastHungerTime >= HUNGER_TIME_LIMIT) {
                System.out.println("You died of hunger!");
                endGame();
                break;
            }


    // Get player input
            System.out.print("What do you want to do? (0. Quit, 1. Move north, 2. Move south, 3. Move east, 4. Move west, 5. Use item, 6. Show inventory) ");
            int choice = scanner.nextInt();

    // Move the player
            switch (choice) {
                case 0:
                    endGame();
                    return;
                case 1:
                    if (currentRow > 0) {
                        currentRow--;
                        System.out.println("You moved north.");
                    } else {
                        System.out.println("You can't move north.");
                    }
                    break;
                case 2:
                    if (currentRow < GRID_SIZE - 1) {
                        currentRow++;
                        System.out.println("You moved south.");
                    } else {
                        System.out.println("You can't move south.");
                    }
                    break;
                case 3:
                    if (currentCol < GRID_SIZE - 1) {
                        currentCol++;
                        System.out.println("You moved east.");
                    } else {
                        System.out.println("You can't move east.");
                    }
                    break;
                case 4:
                    if (currentCol > 0) {
                        currentCol--;
                        System.out.println("You moved west.");
                    } else {
                        System.out.println("You can't move west.");
                    }
                    break;
                case 5:
                    useItem();
                    break;
                case 6:
                    showInventory();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

    // Check if the player has found an item
            if (Math.random() < ITEM_CHANCE) {
                int item = (int) (Math.random() * 3);
                switch (item) {
                    case 0:
                        System.out.println("You found some food!");
                        foodCount++;
                        break;
                    case 1:
                        System.out.println("You found some water!");
                        drinkCount++;
                        break;
                    case 2:
                        System.out.println("You found some coins!");
                        coins += COIN_VALUE;
                        break;
                }
            }

            // Check if the player has won the game
            if (coins >= COINS_TO_WIN) {
                System.out.println("Congratulations! You have collected " + coins + " coins and won the game!");
                endGame();
                break;
            }
        }
    }
//End the game function or method
    private static void endGame() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedDays = elapsedTime / DAY_LENGTH;
        System.out.println("You survived for " + elapsedDays + " days and collected " + coins + " coins.");//printing how many days you survived and how many coins you collected
        System.exit(0);//Checking that the input is 0
    }
//Using a item function or method
    private static void useItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What do you want to use? (1. Food, 2. Water) ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                if (foodCount > 0) {
                    foodCount--;
                    lastHungerTime = System.currentTimeMillis();
                    System.out.println("You ate some food and replenished your hunger.");
                } else {
                    System.out.println("You don't have any food.");
                }
                break;
            case 2:
                if (drinkCount > 0) {
                    drinkCount--;
                    lastThirstTime = System.currentTimeMillis();
                    System.out.println("You drank some water and replenished your thirst.");
                } else {
                    System.out.println("You don't have any water.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void showInventory() {
        System.out.println("Inventory: ");
        System.out.println("Food: " + foodCount);// How much food you have
        System.out.println("Water: " + drinkCount);// How much water you have
        System.out.println("Coins: " + coins);
        System.out.println("Hunger: " + getHungerLevel() + "/10");// How hungry you are
        System.out.println("Thirst: " + getThirstLevel() + "/3");// How hydrated you are
    }

    private static int getHungerLevel() {
        long currentTime = System.currentTimeMillis();
        long elapsedDays = (currentTime - startTime) / DAY_LENGTH;
        long elapsedHours = (currentTime - lastHungerTime) / (60 * 60 * 1000L);
        int hungerLevel = (int) (elapsedHours / (24 * (10 - elapsedDays)));
        return Math.min(hungerLevel, 10);
    }

    private static int getThirstLevel() {
        long currentTime = System.currentTimeMillis();
        long elapsedDays = (currentTime - startTime) / DAY_LENGTH;
        long elapsedHours = (currentTime - lastThirstTime) / (60 * 60 * 1000L);
        int thirstLevel = (int) (elapsedHours / (24 * (3 - elapsedDays)));
        return Math.min(thirstLevel, 3);
    }
}


}
