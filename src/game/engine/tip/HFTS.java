package game.engine.tip;


import game.engine.Game;

import java.util.Random;

/**
 * Created by Sasho on 12.4.2016 г..
 */
public class HFTS extends Tip {
    private final int diff=2;
    public HFTS(){
        super("Help from the Top Students");

    }

    @Override
    public String DoTip(Game game) {
        if(this.isUsed) return this.used;
        this.isUsed = true;
        int mediator = game.getQuestionStatus()/2;
        Random rnd = new Random();
        int[] percentWrong = new int[2];
        percentWrong[0] = rnd.nextInt(mediator+diff+1);
        while(!(percentWrong[0]>=mediator-diff && percentWrong[0]<=mediator+diff)){

            percentWrong[0] = rnd.nextInt(mediator+diff+1);
        }
        percentWrong[1] = rnd.nextInt(2);
        int percentCorrect = 10-percentWrong[0] - percentWrong[1];
        int b = rnd.nextInt(2);
        int c = (int)Math.abs(b-1);
        percentCorrect *=10;
        percentWrong[b] *=10;
        percentWrong[c] *=10;
        switch(game.getCurrentQuestion().getCorrectAnswer()){
            case 0: return "A: "+percentCorrect+"%\nB: "+percentWrong[b]+"%\nC: "+percentWrong[c]+"%";
            case 1: return "A: "+percentWrong[b]+"%\nB: "+percentCorrect+"%\nC: "+percentWrong[c]+"%";
            case 2: return "A: "+percentWrong[b]+"%\nB: "+percentWrong[c]+"%\nC: "+percentCorrect+"%";
            default: return "Error";
        }
    }
}
