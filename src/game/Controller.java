package game;

import game.engine.Game;
import game.engine.Question;
import game.engine.QuestionData;

public class Controller {
    protected Game game;
    protected QuestionData data;
    protected String loadStatus;

    public Controller(String playerName, String filename){
        game = new Game(playerName);
        data = new QuestionData();
        loadStatus = data.loadQuestions(filename);
    }

    public String getLoadStatus(){
        return this.loadStatus;
    }

    public Question nextQuestion(){
        game.setNextQuestion(data.getQuestion(game.getQuestionStatus()));
        return game.getCurrentQuestion();
    }

    public int nextAnswer(int index){
        if(game.getCurrentQuestion().getCorrectAnswer() == index){
            if(game.getQuestionStatus()==data.numOfQuestions-1){
                return 1;
            }
            game.addQuestionStatus();
            return 0;
        }
        else{
            return -1;
        }
    }

    public String Tip(String name){
        return game.getTip(name).DoTip(game);
    }
}
