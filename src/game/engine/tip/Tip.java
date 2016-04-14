package game.engine.tip;

import game.engine.Game;

/**
 * Created by Sasho on 10.4.2016 г..
 */
public abstract class Tip {
    protected final String used;
    private String name;
    protected boolean isUsed;
    protected Tip(String name) {
        this.name = name;
        isUsed = false;
        this.used = "Already used.";
    }

    public abstract String DoTip(Game game);
    public boolean isUsed(){
        return this.isUsed;
    }
    public String getName() {
        return this.name;
    }
}
