/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.ui;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 *
 * @author juliagro
 */
public class PasianssiUi extends Application {
    
    int seconds;
    int delay = 1000;
    int step = 1000;
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Solitaire");
        
        StackPane layout = new StackPane();
        
        BorderPane borderPane = new BorderPane();
        HBox timeBar = new HBox();
        timeBar.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY,Insets.EMPTY)));
        Text timeText = new Text("Time: ");
        timeBar.getChildren().add(timeText);
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                seconds++;
                int min = seconds / 60;
                int sec = seconds % 60;
                timeText.setText("Time: Minutes: " + min + " Seconds: " + sec);
            }
        };
        
        timer.scheduleAtFixedRate(task, delay, step);
        
        HBox menu = new HBox();
        menu.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY,Insets.EMPTY)));
        Text menuText = new Text("Menu");
        menu.getChildren().add(menuText);
        
        borderPane.setTop(menu);
        borderPane.setBottom(timeBar);
        borderPane.setCenter(layout);
        
        Scene scene = new Scene(borderPane, 850, 600,Color.GREEN);
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
    }
    
}
