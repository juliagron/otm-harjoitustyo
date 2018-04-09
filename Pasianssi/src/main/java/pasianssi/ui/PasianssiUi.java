/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


/**
 *
 * @author juliagro
 */
public class PasianssiUi extends Application {
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Ikkuna");
        
        StackPane layout = new StackPane();
        
        Scene scene = new Scene(layout, 850, 600,Color.GREEN);
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
    }
    
}
