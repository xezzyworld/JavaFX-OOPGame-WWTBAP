package game.engine;

import java.util.ArrayList;

/**
 * Created by Sasho on 10.4.2016 г..
 */
public class Question {
    private String question;
    private ArrayList<String> answers;
    private int correctAnswer;

    public Question(String question, int correctAnswer){
        this.question = question;
        answers = new ArrayList<>();
        this.correctAnswer = correctAnswer;
    }
    public void addAnswer(String answer){
        this.answers.add(answer);
    }
    public void addAll(String answer1, String answer2, String answer3){
        this.answers.add(answer1);
        this.answers.add(answer2);
        this.answers.add(answer3);

    }

   @Override
   public String toString(){
       return "Question: "+this.question+"\n"+"correctAnswer:"+this.answers.get(correctAnswer) + "\nAnswers:\n"
               + this.answers.get(0) + "\n" + this.answers.get(1) + "\n" + this.answers.get(2);
   }

    public String getAnswer(int index){
        return answers.get(index);
    }
    public String getQuestion() {return this.question;}
    public int getCorrectAnswer() {return this.correctAnswer;}
    public ArrayList<String> getAnswers() {return new ArrayList<>(answers);}
}
