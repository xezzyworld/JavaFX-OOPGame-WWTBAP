package game;

import game.engine.Question;
import game.engine.QuestionData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by Sasho on 17.4.2016 г..
 */
public class LevelWizard {
    Main main;
    QuestionData qd;
    int questionStatus;
    Stage primaryStage;
    String question;
    int correctAnswer;
    public LevelWizard(Stage stage,Main main){
        this.main = main;
        qd = new QuestionData();
        primaryStage = stage;
        questionStatus = 0;
        start();
    }
    protected void start(){
        BorderPane sceneLayout = new BorderPane();
        GridPane layout = new GridPane();
        sceneLayout.setStyle(Main.backgroundStyle);
        GridPane right = new GridPane();
        Label uiNumQuestion = new Label((+questionStatus+""));
        Label uiNumQuestionText = new Label("Questions in QuestionData: ");
        right.add(uiNumQuestionText,0,0);
        right.add(uiNumQuestion,1,0);
        //uiNumQuestion.setTextFill(Color.BLACK);
        uiNumQuestionText.setTextFill(Color.WHITESMOKE);
        uiNumQuestion.setTextFill(Color.WHITESMOKE);
        TextField tf = new TextField();
        Label tftext = new Label("Question");
        tftext.setTextFill(Color.WHITESMOKE);
        Label tf2text = new Label("Correct Answer");
        tf2text.setTextFill(Color.WHITESMOKE);
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "A",
                        "B",
                        "C"
                );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.getSelectionModel().selectFirst();
        Label answerAText = new Label("Answer A:");
        TextField tfAnswerA = new TextField();
        answerAText.setTextFill(Color.WHITESMOKE);
        Label answerBText = new Label("Answer B:");
        TextField tfAnswerB = new TextField();
        answerBText.setTextFill(Color.WHITESMOKE);
        Label answerCText = new Label("Answer C:");
        TextField tfAnswerC = new TextField();
        answerCText.setTextFill(Color.WHITESMOKE);

        Button set = new Button("Add Question to data");
        set.setOnAction(e-> {
                    question = tf.getText();
                    correctAnswer = comboBox.getSelectionModel().getSelectedIndex();
                    int result = qd.addQuestion(question, correctAnswer, tfAnswerA.getText(), tfAnswerB.getText(), tfAnswerC.getText());
                    if (result == 1) {
                        if(questionStatus!=qd.numOfQuestions) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Problem with the question");
                            alert.setHeaderText("Problem with the question");
                            alert.setContentText("The question is not proper! Try again!");
                            alert.showAndWait();
                        } else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Problem with the QuestionData");
                            alert.setHeaderText("Problem with the QuestionData");
                            alert.setContentText("You can add only " + qd.numOfQuestions + " questions. You can try to save it to file now!");
                            alert.showAndWait();
                        }

                    } else {
                        uiNumQuestion.setText(++questionStatus + "");

                    }
        });
        GridPane bottom = new GridPane();
        Label filenameText = new Label("Filename: ");
        filenameText.setTextFill(Color.WHITESMOKE);
        TextField tfFilename = new TextField();
        Button save = new Button("Save QuestionData to file");
        save.setOnAction(e->{
            if(questionStatus!=qd.numOfQuestions) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Problem with the questions count");
                alert.setHeaderText("Problem with the questions count");
                alert.setContentText("The question count is not proper. You cant save QuestionData to file if you have " + qd.numOfQuestions +
                        " questions added. You have only " + questionStatus);
                alert.showAndWait();
            }else {
                String result = qd.saveQuestions(tfFilename.getText()+".data");
                if (result.equals("Success")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successful");
                    alert.setHeaderText("The QuestionData convert to file successfully.");
                    alert.setContentText("Filename: " + tfFilename.getText() + "!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Problem with the QuestionData");
                    alert.setHeaderText("Problem with the QuestionData");
                    alert.setContentText(result);
                    alert.showAndWait();
                }
            }
        });

        Button toMainScreen = new Button("To MainScreen");
        Main.setButtonStyle(toMainScreen);
        //a.setMinSize(300,80);
        //a.setMaxSize(300,80);
        //a.setBackground(new Background(new BackgroundFill(Color.DARKMAGENTA,new CornerRadii(5),Insets.EMPTY)));
        //a.setTextFill(Color.WHITESMOKE);
        //a.setWrapText(true);
        toMainScreen.setOnAction(e->{
            Alert sure = new Alert(Alert.AlertType.CONFIRMATION);
            sure.setTitle("Are you sure you want to quit?");
            sure.setHeaderText("Are you sure you want to quit the Level Wizard?");
            sure.setContentText("The current QuestionData will be lost. ");
            Optional<ButtonType> result = sure.showAndWait();
            if(result.get().equals(ButtonType.OK)) {
                main.start(primaryStage);
            }else{

            }

        });



        bottom.add(filenameText,0,0);
        bottom.add(tfFilename,1,0);
        bottom.add(save,1,1);

        layout.add(tf,1,0);
        layout.add(tftext,0,0);
        layout.add(comboBox,1,1);
        layout.add(tf2text,0,1);
        layout.add(answerAText,0,2);
        layout.add(tfAnswerA,1,2);
        layout.add(answerBText,0,3);
        layout.add(tfAnswerB,1,3);
        layout.add(answerCText,0,4);
        layout.add(tfAnswerC,1,4);
        layout.add(set,1,5);
        layout.setPadding(new Insets(0,0,0,120));

        sceneLayout.setCenter(layout);
        sceneLayout.setLeft(right);
        sceneLayout.setBottom(bottom);
        sceneLayout.setRight(toMainScreen);
        Scene scene = new Scene(sceneLayout,800,600);
        primaryStage.setScene(scene);
    }
}
