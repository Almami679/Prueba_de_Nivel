package Factory;

import Modules.Game;
import Modules.Products.RpgGame;

public class RpgGameFactory implements GameFactory{

    @Override
    public Game createGame(String name, int difficulty, double price, int stock) {
        return new RpgGame(name, difficulty, price, stock);
    }
}
