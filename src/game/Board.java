package game;

import item.Dice;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Board implements Initializable {

    public ImageView sixDice;
    public TextField showFaceValue;
    public Button roll;
    public ImageView dice01;
    public ImageView dice02;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void rollDicesAndPutImage(ActionEvent actionEvent) {
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        dice1.randomFaceValue();
        dice2.randomFaceValue();
        System.out.println("Dice 1: " + dice1.getFaceValue());
        System.out.println("Dice 2: " + dice2.getFaceValue());

        ArrayList<String>  diceImages = new ArrayList<>();
        diceImages.addAll(Arrays.asList("one.png", "two.png", "three.png", "four.png", "five.png", "six.png"));
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 6; j++){
                if(i==0){
                    if(dice1.getFaceValue() == j+1){
                        dice01.setImage(new Image(diceImages.get(j)));
                    }
                }
                else if(i==1){
                    if(dice2.getFaceValue() == j+1){
                        dice02.setImage(new Image(diceImages.get(j)));
                    }
                }
            }
        }
    }
}
