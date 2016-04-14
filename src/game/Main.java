package game;

import game.engine.Game;
import game.engine.Question;
import game.engine.QuestionData;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public static final String backgroundStyle = "-fx-background-image: url(\"background.jpg\"); -fx-background-size:800";


    @Override
    public void start(Stage primaryStage){

        Image img = new Image("background.jpg");
        Background bck = new Background(new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT));





        //Scene init
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);
        layout.setPadding(new Insets(250,0,0,0));
        //layout.setBackground(bck);
        layout.setStyle(backgroundStyle);



        Button a = new Button("Start");
        setButtonStyle(a);
        a.setOnAction(e->{
            Start start = new Start(this);
            try {
                start.start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button b = new Button("Level Wizard");
        setButtonStyle(b);


        Button c = new Button("Exit");
        setButtonStyle(c);
        c.setOnAction(e->primaryStage.close());

        layout.getChildren().addAll(a,b,c);




        Scene mainScene = new Scene(layout,800,600);


        //Stage init
        primaryStage.setTitle("Who wants to be a programmer");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);




        //SHOW
        primaryStage.show();

    }
    public static void setButtonStyle(Button a){
        a.setMinWidth(100);
        a.setMaxWidth(100);
        a.setBackground(new Background(new BackgroundFill(Color.GRAY,new CornerRadii(5),Insets.EMPTY)));
        a.setBorder(new Border(new BorderStroke(Color.DARKGRAY,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderStroke.THIN)));
        a.setTextFill(Color.WHITESMOKE);
    }


    public static void main(String[] args) {
        launch(args);
    }




}
