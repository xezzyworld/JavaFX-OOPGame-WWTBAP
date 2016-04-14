package game.engine.tip;

import game.engine.Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sasho on 12.4.2016 г..
 */
public class CallNakov extends Tip {

    public CallNakov() {
        super("Call Nakov");
    }
    @Override
    public String DoTip(Game game) {
        if(this.isUsed) return this.used;
        Random rnd = new Random();
        ArrayList<Byte> ca = new ArrayList<Byte>();
        int currentStatus = game.getQuestionStatus();
        int caNum = 98 - 2*currentStatus;
        for (int i = 0; i < caNum; i++) {
            ca.add((byte)game.getCurrentQuestion().getCorrectAnswer());
        }
        for (int i = 0; i < currentStatus+1; i++) {
            switch(game.getCurrentQuestion().getCorrectAnswer()){
                case 0:
                    ca.add((byte)1);
                    ca.add((byte)2);
                    break;
                case 1:
                    ca.add((byte)0);
                    ca.add((byte)2);
                case 2:
                    ca.add((byte)0);
                    ca.add((byte)1);
            }

        }
        int result = ca.get(rnd.nextInt(100));
        String[] toString = {"A","B","C"};
        this.isUsed = true;
        return "The correct answer is: "+toString[result];

    }
}
