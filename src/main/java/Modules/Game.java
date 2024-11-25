package Modules;

public class Game {

    private int id;
    private static int nextId = 1;
    private String name;
    private int difficulty;
    private double price;
    private boolean enable;
    private int stock;
    private static final int IVA = 10;

    public Game (String name, int difficulty, double price, int stock) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.enable = false;
        this.stock = stock;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Game " +
                name + "\n difficulty= " + difficulty +
                "\n price= " + price +
                "\n stock=" + stock;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public double getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }
}
