package Factory;

import Modules.Game;
import Modules.Products.FpsGame;

public class FpsGameFactory implements GameFactory{

    @Override
    public Game createGame(String name, int difficulty, double price, int stock) {
        return new FpsGame(name, difficulty, price, stock);
    }
}
