package game.engine;

import game.engine.tip.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sasho on 10.4.2016 г..
 */
public class Game {
    private String playerName;
    private Question question;
    private HashMap<String,Tip> tips;
    private int questionStatus;

    public String getPlayerName(){
        return this.playerName;
    }

    public Game(String playerName){
        this.playerName = playerName;
        questionStatus = 0;
        tips = new HashMap<String,Tip>();
        tips.put("Fifty",new Fifty());
        tips.put("HFTS",new HFTS());
        tips.put("CallNakov",new CallNakov());

    }
    public void setNextQuestion(Question nextQuestion){
        this.question = nextQuestion;
    }
    public Tip getTip(String name){
        return tips.get(name);
    }
    public void addQuestionStatus(){this.questionStatus++;}
    public Question getCurrentQuestion(){
        return this.question;
    }
    public int getQuestionStatus(){
        return this.questionStatus;
    }

}
