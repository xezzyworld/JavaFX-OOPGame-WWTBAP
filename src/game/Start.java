package game;

import game.engine.Question;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Sasho on 14.4.2016 г..
 */
public class Start {
    private final String extensiondbfile = ".data";
    Controller game;
    private Stage primaryStage;
    private String name = "default";
    private String filename = "default";
    private Main main;
    private Label question;
    private ListView<String> answers;
    private Label uiLevel;
    private Label uiQuestion;
    public String[] levelArray= {"Basic","Fundamental","Advanced Front-End","Advanced Back-End"};

    public Start(Main main){
        this.main = main;
    }

public void getName(){
    Stage getName = new Stage();
    getName.initModality(Modality.APPLICATION_MODAL);
    getName.setTitle("Who wants to be a programmer - Name of Player");
    GridPane layout = new GridPane();
    TextField tf = new TextField();
    Label tftext = new Label("Your name:");
    TextField tf2 = new TextField("default");
    Label tf2text = new Label("Question data name:");
    tf.setPromptText("Put your name");
    Button set = new Button("Set");
    set.setOnAction(e->{
        if(!tf.getText().equals(""))
        name = tf.getText();
        filename = tf2.getText();
        getName.close();

    });
    layout.add(tf,1,0);
    layout.add(tftext,0,0);
    layout.add(tf2,1,1);
    layout.add(tf2text,0,1);
    layout.add(set,1,2);

    Scene scene = new Scene(layout,300,200);
    getName.setScene(scene);
    getName.showAndWait();
    getName.setResizable(false);


}



    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        getName();
        game = new Controller(name,filename+extensiondbfile);
        System.out.println(game.getLoadStatus());
        if(game.getLoadStatus().equals("Success")){

        }
        else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error with QDF");
            error.setHeaderText("Error with Question Database File");
            error.setContentText("There was an error with the question database file called " + "\""+filename+extensiondbfile+"\""
            +"\n"+game.getLoadStatus());
            error.showAndWait();
            return;
        }
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
            sure.setHeaderText("Are you sure you want to quit this session of Who Wants To Be A Programmer?");
            Optional<ButtonType> result = sure.showAndWait();
            if(result.get().equals(ButtonType.OK)) {
                main.start(primaryStage);
            }else{

            }

        });

        //Scene init
        BorderPane layout = new BorderPane();
        layout.setStyle(Main.backgroundStyle);

        question = new Label();
        question.setFont(Font.font(22));
        question.setTextFill(Color.WHITESMOKE);
        question.setTranslateY(210);
        question.setWrapText(true);
        question.setText("You are developing an application that contains a class named TheaterCustomer and a method named ProcessTheaterCustomer.");// The ProcessTheaterCustomer() method accepts a TheaterCustomer object as the input parameter. You have the following requirements: - Store the TheaterCustomer objects in a collection. - Ensure that the ProcessTheaterCustomer() method processes the TheaterCustomer objects in the order in which they are placed into the collection. You need to meet the requirements. What should you do? ");
        question.setBackground(new Background(new BackgroundFill(Color.DARKMAGENTA, CornerRadii.EMPTY, Insets.EMPTY)));


        answers = new ListView<>();

        answers.setOnMousePressed(e->{
            int result = game.nextAnswer(answers.getSelectionModel().getSelectedIndex());
            if(result==0){
                drawNextQuestion(game.nextQuestion());
                updateUI();
            }
            if(result==1){
                win();
            }
            if(result==-1){
                lost();
            }
        });

        //answers.getItems().add(new Text("asd Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex"));
        answers.setPrefSize(400,75);
        answers.setMaxSize(400,75);
        answers.setTranslateX(200);
        GridPane ui = new GridPane();
        layout.setBottom(answers);
        layout.setCenter(question);
        layout.setRight(toMainScreen);
        layout.setLeft(ui);
        Label uiQuestionText = new Label("Question:");
        uiQuestionText.setTextFill(Color.WHITESMOKE);
        ui.add(uiQuestionText,0,0);
        uiQuestion = new Label(game.game.getQuestionStatus()+"");
        uiQuestion.setTextFill(Color.WHITESMOKE);
        ui.add(uiQuestion,1,0);

        Label uiLevelText = new Label("Level:");
        uiLevelText.setTextFill(Color.WHITESMOKE);
        ui.add(uiLevelText,0,1);
        uiLevel = new Label(levelArray[game.game.getQuestionStatus()/3]);
        uiLevel.setTextFill(Color.WHITESMOKE);
        ui.add(uiLevel,1,1);
        Label tips = new Label("TIPS:");
        tips.setTextFill(Color.WHITESMOKE);
        ui.add(tips,0,2);
        Button callNakov = new Button("Call Nakov");
        Main.setButtonStyle(callNakov);
        callNakov.setMaxWidth(callNakov.getMinWidth()*2);
        callNakov.setOnAction(e->{
            doTip(game.Tip("CallNakov"));
        });
        Button fifty = new Button("50/50");
        Main.setButtonStyle(fifty);
        fifty.setMaxWidth(fifty.getMinWidth()*2);
        fifty.setOnAction(e->{
            doTip(game.Tip("Fifty"));
        });
        Button HFTS = new Button("Help from the lectures");
        Main.setButtonStyle(HFTS);
        HFTS.setMaxWidth(HFTS.getMinWidth()*2);
        HFTS.setOnAction(e->{
            doTip(game.Tip("HFTS"));
        });
        ui.add(callNakov,0,3);
        ui.add(fifty,0,4);
        ui.add(HFTS,0,5);
        Scene mainScene = new Scene(layout,800,600);


        //Stage init
        primaryStage.setScene(mainScene);

        drawNextQuestion(game.nextQuestion());


    }
    public void drawNextQuestion(Question question){
        this.question.setText(question.getQuestion());
        this.answers.setItems(FXCollections.observableArrayList(question.getAnswers()));
    }
    public void updateUI(){
        uiLevel.setText(levelArray[game.game.getQuestionStatus()/3]);
        uiQuestion.setText(game.game.getQuestionStatus()+"");
    }
    public void lost(){
        Alert sure = new Alert(Alert.AlertType.INFORMATION);
        sure.setTitle("YOU LOST");
        sure.setHeaderText("Sorry " + name + " you lost the game!" );
        sure.showAndWait();
            main.start(primaryStage);

    }
    public void win(){
        Alert sure = new Alert(Alert.AlertType.INFORMATION);
        sure.setTitle("Congratz");
        sure.setHeaderText("Congratz " + name + " you WIN the game!" );
        sure.showAndWait();
        main.start(primaryStage);
    }
    public void doTip(String text){
        Alert sure = new Alert(Alert.AlertType.INFORMATION);
        sure.setTitle("TIP");
        sure.setHeaderText(text);
        sure.showAndWait();
    }
}
