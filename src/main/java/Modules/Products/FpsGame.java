package Modules.Products;

import Modules.Game;

public class FpsGame extends Game implements Games {

    public FpsGame(String name, int difficulty, double price, int stock) {
        super(name, difficulty, price, stock);
    }

    @Override
    public void setPrice(double price) {

    }

    @Override
    public void download(Boolean enable) {

    }
}
