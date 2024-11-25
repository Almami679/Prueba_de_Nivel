package Modules.Products;

import Modules.Game;

public class RpgGame extends Game implements Games {

    public RpgGame(String name, int difficulty, double price, int stock) {
        super(name, difficulty, price, stock);
    }

    @Override
    public void setPrice(double price) {

    }

    @Override
    public void download(Boolean enable) {

    }
}
