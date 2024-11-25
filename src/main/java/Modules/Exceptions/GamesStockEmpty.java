package Modules.Exceptions;

public class GamesStockEmpty extends RuntimeException {
    public GamesStockEmpty(String message) {
        super(message);
    }
}
