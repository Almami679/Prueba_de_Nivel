package Factory;

import Modules.Game;
import Modules.Products.StrategyGame;

public class StrategyGameFactory implements GameFactory {

    @Override
    public Game createGame(String name, int difficulty, double price, int stock) {
        return new StrategyGame(name, difficulty, price, stock);
    }
}

