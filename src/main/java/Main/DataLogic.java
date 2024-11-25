package Main;

import Factory.FpsGameFactory;
import Factory.GameFactory;
import Factory.RpgGameFactory;
import Factory.StrategyGameFactory;
import Modules.Exceptions.GamesStockEmpty;
import Modules.Exceptions.NumberOutOfRange;
import Modules.Exceptions.StringInvalid;
import Modules.Game;
import Modules.Products.FpsGame;
import Modules.Products.RpgGame;
import Modules.Products.StrategyGame;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Main.Screen.frame;


public class DataLogic {

    private static final Scanner INPUT = new Scanner(System.in);
    private static final ArrayList<Game> GAMES = new ArrayList<Game>();
    private static GameFactory mainFactory;
    private static boolean exit = false;

    public static void chooseCategory() {
        System.out.println("""
                Choose Category
                [1]Strategy           [2]RPG game
                [3]Fps Game         
                """);
        int option = INPUT.nextInt();
        switch (option) {
            case 1:
                mainFactory = new StrategyGameFactory();
                break;
            case 2:
                mainFactory = new RpgGameFactory();
                break;
            case 3:
                mainFactory = new FpsGameFactory();
                break;
            default:
                System.out.println("Option Invalid, please try again");
        }
        INPUT.nextLine();
    }

    public static void deleteGame() {
        int option = -1;
        if (checkGames()) {
            do {
                System.out.println("Which one do you want to delete");
                printAllForOption();
                option = askOption();
            }while(!validateOption(option) || option == -1);
            System.out.println("Game " + GAMES.get(option).getName() + " has been deleted.");
            GAMES.remove(option);
        }
    }

    public static boolean checkGames() throws GamesStockEmpty {
        boolean output = false;
        if (GAMES.isEmpty()) {
            throw new GamesStockEmpty("Stock games is empty");
        } else {
            output = true;
        }
        return output;
    }

    public static void printAllAvailable() {
        try {
            if (checkGames()) {
                GAMES.forEach( game -> {
                    if(game.getStock()> 0) {
                        System.out.println("[- ]" + game.getName() +
                                " [" + game.getStock() + " units available]");
                    }
                });
            }
        } catch (GamesStockEmpty e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printAllForOption(){
        GAMES.forEach(game -> System.out.println(
                "[" + (GAMES.indexOf(game) + 1) + "] "
                        + game.getName()));
    }

    private static boolean ValidateString(String string) {
        Pattern especialChars = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher especialChar = especialChars.matcher(string);

        return especialChar.find();
    }

    private static boolean validateOption(int option){
        boolean output = true;
        if(option > GAMES.size() || option <= 0) {
            System.out.println("Number out of range, please try again");
            output = false;
        }
        return output;
    }


    private static String askDataString() throws StringInvalid {
        String output = "";
        do {
            output = INPUT.nextLine();
            if(ValidateString(output)) {
                output= "";
                throw new StringInvalid("The name contains invalid characters");
            }
        } while(output.isEmpty());

        return output;
    }

    private static void showGame(String name){
        GAMES.forEach(game -> {
            if(game.getName().equalsIgnoreCase(name)) {
                System.out.println(game);
            }
        });
    }


    public static int askOption() {
        int output = 0;
        do {
            try {
                output = INPUT.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("enter a number please");
            } finally {
                INPUT.nextLine();
            }
        } while(output == 0);
        return output;
    }

    public static double askDouble() {
        double output = 0;
        do {
            try {
                output = INPUT.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("enter a number please");
            } finally {
                INPUT.nextLine();
            }
        } while(output == 0);
        return output;
    }

    public static Game newGame() throws NumberOutOfRange {
        int difficulty;
        String name;
        double price;
        int stock;
        System.out.println("what is the name of the game?");
        name = askDataString();
        do {
            System.out.println("enter the difficulty from 1 to 10");
            difficulty = askOption();
            if(difficulty <= 0 || difficulty > 10) {
                throw new NumberOutOfRange("Number out of range");
            }
        }while (difficulty <= 0 || difficulty > 10);
        System.out.println("what is its price");
        price = askDouble();
        do {
            System.out.println("how many units are in stock");
            stock = askOption();
            if(stock <= 0) {
                throw new NumberOutOfRange("Number of Stock out of range");
            }
        }while (difficulty <= 0);

        return mainFactory.createGame(name, difficulty, price, stock);
    }

    public static void setStockUnits() {
        int option = 0;
        int value = 0;
        if(checkGames()) {
            do {
                System.out.println("What game do you want to modify the stock of?");
                printAllForOption();
                option = askOption() -1;
            }while(!validateOption(option));
            System.out.println("The game " + GAMES.get(option).getName() +
                    " has " + GAMES.get(option).getStock() + " units in stock.\n" +
                    "What is the new value?");
            value = askOption();
            GAMES.get(option).setStock(value);
            System.out.println("game stock "+ GAMES.get(option).getName() + " UPDATED");
        }

    }

    public static void filterByDifficulty(String difficulty) {
        if (difficulty.equalsIgnoreCase("Easy")) {
            GAMES.stream()
                    .filter(game -> game.getDifficulty() < 4)
                    .map(Game::getName)
                    .forEach(System.out::println);
        } else if(difficulty.equalsIgnoreCase("Hard")) {
            GAMES.stream()
                    .filter(game -> game.getDifficulty() > 7)
                    .map(Game::getName)
                    .forEach(System.out::println);
        } else {
            GAMES.stream()
                    .filter(game -> game.getDifficulty() < 7 && game.getDifficulty() >= 4)
                    .map(Game::getName)
                    .forEach(System.out::println);
        }

    }

    public static void filterByCategory(String category) {
        if (category.equalsIgnoreCase("RPG")) {
            GAMES.stream()
                    .filter(game -> {
                        if (game instanceof RpgGame) {
                            System.out.println(game);
                        }
                        return false;
                    }).map(Game::getName)
                    .forEach(System.out::println);
        } else if (category.equalsIgnoreCase("FPS")) {
            GAMES.stream()
                    .filter(game -> {
                        if (game instanceof FpsGame) {
                            System.out.println(game);
                        }
                        return false;
                    }).map(Game::getName)
                    .forEach(System.out::println);
        } else {
            GAMES.stream()
                    .filter(game -> {
                        if (game instanceof StrategyGame) {
                            System.out.println(game);
                        }
                        return false;
                    }).map(Game::getName)
                    .forEach(System.out::println);
        }
    }


    public static void menuLogic(int option) {

        switch(option){
            case 1:
                System.out.println(frame);
                DataLogic.chooseCategory();
                GAMES.add(DataLogic.newGame());
                break;
            case 2:
                setStockUnits();
                break;
            case 3:
                printAllAvailable();
                break;
            case 4:
                System.out.println("Write the game's name");
                showGame(askDataString());
                break;
            case 5:
                deleteGame();
                break;
            case 6:
                System.out.println("[1]Difficulty: \n" +
                        "[2]Category");
                int category = askOption();
                switch(category){
                    case 1:
                        System.out.println("[1]easy\n[2]Medium\n[3]Hard");
                        int dificult = askOption();
                        switch(dificult){
                            case 1:
                                filterByDifficulty("Easy");
                                break;
                            case 2:
                                filterByDifficulty("Medium");
                                break;
                            case 3:
                                filterByDifficulty("Hard");
                                break;
                            default:
                                System.out.println("Invalid Option");
                        }
                        break;
                    case 2:
                        System.out.println("[1]Strategy\n[2]FPS\n[3]Rol RPG");
                        int categorySelected = askOption();
                        switch(categorySelected){
                            case 1:
                                filterByCategory("Strategy");
                                break;
                            case 2:
                                filterByCategory("FPS");
                                break;
                            case 3:
                                filterByCategory("RPG");
                                break;
                            default:
                                System.out.println("Invalid Option");
                        }
                        break;
                }
            case 7:
                exit = true;
                break;
            default:
                System.out.println("Option Invalid, please try again");

        }
    }

    public static void innitMenu(){

        do {
            Screen.printMenu();
            DataLogic.menuLogic(DataLogic.askOption());
        } while (!exit);
    }



}
