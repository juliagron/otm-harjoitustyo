/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pasianssi.dao.Database;
import pasianssi.dao.HighScoreDao;

import pasianssi.domain.Card;
import pasianssi.domain.CardStack;
import pasianssi.domain.StartingSituation;
import pasianssi.domain.Card.CardGroup;
import pasianssi.domain.HighScore;
import pasianssi.domain.LegalMove;

/**
 * Ohjelman pääluokka, joka tekee käyttöliittymän
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
    private final double WINWIDTH = 1000;
    private final double WINHEIGHT = 750;
    private final double SEPARATION = 40;
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
    private List<Card> inDrag = new ArrayList();
    private LegalMove move = new LegalMove();
    private Database database;
    private HighScoreDao highDao;
    private MenuItem menuNew;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        database = new Database();
        highDao = new HighScoreDao(database);
        situation.newDeal();
        game(primaryStage);

    }

    /**
     * Metodi, joka luo pelin aloitustilanteen eli sijoittaa komponentit
     * oikeille paikoilleen
     *
     * @param primaryStage start-metodin Stage-parametri, joka sisältää ikkunan
     * näkymän
     */
    public void game(Stage primaryStage) {
        primaryStage.setTitle("Solitaire");

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox timeBar = new HBox();
        timeBar.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        Text timeText = new Text("Time: ");
        timeBar.getChildren().add(timeText);

        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
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
        Label allUp = new Label("All up");
        allUp.setOnMouseClicked(e -> {
            try {
                allUp();
            } catch (SQLException ex) {
                Logger.getLogger(PasianssiUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Label highscores = new Label("High scores");
        highscores.setOnMouseClicked(e -> {
            try {
                showScores();
            } catch (SQLException ex) {
                Logger.getLogger(PasianssiUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Menu high = new Menu();
        high.setGraphic(highscores);

        Menu allup = new Menu();
        allup.setGraphic(allUp);

        menuNew = new MenuItem("New Deal");
        MenuItem menuQuit = new MenuItem("Quit");
        MenuItem menuSame = new MenuItem("Same Deal");

        menuQuit.setOnAction(e -> Platform.exit());
        menuNew.setOnAction(e -> restartNew(primaryStage, borderPane));
        menuSame.setOnAction(e -> restartSame(primaryStage, borderPane));

        game.getItems().addAll(menuNew, menuSame, menuQuit);
        menu.getMenus().addAll(game, allup, high);

        borderPane.setTop(menu);
        borderPane.setBottom(timeBar);

        Group group = new Group();
        drawStartingSituation(group);
        borderPane.setCenter(group);

        Rectangle r = new Rectangle();
        double locx = (1 + stacks.get(0).getLocationX()) * SEPARATION + stacks.get(0).getLocationX() * WIDTH;
        double locy = (1 + stacks.get(0).getLocationY()) * SEPARATION + stacks.get(0).getLocationY() * HEIGHT;
        r.setX(locx);
        r.setY(locy);
        r.setHeight(HEIGHT);
        r.setWidth(WIDTH);
        r.setFill(Color.TRANSPARENT);
        r.setOnMouseClicked(e -> clickingTheDeck());
        r.setOnMouseEntered(e -> {
            r.setCursor(Cursor.HAND);
        });
        group.getChildren().add(r);

        Scene scene = new Scene(borderPane, WINWIDTH, WINHEIGHT);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Metodi aloittaa uuden pelin, jossa kortit sekoitetaan
     *
     * @param stage start-metodin Stage-parametri, jok sisältää ikkunan näkymän
     * @param borderPane game-metodin luoma olio, joka vastaa näkymän
     * komponenttien asettelusta
     */
    public void restartNew(Stage stage, BorderPane borderPane) {
        cleanup(borderPane);
        situation.newDeal();
        game(stage);
    }

    /**
     * Metodi, joka aloittaa uuden pelin, jossa kortit ovat samassa
     * järjestyksessä kuin edellisessä pelissä
     *
     * @param stage start-metodin Stage-parametri, jok sisältää ikkunan näkymän
     * @param borderPane game-metodin luoma olio, joka vastaa näkymän
     * komponenttien asettelusta
     */
    public void restartSame(Stage stage, BorderPane borderPane) {
        cleanup(borderPane);
        situation.sameDeal();
        game(stage);
    }

    /**
     * Metodi tyhjentää näkymän komponenteista ja aloittaa kellon alusta
     *
     * @param borderPane näkymästä vastaava olio
     */
    public void cleanup(BorderPane borderPane) {
        borderPane.getChildren().clear();
        timer.cancel();
        timer.purge();
        seconds = 0;
    }

    /**
     * Metodi vastaa aloitustilanteen piirtämisestä
     *
     * @param group näkymän keskellä sijaitseva olio, johon korttipinot
     * sijoitetaan
     */
    public void drawStartingSituation(Group group) {
        stacks = situation.getListOfAllStacks();
        stacks.stream().forEach((stack) -> {
            drawStack(group, stack);
        });
    }

    /**
     * Metodi yhden kortin piirtämiseen kortin arvon, värin ja sijainnin mukaan
     *
     * @param card piirrettävä kortti
     */
    public void drawCard(Card card) {
        Rectangle rec = new Rectangle();
        CardGroup group = new CardGroup(card);
        if (card.getStack().getLocationY() == 0) {
            if (card.getStack().getLocationX() == 1) {
                group.setLayoutX(0);
                group.setLayoutY(0);
            } else {
                group.setLayoutY(0);
            }
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

    /**
     * Metodi, joka piirtää tyhjän pinon
     *
     * @param group olio, jossa sijaitsee yksi korttipino
     */
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

    /**
     * Metodi yhden korttipinon piirtämiseen
     *
     * @param group olio, jossa sijaitsee yksi pino kortteineen
     * @param stack piirrettävä korttipino
     */
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
            cards.stream().map((c) -> {
                drawCard(c);
                return c;
            }).map((c) -> {
                group1.getChildren().add(c.getGroup());
                return c;
            }).filter((c) -> (c.isTheCardFaceUp())).forEach((c) -> {
                makeDraggable(c);
            });
        }
        group.getChildren().add(group1);
        if (stack.isTheStackOnTheTable() || stack.isTheStackOneOfTheEndStacks()) {
            makeTarget(stack);
        }

    }

    /**
     * Metodi, joka piirtää korttipinon uudelleen, kun kortteja on siirretty
     * pinosta toiseen
     *
     * @param stack uudelleenpiirrettävä korttipino
     */
    public void reDrawStack(CardStack stack) {
        Group group = stack.getGroup();
        if (stack.isTheStackOnTheTable() && !"1".equals(stack.sizeOfTheStack())) {
            if (stack.topCard() != null && !stack.topCard().isTheCardFaceUp()) {
                stack.topCard().setTheCardFaceUp(true);
            }
        }

        group.getChildren().clear();
        Group bigGroup = stack.getBigGroup();
        group.toBack();
        bigGroup.getChildren().removeAll(group);
        stack.setGroup(new Group());
        drawStack(bigGroup, stack);
        stack.getGroup().toBack();
    }

    /**
     * Metodi, joka määrittelee miten kortteja siirretään käsipakasta sen
     * viereen, kun käsipakkaa klikataan hiirellä, piirtää molemmat pakat
     * uudelleen
     */
    public void clickingTheDeck() {
        if (stacks.get(0).sizeOfTheStack() == 0) {
            //return all the cards to the deck
            List<Card> copy = new ArrayList();
            copy.addAll(stacks.get(1).cards());
            for (int i = copy.size() - 1; i >= 0; i--) {
                copy.get(i).setTheCardFaceUp(false);
                stacks.get(1).cards().remove(copy.get(i));
                stacks.get(0).cards().add(copy.get(i));
            }
        } else {
            //draw 1
            Card c = stacks.get(0).cards().get(stacks.get(0).sizeOfTheStack() - 1);
            c.getGroup().getChildren().clear();
            stacks.get(0).cards().remove(c);
            c.setTheCardFaceUp(true);
            CardGroup cardGroup = new CardGroup(c);
            stacks.get(1).cards().add(c);
            c.setStack(stacks.get(1));
            List<Card> list = Arrays.asList(c);
        }
        reDrawStack(stacks.get(0));
        reDrawStack(stacks.get(1));

    }

    /**
     * Luokka hiiren paikan määrittamiseen ikkunassa
     */
    public static class MouseLocation {

        /**
         * Paramatri, joka määrittää hiiren x-koordinaatin
         */
        public double x;

        /**
         * Parametri, joka määrittää hiiren y-koordinaatit
         */
        public double y;
    }

    /**
     * Metodi kuuntelee 'pudotetaanko' pinon päälle kortteja ja siirtää ne, jos
     * siirto on laillinen
     *
     * @param stack pino, jonka täälle voi 'pudottaa' kortteja
     */
    public void makeTarget(CardStack stack) {
        Group source = stack.getGroup();
        source.setOnMouseDragReleased((final MouseDragEvent e) -> {
            if (!inDrag.isEmpty()) {
                CardStack targetStack = stack;
                Card sourceCard = inDrag.get(0);
                CardStack sourceStack = sourceCard.getStack();
                if (sourceStack != targetStack) {
                    List<Card> copy = new ArrayList();
                    copy.addAll(inDrag);
                    if (move.isTheMoveLegal(targetStack, copy)) {
                        try {
                            moveCards(copy, targetStack);
                        } catch (SQLException ex) {
                            Logger.getLogger(PasianssiUi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        inDrag.clear();
                        copy.clear();
                    }
                }
                e.consume();
            }
        });
    }

    /**
     * Metodi, joka siirtää annetun listan kortit kohdepinoon ja piirtää pinot
     * uudelleen
     *
     * @param list lista siirrettävistä korteista
     * @param targetStack pino, johon kortteja ollaan siirtämässä
     * @throws SQLException
     */
    public void moveCards(List<Card> list, CardStack targetStack) throws SQLException {
        if (list.isEmpty()) {
            return;
        }

        CardStack sourceStack = list.get(0).getStack();
        list.stream().map((card) -> {
            sourceStack.removeCardFromTheStack(card);
            return card;
        }).map((card) -> {
            targetStack.addCardToTheStack(card);
            return card;
        }).map((card) -> {
            CardGroup cardGroup = new CardGroup(card);
            targetStack.getGroup().getChildren().add(cardGroup);
            makeDraggable(card);
            return card;
        }).forEach((card) -> {
            card.setStack(targetStack);
        });
        reDrawStack(sourceStack);
        reDrawStack(targetStack);

        checkFinish();
    }

    /**
     * Metodi vastaa kortin siirtämisestä lopetuspinoon, käytetään vain
     * tapauksessa, jossa korttia on klikattu kahdesti
     *
     * @param card siirrettävä kortti
     * @return true, jos siirto onnistui, muuten false
     * @throws SQLException
     */
    public boolean moveToEndStack(Card card) throws SQLException {
        List<Card> toPut = Arrays.asList(card);
        for (CardStack stack : stacks) {
            if (stack.isTheStackOneOfTheEndStacks()) {
                if (move.isTheMoveLegal(stack, toPut)) {
                    moveCards(toPut, stack);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodi vastaa korttien siirtämisestä, raahamalla tai klikkaamalla,
     * pinosta toiseen, muuttaa kursorin kuvaa, jos se menee kortin päälle
     *
     * @param card kortti, jota voi siirtää
     */
    public void makeDraggable(Card card) {
        final MouseLocation lastLoc = new MouseLocation();
        CardGroup source = card.getGroup();
        if (card != null) {
            source.setOnDragDetected(e -> {
                source.startFullDrag();
                e.consume();
            });
            source.setOnMousePressed(e -> {
                if (e.getClickCount() == 2) {
                    Card sourceCard = source.getCard();
                    if (sourceCard.isOnTopOfTheStack()) {
                        try {
                            moveToEndStack(sourceCard);
                        } catch (SQLException ex) {
                            Logger.getLogger(PasianssiUi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    Card sourceCard = source.getCard();
                    CardStack sourceStack = sourceCard.getStack();
                    lastLoc.x = e.getSceneX();
                    lastLoc.y = e.getSceneY();
                    inDrag.add(sourceCard);
                    if (sourceCard.getStack().isTheStackOnTheTable()) {
                        sourceStack.getRestAfter(sourceCard).stream().forEach((c) -> {
                            inDrag.add(c);
                        });
                    }
                    source.setCursor(Cursor.CLOSED_HAND);
                    source.setMouseTransparent(true);
                    sourceStack.getGroup().toFront();
                }
                e.consume();
            });
            source.setOnMouseDragged((MouseEvent mouseEvent) -> {
                double deltax = mouseEvent.getSceneX() - lastLoc.x;
                double deltay = mouseEvent.getSceneY() - lastLoc.y;
                inDrag.stream().map((c) -> {
                    double newx = c.getGroup().getLayoutX() + deltax;
                    double newy = c.getGroup().getLayoutY() + deltay;
                    c.getGroup().setLayoutX(newx);
                    c.getGroup().setLayoutY(newy);
                    return c;
                }).map((_item) -> {
                    lastLoc.x = mouseEvent.getSceneX();
                    return _item;
                }).forEach((_item) -> {
                    lastLoc.y = mouseEvent.getSceneY();
                });
                mouseEvent.consume();
            });
            source.setOnMouseEntered(e -> {
                source.setCursor(Cursor.HAND);
            });
            source.setOnMouseReleased(e -> {
                source.setMouseTransparent(false);
                CardStack sourceStack = source.getCard().getStack();
                sourceStack.getGroup().toBack();
                if (!inDrag.isEmpty()) {
                    Card c = inDrag.get(0);
                    inDrag.clear();
                    reDrawStack(c.getStack());
                }
                e.consume();
            });
        }
    }

    /**
     * Metodi tarkistaa onko peli loppu eli onko kaikki kortit lopetuspinoissa,
     * tallentaa pelin tuloksen, jos se sijoittuu viiden parhaan kärkeen
     *
     * @throws SQLException
     */
    public void checkFinish() throws SQLException {
        boolean endsFull = true;
        for (CardStack stack : stacks) {
            if (stack.isTheStackOneOfTheEndStacks()) {
                if (stack.sizeOfTheStack() != 13) {
                    endsFull = false;
                }
            }
        }

        if (endsFull) {
            timer.cancel();
            int howMany = highDao.howManyScores();
            List<HighScore> scores = new ArrayList();
            if (howMany < 5) {
                scores = highDao.findAll();
            } else {
                scores = highDao.findFive();
            }
            int last = 0;
            if (howMany == 0 || scores.size() < 5) {
                last = 0;
            } else {
                last = scores.get(scores.size() - 1).getTime();
            }

            if (seconds > last) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("New Highscore");
                dialog.setHeaderText(null);
                dialog.setContentText("Please enter your name:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    List<HighScore> newScores = new ArrayList();
                    HighScore newScore = new HighScore(result.get(), seconds);
                    highDao.save(newScore);
                    if (highDao.howManyScores() < 5) {
                        newScores = highDao.findAll();
                    } else {
                        newScores = highDao.findFive();
                    }
                    String high = "";
                    for (int i = 0; i < highDao.howManyScores(); i++) {
                        int min = newScores.get(i).getTime() / 60;
                        int sec = newScores.get(i).getTime() % 60;
                        String string = i + 1 + ".    " + newScores.get(i).getName() + "  " + min + ":" + sec + "\n";
                        high = high + string;
                    }
                    Alert alert = new Alert(Alert.AlertType.NONE, high, ButtonType.OK, ButtonType.CANCEL);
                    alert.setTitle("High Scores");
                    alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> menuNew.fire());
                }
            } else {
                int min = seconds / 60;
                int sec = seconds % 60;
                Alert alert = new Alert(Alert.AlertType.NONE, "Your time: " + min + ":" + sec, ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> menuNew.fire());
            }

        }
    }

    /**
     * Metodi näyttää tuloksista viisi parasta, tai kaikki jos niitä on alle
     * viisi
     *
     * @throws SQLException
     */
    public void showScores() throws SQLException {
        List<HighScore> newScores = new ArrayList();
        if (highDao.howManyScores() < 5) {
            newScores = highDao.findAll();
        } else {
            newScores = highDao.findFive();
        }
        String high = "";
        if (newScores.isEmpty()) {
            String text = "No highscores";
            high = high + text;
        } else {
            for (int i = 0; i < newScores.size(); i++) {
                String score = i + 1 + ". " + newScores.get(i).getName() + "    " + newScores.get(i).getTime() / 60 + ":" + newScores.get(i).getTime() % 60 + "\n";
                high = high + score;
            }
        }

        Alert alert = new Alert(Alert.AlertType.NONE, high, ButtonType.CANCEL);
        alert.setTitle("High Scores");
        alert.showAndWait().isPresent();
    }

    /**
     * Metodi katsoo onko kaikki kortit pöydällä ja oikein päin, jos ne ovat
     * siirtää metodi kaikki kortit oikeisiin lopetuspinoihin
     *
     * @throws SQLException
     */
    public void allUp() throws SQLException {
        boolean canBeTurned = true;
        if (stacks.get(0).sizeOfTheStack() == 0 && stacks.get(1).sizeOfTheStack() == 0) {
            for (CardStack stack : stacks) {
                if (stack.isTheStackOnTheTable()) {
                    for (Card card : stack.cards()) {
                        if (!card.isTheCardFaceUp()) {
                            canBeTurned = false;
                        }
                    }
                }
            }
        }
        if (canBeTurned) {
            boolean moving = false;
            do {
                moving = false;
                for (CardStack stack : stacks) {
                    if (stack.isTheStackOnTheTable()) {
                        if (stack.sizeOfTheStack() != 0) {
                            Card card = stack.topCard();
                            if (moveToEndStack(card)) {
                                moving = true;
                            }
                        }
                    }
                }
            } while (moving);
        }
    }

    @Override
    public void stop() throws Exception {
        timer.cancel();
        super.stop();
    }

}
