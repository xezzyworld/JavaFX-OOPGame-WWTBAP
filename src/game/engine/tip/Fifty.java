package game.engine.tip;

import game.engine.Game;

import java.util.Random;

/**
 * Created by Sasho on 12.4.2016 г..
 */
public class Fifty extends Tip {

    public Fifty(){
        super("Fifty/Fifty (50/50)");
    }

    @Override
    public String DoTip(Game game) {
        if(this.isUsed) return this.used;
        isUsed = true;
        int ca = game.getCurrentQuestion().getCorrectAnswer();
        Random rnd = new Random();
        int result = rnd.nextInt(3);
        while(result == ca){
            result = rnd.nextInt(3);
        }
        String[] letters = {"A","B","C"};
        String output = "Answer " + letters[result] + " is not correct!";
        return output;
    }
}
