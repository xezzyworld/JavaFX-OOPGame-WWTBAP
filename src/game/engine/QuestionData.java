package game.engine;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Sasho on 12.4.2016 г..
 */
public class QuestionData {
    private final String dir = "data\\";
    public final int numOfQuestions = 12;
    private ArrayList<Question> questions;
    boolean isLocked;

    public QuestionData() {
        questions = new ArrayList<Question>();
        isLocked = false;
    }

    public Question getQuestion(int id){
        return questions.get(id);
    }

    public int addQuestion(String question, int correctAnswer, String answer1,String answer2,String answer3){
        if(isLocked == false) {
            if(question==null || question.equals("") ||answer1==null||answer1.equals("")||answer2==null||answer2.equals("")||answer3==null||answer3.equals("")) return 1;
            questions.add(new Question(question, correctAnswer));
            questions.get(questions.size() - 1).addAll(answer1, answer2, answer3);
            if(questions.size() == 12) isLocked = true;
            return 0;
        }
        else{
            return 1;
        }
    }

    public String loadQuestions(String filename){
        questions = new ArrayList<Question>();
        try(BufferedReader br = new BufferedReader(new FileReader(dir+filename))){
            for (int i = 0; i < numOfQuestions; i++) {
                //READING AND PARSING QUESTION
                String question = br.readLine();
                if(question.startsWith(i+".")){
                    question = question.replaceFirst(i+"\\.","");

                    //READING ANSWERS
                    String answer1 = br.readLine();
                    String answer2 = br.readLine();
                    String answer3 = br.readLine();


                    // PARSING ANSWERS and CORRECT ANSWER
                    int correctAnswer = -1;

                    if(answer1.startsWith("-")){
                        answer1 = answer1.replaceFirst("-a\\)","");
                        correctAnswer = 0;
                    } else{
                        answer1 = answer1.replaceFirst("a\\)","");
                    }
                    if(answer2.startsWith("-")){
                        answer2 = answer2.replaceFirst("-b\\)","");
                        correctAnswer = 1;
                    } else{
                        answer2 = answer2.replaceFirst("b\\)","");
                    }
                    if(answer3.startsWith("-")){
                        answer3 = answer3.replaceFirst("-c\\)","");
                        correctAnswer = 2;
                    } else{
                        answer3 = answer3.replaceFirst("c\\)","");
                    }
                    if(correctAnswer==-1){
                        questions = new ArrayList<Question>();
                        return "Incorrect format of answer.";
                    }

                    //CREATING THE OBJECT

                    questions.add(i,new Question(question,correctAnswer));

                    questions.get(i).addAll(answer1,answer2,answer3);


                }
                else{
                    questions = new ArrayList<Question>();
                    return "Incorrect format of question.";
                }

            }
        } catch (IOException e){
            questions = new ArrayList<Question>();
            return "Problem with the file.";
        }
        isLocked = true;
        return "Success";
    }

    public String saveQuestions(String filename){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(dir+filename))){
            if(questions.size()==numOfQuestions) {


                for (int i = 0; i < questions.size(); i++) {
                    Question current = questions.get(i);
                    String[] cq = new String[3];
                    for (int j = 0; j < cq.length; j++) {
                        cq[j] = "";
                    }
                    cq[current.getCorrectAnswer()] = "-";

                    bw.append(i + "." + current.getQuestion());
                    bw.newLine();
                    bw.append(cq[0] + "a)" + current.getAnswer(0));
                    bw.newLine();
                    bw.append(cq[1] + "b)" + current.getAnswer(1));
                    bw.newLine();
                    bw.append(cq[2] + "c)" + current.getAnswer(2));
                    bw.newLine();
                }
            }
            else{
                System.out.println(questions.size());
                return "Current question list is not correct for save.";
            }
        } catch (IOException e){
            return "Failure. Probably problem with filename";
        }
        isLocked = true;
        return "Success";
    }

}
