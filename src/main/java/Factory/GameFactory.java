package Factory;

import Modules.Game;

public interface GameFactory {

    Game createGame(String name, int difficulty, double price, int stock);

}
