/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Group;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import pasianssi.domain.Card;
import pasianssi.domain.CardStack;
import pasianssi.domain.StartingSituation;
import pasianssi.domain.Card.CardGroup;


/**
 *
 * @author juliagro
 */
public class PasianssiUi extends Application {
    
    int seconds;
    int delay = 1000;
    int step = 1000;
    private Timer timer;
    private final Paint CARD_BACK = Paint.valueOf("linear-gradient(from 15% 15% to 100% 100%, #0066ff, #66ffff)");
    private final double HEIGHT = 150;
    private final double WIDTH = 100;
    private final double WINWIDTH = 900;
    private final double WINHEIGHT = 650;
    private final double SEPARATION = 20;
    private final CardStack deck = new CardStack(0, 0);
    private final CardStack waste = new CardStack(1, 0);
    private final CardStack endStack1 = new CardStack(3, 0);
    private final CardStack endStack2 = new CardStack(4, 0);
    private final CardStack endStack3 = new CardStack(5, 0);
    private final CardStack endStack4 = new CardStack(6, 0);
    private final CardStack table1 = new CardStack(0, 1);
    private final CardStack table2 = new CardStack(1, 1);
    private final CardStack table3 = new CardStack(2, 1);
    private final CardStack table4 = new CardStack(3, 1);
    private final CardStack table5 = new CardStack(4, 1);
    private final CardStack table6 = new CardStack(5, 1);
    private final CardStack table7 = new CardStack(6, 1);
    private List<CardStack> stacks = new ArrayList();
    private final StartingSituation situation = new StartingSituation(deck, waste, endStack1, endStack2, endStack3, endStack4, table1, table2, table3, table4, table5, table6, table7);
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        newGame(primaryStage);
        
    }
    
    public void newGame(Stage primaryStage) {
        situation.newDeal();
        primaryStage.setTitle("Solitaire");

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox timeBar = new HBox();
        timeBar.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        Text timeText = new Text("Time: ");
        timeBar.getChildren().add(timeText);

        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                seconds++;
                int min = seconds / 60;
                int sec = seconds % 60;
                timeText.setText("Time: Minutes: " + min + " Seconds: " + sec);
            }
        };

        timer.scheduleAtFixedRate(task, delay, step);

        MenuBar menu = new MenuBar();
        Menu game = new Menu("Game");
        MenuItem menuNew = new MenuItem("New Deal");
        MenuItem menuQuit = new MenuItem("Quit");
        MenuItem menuSame = new MenuItem("Same Deal");
        menuQuit.setOnAction(e -> Platform.exit());
        menuNew.setOnAction(e -> restartNew(primaryStage, borderPane));
        menuSame.setOnAction(e -> restartSame(primaryStage, borderPane));

        game.getItems().addAll(menuNew, menuSame, menuQuit);
        menu.getMenus().add(game);

        borderPane.setTop(menu);
        borderPane.setBottom(timeBar);
        borderPane.setCenter(drawStartingSituation());

        Scene scene = new Scene(borderPane, WINWIDTH, WINHEIGHT);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
    
    public void sameGame(Stage primaryStage) {
        situation.sameDeal();
        primaryStage.setTitle("Solitaire");

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox timeBar = new HBox();
        timeBar.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        Text timeText = new Text("Time: ");
        timeBar.getChildren().add(timeText);

        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                seconds++;
                int min = seconds / 60;
                int sec = seconds % 60;
                timeText.setText("Time: Minutes: " + min + " Seconds: " + sec);
            }
        };

        timer.scheduleAtFixedRate(task, delay, step);

        MenuBar menu = new MenuBar();
        Menu game = new Menu("Game");
        MenuItem menuNew = new MenuItem("New Deal");
        MenuItem menuQuit = new MenuItem("Quit");
        MenuItem menuSame = new MenuItem("Same Deal");
        menuQuit.setOnAction(e -> Platform.exit());
        menuNew.setOnAction(e -> restartNew(primaryStage, borderPane));
        menuSame.setOnAction(e -> restartSame(primaryStage, borderPane));

        game.getItems().addAll(menuNew, menuSame, menuQuit);
        menu.getMenus().add(game);

        borderPane.setTop(menu);
        borderPane.setBottom(timeBar);
        borderPane.setCenter(drawStartingSituation());

        Scene scene = new Scene(borderPane, WINWIDTH, WINHEIGHT);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
    
    public void restartNew(Stage stage, BorderPane borderPane) {
        cleanup(borderPane);
        newGame(stage);
    }

    public void restartSame(Stage stage, BorderPane borderPane) {
        cleanup(borderPane);
        sameGame(stage);
    }
    
    public void cleanup(BorderPane borderPane) {
        borderPane.getChildren().clear();
        timer.cancel();
        timer.purge();
        seconds = 0;
    }
    
    public Group drawStartingSituation() {
        Group group = new Group();
        stacks = situation.getListOfAllStacks();
        for (CardStack stack : stacks) {
            drawStack(group, stack);
        }
        return group;
    }
    
    public void drawCard(Card card) {
        Rectangle rec = new Rectangle();
        CardGroup group = new CardGroup(card);
        if (card.getStack().getLocationY() == 0) {
            group.setLayoutY(0);
        } else if (card.getStack().sizeOfTheStack() == 0) {
            group.setLayoutY(0);
        } else {
            group.setLayoutY(card.getStack().cards().indexOf(card) * (SEPARATION / 2));
        }
        rec.setWidth(WIDTH);
        rec.setHeight(HEIGHT);
        group.getChildren().add(rec);
        if (card.isTheCardFaceUp()) {
            rec.setFill(Color.WHITE);
            rec.setStroke(Color.BLACK);
            //middle text
            Text text = new Text(card.getValue().str + "\n" + card.getColor().str);
            if (card.getColor().isBlack()) {
                text.setFill(Color.BLACK);
            } else {
                text.setFill(Color.RED);
            }
            text.setTextAlignment(TextAlignment.CENTER);
            text.relocate(WIDTH / 2 - text.getBoundsInLocal().getWidth() / 2, HEIGHT / 2 - text.getBoundsInLocal().getHeight() / 2);
            group.getChildren().add(text);
            //up text
            Text upText = new Text(card.getValue().str + " " + card.getColor().str);
            if (card.getColor().isBlack()) {
                upText.setFill(Color.BLACK);
            } else {
                upText.setFill(Color.RED);
            }
            upText.relocate(WIDTH / 2 - text.getBoundsInLocal().getWidth() / 2, HEIGHT / 2 - text.getBoundsInLocal().getHeight() / 2 - 55);
            group.getChildren().add(upText);
            //down text
            Text downText = new Text(card.getValue().str + " " + card.getColor().str);
            if (card.getColor().isBlack()) {
                downText.setFill(Color.BLACK);
            } else {
                downText.setFill(Color.RED);
            }
            downText.relocate(WIDTH / 2 - (text.getBoundsInLocal().getWidth() / 2), HEIGHT / 2 - text.getBoundsInLocal().getHeight() / 2 + 65);
            group.getChildren().add(downText);
        } else {
            rec.setFill(CARD_BACK);
            rec.setStroke(Color.BLACK);
        }

    }
    
    public void drawEmpty(Group group) {
        Rectangle rec = new Rectangle();
        rec.setWidth(WIDTH);
        rec.setHeight(HEIGHT);
        rec.setX(0);
        rec.setY(0);
        rec.setFill(Color.TRANSPARENT);
        rec.setStroke(Color.BLACK);
        group.getChildren().add(rec);

    }
    
    public void drawStack(Group group, CardStack stack) {
        Group group1 = new Group();
        List<Card> cards = stack.cards();
        stack.setBigGroup(group);
        stack.setGroup(group1);
        double locx = (1 + stack.getLocationX()) * SEPARATION + stack.getLocationX() * WIDTH;
        double locy = (1 + stack.getLocationY()) * SEPARATION + stack.getLocationY() * HEIGHT;
        group1.relocate(locx, locy);
        if (stack.sizeOfTheStack() == 0) {
            drawEmpty(group1);
        } else {
            drawEmpty(group1);
            for (Card card : cards) {
                drawCard(card);
                group1.getChildren().add(card.getGroup());
            }
        }
        group.getChildren().add(group1);

    }
    
}
