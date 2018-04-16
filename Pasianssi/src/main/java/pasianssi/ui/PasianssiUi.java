/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.ui;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
    private Timer timer;
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        newGame(primaryStage);
        
    }
    
    public void newGame(Stage primaryStage) {
        primaryStage.setTitle("Solitaire");
        
        StackPane layout = new StackPane();
        
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox timeBar = new HBox();
        timeBar.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY,Insets.EMPTY)));
        Text timeText = new Text("Time: ");
        timeBar.getChildren().add(timeText);
        
        timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                seconds++;
                int min = seconds / 60;
                int sec = seconds % 60;
                timeText.setText("Time: Minutes: " + min + " Seconds: " + sec);
            }
        };
        
        timer.scheduleAtFixedRate(task, delay, step);
        
        MenuBar menu = new MenuBar();
        Menu game = new Menu("Game");
        MenuItem menuNew = new MenuItem("New");
        MenuItem menuQuit = new MenuItem("Quit");
        menuQuit.setOnAction(e -> Platform.exit());
        menuNew.setOnAction(e -> restart(primaryStage, borderPane));
        
        game.getItems().addAll(menuNew, menuQuit);
        menu.getMenus().add(game);
        
        borderPane.setTop(menu);
        borderPane.setBottom(timeBar);
        borderPane.setCenter(layout);
        
        Scene scene = new Scene(borderPane, 850, 600);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
    public void restart(Stage stage, BorderPane borderPane) {
        cleanup(borderPane);
        newGame(stage);
    }
    
    public void cleanup(BorderPane borderPane) {
        borderPane.getChildren().clear();
        timer.cancel();
        timer.purge();
        seconds = 0;
    }
    
}
